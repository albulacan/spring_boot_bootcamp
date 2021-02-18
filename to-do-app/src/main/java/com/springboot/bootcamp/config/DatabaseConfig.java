package com.springboot.bootcamp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Profile("dev")
    @Bean
    public void devDbConnection() {
        System.out.println("Hello from dev");
        System.out.println(this.url);
        System.out.println(this.username);
        System.out.println(this.password);
        System.out.println(this.driverClassName);
    }

    @Profile("sit")
    @Bean
    public void sitDbConnection() {
        System.out.println("Hello from sit");
        System.out.println(this.url);
        System.out.println(this.username);
        System.out.println(this.password);
        System.out.println(this.driverClassName);
    }

    @Profile("uat")
    @Bean
    public void uatDbConnection() {
        System.out.println("Hello from uat");
        System.out.println(this.url);
        System.out.println(this.username);
        System.out.println(this.password);
        System.out.println(this.driverClassName);
    }

}
