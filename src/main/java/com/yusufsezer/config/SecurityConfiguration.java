package com.yusufsezer.config;

import com.yusufsezer.enumeration.Role;
import com.yusufsezer.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GlobalService globalService;

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {

        http.exceptionHandling().accessDeniedPage("/");

        http.authorizeRequests()
                .mvcMatchers("/admin/**")
                .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.EDITOR.name())
                .mvcMatchers("/login").anonymous();

        http.formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(globalService.customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
