package com.example.piwkotest;


import Entity.*;
import Service.MailService;
import Service.TokenService;
import Service.UserService;
import Service.YeastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;


@RestController
public class test {

    UserService userService;
    PasswordEncoder passwordEncoder;
    TokenService tokenService;
    MailService mailService;
    YeastService yeastService;


    @Autowired
    public test(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService, MailService mailService, YeastService yeastService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.mailService = mailService;
        this.yeastService=yeastService;
    }


    @GetMapping("/test1")
    public String test1() {
        return "test=1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

    @GetMapping("/test3")
    public String test3() {
        return "test3";
    }

    @PostMapping("/register")
    public void Register(@RequestParam String login, String password, String email)
    {
        String role = "ROLE_USER";
        Boolean enable=false;
        User user = new User(login, passwordEncoder.encode(password), email, role,enable);
        Token token = new Token(1 + (int) (Math.random() * 200), user);
        user.setToken(token);
        tokenService.save(token);
        userService.save(user);
        mailService.SenderMail(user.getEmail(), "link : http://localhost:8080//activation    Your token=" + token.getValue(), "activation of your account");
    }


    @PostMapping("/activation")
    public void Activation(@RequestParam String login,int value)
    {
        User userr = userService.findByLogin(login);
        int valueoftoken= userr.getToken().getValue();


        if(userr!=null && valueoftoken==value)
        {
            userr.setEnabled(true);
            userService.save(userr);
        }
    }

    @PostMapping("/completehops")
    public void CompleteHops(@RequestParam int count, String date)
    {
        String date2 = date;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  datefromuser = LocalDate.parse(date2,df);
        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);

        for (int i = 0; i < count;  i++)
        {
            Hops hops= new Hops(datefromuser);
            user.getHopsList().add(hops);

        }
        userService.save(user);
    }

    @PostMapping("/completemalt")
    public void CompleteMalt(@RequestParam int count, String date)
    {
        String date2 = date;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  datefromuser = LocalDate.parse(date2,df);
        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);

        for (int i = 0; i < count;  i++)
        {
            Malt malt= new Malt(datefromuser);
            user.getMaltList().add(malt);

        }
        userService.save(user);
    }

    @PostMapping("/completeyeast")
    public void CompleteYeast(@RequestParam int count, String date)
    {
        String date2 = date;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  datefromuser = LocalDate.parse(date2,df);
        String loggeduser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userService.findByLogin(loggeduser);

        for (int i = 0; i < count;  i++)
        {
            Yeast yeast= new Yeast(datefromuser);
            user.getYeastList().add(yeast);

        }
        userService.save(user);

    }

//DZIALA KURWA
    @GetMapping("/test4")
    public void abc()
    {
        String date2 = "2020-05-05";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  d1 = LocalDate.parse(date2,df);
        String user1 = SecurityContextHolder.getContext().getAuthentication().getName();
        int count=4;
        User user =userService.findByLogin(user1);

        for (int i = 0; i < count;  i++)
        {
            System.out.println("dupa");
            Malt malt= new Malt(d1);
            user.getMaltList().add(malt);
             Yeast yeast= new Yeast(d1);
            user.getYeastList().add(yeast);

        }
        userService.save(user);
    }
}


