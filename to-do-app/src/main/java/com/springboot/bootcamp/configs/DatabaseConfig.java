package com.springboot.bootcamp.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("db")
public class DatabaseConfig {

    private String server;
    private String name;
    private String username;
    private String password;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        System.out.println(this.server);
        System.out.println(this.name);
        System.out.println(this.username);
        System.out.println(this.password);
    }

    @Profile("sit")
    @Bean
    public void sitDbConnection() {
        System.out.println("Hello from sit");
        System.out.println(this.server);
        System.out.println(this.name);
        System.out.println(this.username);
        System.out.println(this.password);
    }

    @Profile("uat")
    @Bean
    public void uatDbConnection() {
        System.out.println("Hello from uat");
        System.out.println(this.server);
        System.out.println(this.name);
        System.out.println(this.username);
        System.out.println(this.password);
    }

}
