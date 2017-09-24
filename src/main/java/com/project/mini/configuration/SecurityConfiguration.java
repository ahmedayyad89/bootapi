package com.project.mini.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as username , password, 1 as enabled from user where email=?")
                .authoritiesByUsernameQuery("select email as username , role from user where email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll() // Should igonre OPTIONS requests
                .antMatchers("/admin/**").hasRole("ADMIN")//Only Allow Admins to access specific api mathods
                //Should allow any registered user to access the rest of the api's methods
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .and()
                .csrf().disable().headers().frameOptions().disable();

        //invalidate cookies of logged out user
        http.logout().deleteCookies("JSESSIONID");
    }

}