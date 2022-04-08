package com.hackerearth.hackathonHackerearth.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");

    }*/
    /*//configure data source
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication() //got information and go to database
            .dataSource(dataSource) //embeded database h2 autoconfigure
            .withDefaultSchema()
            .withUser(
                    User.withUsername("user")
                    .password("user")
                    .roles("USER"))
            .withUser(
                    User.withUsername("admin")
                    .password("admin")
                    .roles("ADMIN")
            );
    }*/

    //configure the data into database

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select username,password,enabled from users where username=?")
            .authoritiesByUsernameQuery("select username,authority from authorities where username = ?");
    }

    @Bean
    public PasswordEncoder getPasswordEncode(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/home").permitAll() //for all
                //.antMatchers("/**").permitAll() // for other urls
                .and().formLogin();
    }
}
