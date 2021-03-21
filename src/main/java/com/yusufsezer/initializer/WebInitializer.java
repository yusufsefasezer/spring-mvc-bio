package com.yusufsezer.initializer;

import com.yusufsezer.config.AOPConfig;
import com.yusufsezer.config.AppConfig;
import com.yusufsezer.config.DataJPAConfig;
import com.yusufsezer.config.DataSourceConfig;
import com.yusufsezer.config.JPAConfig;
import com.yusufsezer.config.MessageConfig;
import com.yusufsezer.config.SecurityConfig;
import com.yusufsezer.config.SecurityConfiguration;
import com.yusufsezer.config.WebConfig;
import com.yusufsezer.config.WebMvcConfiguration;
import javax.servlet.Filter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            AppConfig.class,
            MessageConfig.class,
            DataSourceConfig.class,
            JPAConfig.class,
            DataJPAConfig.class,
            SecurityConfig.class,
            SecurityConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            WebConfig.class,
            WebMvcConfiguration.class,
            AOPConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return new Filter[]{
            loggingFilter
        };
    }

}
