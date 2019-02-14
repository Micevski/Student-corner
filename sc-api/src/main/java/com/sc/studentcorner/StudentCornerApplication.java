package com.sc.studentcorner;

import com.sc.studentcorner.model.Article;
import com.sc.studentcorner.model.ArticleCategory;
import com.sc.studentcorner.repository.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootApplication
public class StudentCornerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentCornerApplication.class, args);
    }


    // Fix the CORS errors
    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }


   /* @Bean
    public CommandLineRunner loadData(ArticleRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Article("Title", ArticleCategory.NEWS, "Bla bla bla bla bla bla bla", "thumbnail", LocalDateTime.now()));

        };
    }*/
}

