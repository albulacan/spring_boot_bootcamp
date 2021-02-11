package com.springboot.bootcamp.configs;

import com.springboot.bootcamp.services.ToDoService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {

    @Bean
    public ToDoService getToDoService() {
        return new ToDoService();
    }
}
