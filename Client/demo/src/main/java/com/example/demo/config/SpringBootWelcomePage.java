package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase para configurar la pagina de inicio
 * @author Daniel
 * @version 1.0 27/03/2020
 * @since JDK 1.8
 */
@Configuration
public class SpringBootWelcomePage implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/index.xhtml");

        registry.addViewController("/")
                .setViewName("forward:/pages/principal.xhtml");
    }
}
