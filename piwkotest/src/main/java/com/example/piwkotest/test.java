package com.example.piwkotest;


import Entity.*;
import Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.client.Hop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
public class test
{

    UserService userService;
    PasswordEncoder passwordEncoder;
    TokenService tokenService;
    MailService mailService;
    YeastService yeastService;
    MaltService maltService;
    HopsService hopsService;

    String date2 = "2020-01-01";
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate  datefromuser = LocalDate.parse(date2,df);
    java.sql.Date date =java.sql.Date.valueOf( datefromuser );


    Logger logger= LoggerFactory.getLogger(test.class);


    @Autowired
    public test(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService, MailService mailService, YeastService yeastService, MaltService maltService,HopsService hopsService)
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.mailService = mailService;
        this.yeastService=yeastService;
        this.maltService=maltService;
        this.hopsService=hopsService;
    }



    @PostMapping("/register")
    public ResponseEntity Register(@RequestParam String login, String password, String email)
    {
        logger.error("Someone create new account login = " + login + "  password= " +password + "  email = " + email);
        String role = "ROLE_USER";
        Boolean enable=false;
        User user = new User(login, passwordEncoder.encode(password), email, role,enable);
        Token token = new Token(1 + (int) (Math.random() * 200), user);
        user.setToken(token);
        tokenService.save(token);
        userService.save(user);
        mailService.SenderMail(user.getEmail(), "link : http://localhost:8080//activation    Your token=" + token.getValue(), "activation of your account");
        return ResponseEntity.ok().build();
    }


    @PostMapping("/activation")
    public ResponseEntity Activation(@RequestParam String login,int value)
    {
        User userr = userService.findByLogin(login);
        int valueoftoken= userr.getToken().getValue();

        if(userr!=null && valueoftoken==value)
        {
            logger.error(login+" has been activated");
            userr.setEnabled(true);
            userService.save(userr);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/completehops")
    public ResponseEntity CompleteHops(@RequestParam int count, String date)
    {
        String date2 = date;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  datefromuser = LocalDate.parse(date2,df);

        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);
        if(user!= null)
        {
            for (int i = 0; i < count;  i++)
            {
                Hops hops= new Hops(datefromuser);
                user.getHopsList().add(hops);
            }
            userService.save(user);
            logger.error(loggeduser+" add  " + count+" of hops , expiration date" + datefromuser);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/completemalt")
    public ResponseEntity CompleteMalt(@RequestParam int count, String date)
    {
        String date2 = date;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  datefromuser = LocalDate.parse(date2,df);

        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);
        if(user!= null)
        {
            for (int i = 0; i < count;  i++)
            {
                Malt malt= new Malt(datefromuser);
                user.getMaltList().add(malt);
            }
            userService.save(user);
            logger.error(loggeduser+" add  " + count+" of malt , expiration date" + datefromuser);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/completeyeast")
    public ResponseEntity CompleteYeast(@RequestParam int count, String date)
    {
        String date2 = date;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  datefromuser = LocalDate.parse(date2,df);

        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);

        if(user!= null)
        {
            for (int i = 0; i < count;  i++)
            {
                Yeast yeast= new Yeast(datefromuser);
                user.getYeastList().add(yeast);
            }
            userService.save(user);
            logger.error(loggeduser+" add  " + count+" of yeast  , expiration date" + datefromuser);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }



    @GetMapping("/supplies")
    public String Supplies()
    {
        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);

        int YeastCount=user.getYeastList().size();
        int HopsCount=user.getHopsList().size();
        int  MaltCount= user.getMaltList().size();

        int count_of_moldy_hops=hopsService.findByDate(user.getId_users(), date).size();
        int count_of_moldy_malt=maltService.findByDate(user.getId_users(), date).size();
        int count_of_moldy_yeast=yeastService.findByDate(user.getId_users(), date).size();

        if(user!= null)
        {
            logger.error(loggeduser+" he checked his inventory");
            return "   yeastCount=   " +YeastCount +"" +
                    "    HopsCount =   "+HopsCount +
                    "   MaltCount=   " +MaltCount +" " +
                    "  count of moldy yeast=  "+count_of_moldy_yeast +
                    "   count of moldy malt =  "+count_of_moldy_malt +
                    "   count of moldy hops =  "+count_of_moldy_hops ;
        }
        else
        {
            return null;
        }

    }


    @DeleteMapping("/deletemoldy")
    public ResponseEntity DeleteMoldy()
    {
        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);

        if(user!= null)
        {
            List<Hops> HopsToDelete = hopsService.findByDate(user.getId_users(), date);
            List<Malt> MaltToDelete = maltService.findByDate(user.getId_users(), date);
            List<Yeast> YeastToDelete = yeastService.findByDate(user.getId_users(), date);
            hopsService.deleteMoldy(HopsToDelete);
            maltService.deleteMoldy(MaltToDelete);
            yeastService.deleteMoldy(YeastToDelete);
            logger.error(loggeduser + " he deleted moldy supplies");
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/deleteused")
    public ResponseEntity DeleteUsed(@RequestParam Integer count)
    {

        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByLogin(loggeduser);

        List<Hops> HopsNotMoldy = hopsService.findByDate2(user.getId_users(), date);
        List<Malt> MaltNotMoldy = maltService.findByDate2(user.getId_users(), date);
        List<Yeast> YeastNotMoldy = yeastService.findByDate2(user.getId_users(), date);

        ArrayList<Hops> hopstodelete = new ArrayList<Hops>();
        ArrayList<Malt> malttodelete = new ArrayList<Malt>();
        ArrayList<Yeast> yeasttodelete = new ArrayList<Yeast>();

        if(user!= null)
        {
            for (int i = 0; i < count;  i++)
            {
                hopstodelete.add(HopsNotMoldy.get(i));
                malttodelete.add(MaltNotMoldy.get(i));
                yeasttodelete.add(YeastNotMoldy.get(i));
            }
            hopsService.deleteUsed(hopstodelete);
            maltService.deleteUsed(malttodelete);
            yeastService.deleteUsed(yeasttodelete);
            logger.error(loggeduser+"  he delete used supplies");
            return ResponseEntity.ok().build();
        }
        else
        {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/logs")
    public String getLogs() throws IOException 
    {
      String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
      User user = userService.findByLogin(loggeduser);

      if(user.getRoles().equals("ROLE_ADMIN"))
      {
          Path fileName = Path.of("D:"+ File.separator +"projekty"+ File.separator +"test query"+ File.separator +"piwkotest"+ File.separator +"logs"+ File.separator +"users-logger.log");
          return  Files.readString(fileName);
      }
            return null;
    }



    @GetMapping("/whoim")
    public void asd()
    {
        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.error(loggeduser + " to smiec");
        System.out.print(loggeduser);
    }
}


