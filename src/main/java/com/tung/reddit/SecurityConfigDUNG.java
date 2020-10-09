package com.tung.reddit;

import com.tung.reddit.services.AppUserService;
import com.tung.reddit.services.AppUserServiceDUNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigDUNG extends WebSecurityConfigurerAdapter {
    @Qualifier("appUserServiceImplDUNG")
    @Autowired
    AppUserServiceDUNG appUserServiceImplDUNG;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) appUserServiceImplDUNG).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/create").permitAll()
                .and()
                .authorizeRequests().antMatchers("/user1/**").hasAnyRole("USER")
                .and()
              //  .authorizeRequests().antMatchers("/premium**").hasAnyRole("PREMIUM_USER")
              //  .and()
                .authorizeRequests().antMatchers("/admin1/**").hasAnyRole("ADMIN")
                .and()
               // .authorizeRequests().antMatchers("/admin**").hasAnyRole("MODERATOR")
               // .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }
}