package com.sr.cloud.business;

import com.sr.cloud.user.api.UserServiceApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableFeignClients(clients = UserServiceApi.class)
@EnableEurekaClient
@EnableAspectJAutoProxy
@ComponentScan("com.sr.cloud.*")
@MapperScan("com.sr.cloud.business.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
