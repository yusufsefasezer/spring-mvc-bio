package com.yusufsezer.config;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

@Configuration
@ComponentScan(
        basePackages = "com.yusufsezer.controller",
        useDefaultFilters = false,
        includeFilters = {
            @Filter(
                    type = FilterType.ANNOTATION,
                    value = {
                        Controller.class,
                        ControllerAdvice.class
                    }
            )
        }
)
public class WebMvcConfiguration extends DelegatingWebMvcConfiguration {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp();
    }

    @Override
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Override
    public ThemeResolver themeResolver() {
        return new SessionThemeResolver();
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<LocalDate>() {
            @Override
            public String print(LocalDate object, Locale locale) {
                return DateTimeFormatter.ISO_DATE.format(object);
            }

            @Override
            public LocalDate parse(String text, Locale locale)
                    throws ParseException {
                return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
            }
        });
        super.addFormatters(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LocaleChangeInterceptor());
        registry.addInterceptor(new ThemeChangeInterceptor());
    }

}
