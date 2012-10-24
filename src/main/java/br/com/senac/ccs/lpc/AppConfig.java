package br.com.senac.ccs.lpc;

import javax.faces.application.ResourceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"br.com.senac.ccs.lpc"})
public class AppConfig extends WebMvcConfigurationSupport {
    @Override
    public void addViewControllers ( ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Override
    protected void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("resources/");
    }
    
}
