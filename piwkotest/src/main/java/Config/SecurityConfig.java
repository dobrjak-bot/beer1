package Config;


import Service.UserPrincipalDetailsService;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    UserPrincipalDetailsService userPrincipalDetailsService;
    UserService userService;

    @Autowired
    public SecurityConfig( UserPrincipalDetailsService userPrincipalDetailsService,UserService userService)
    {
        this.userPrincipalDetailsService=userPrincipalDetailsService;
        this.userService=userService;
    }



    //pobieranie uzytkownikow z bazy
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authenticationProvider());
    }


    //dostep do endpointow
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/completehops").authenticated()
                .antMatchers("/completemalt").authenticated()
                .antMatchers("/completeyeast").authenticated()
                .antMatchers("/whoim").authenticated()
                .antMatchers("/supplies").authenticated()
                .antMatchers("/deletemoldy").authenticated()
                .antMatchers("/deleteused").authenticated()
                .antMatchers("/logs").hasRole("ADMIN")
                .and()
                .formLogin().permitAll();
    }

    //sprawdzanie princepalow usera
    @Bean
    DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }


    //hasowanie hasel
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
