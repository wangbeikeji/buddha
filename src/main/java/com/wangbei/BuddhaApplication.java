package com.wangbei;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * @author yuyidi 2017-07-06 10:25:00
 * @class com.wangbei.BuddhaApplication
 * @description 启用@EnableAutoConfiguration 及配置@ComponentScan 扫描当前类下及子级的包
 */
@SpringBootApplication
public class BuddhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuddhaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
            }
        };
    }
}

