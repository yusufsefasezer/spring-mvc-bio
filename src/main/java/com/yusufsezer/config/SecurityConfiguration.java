package com.yusufsezer.config;

import com.yusufsezer.enumeration.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            HandlerMappingIntrospector introspector)
            throws Exception {

        http.exceptionHandling((exceptionHandling) -> {
            exceptionHandling.accessDeniedPage("/");
        });

        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.authorizeHttpRequests((authorizeHttpRequests) -> {
            authorizeHttpRequests
                    .requestMatchers(mvcMatcherBuilder.pattern("/admin/**"))
                    .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.EDITOR.name())
                    .requestMatchers(mvcMatcherBuilder.pattern("/login"))
                    .anonymous()
                    .anyRequest()
                    .permitAll();
        });

        http.formLogin((formLogin) -> {
            formLogin.loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password");
        });

        http.logout((logout) -> {
            logout.logoutRequestMatcher(mvcMatcherBuilder.pattern("/logout"));
        });

        return http.build();
    }

}
