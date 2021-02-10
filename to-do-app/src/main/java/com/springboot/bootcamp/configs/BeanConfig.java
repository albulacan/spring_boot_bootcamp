package com.springboot.bootcamp.configs;

import com.springboot.bootcamp.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {

    @Bean
    public UserService getUserService() {
        return new UserService();
    }
}
