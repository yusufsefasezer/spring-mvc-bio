package com.yusufsezer;

import com.yusufsezer.config.AOPConfig;
import com.yusufsezer.config.DataJPAConfig;
import com.yusufsezer.config.DataSourceConfig;
import com.yusufsezer.config.JPAConfig;
import java.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                DataSourceConfig.class,
                JPAConfig.class,
                DataJPAConfig.class,
                AOPConfig.class
        );

        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);
    }

}
