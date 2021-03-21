package com.yusufsezer.config;

import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaRepositories("com.yusufsezer.*")
@EnableJpaAuditing
@EnableSpringDataWebSupport
public class DataJPAConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Stream.of(loginUser(), systemUser())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public Optional<String> loginUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(context -> context.getAuthentication())
                .map(authentication -> authentication.getName());
    }

    public Optional<String> systemUser() {
        return Optional.of(System.getProperty("user.name"));
    }

}
