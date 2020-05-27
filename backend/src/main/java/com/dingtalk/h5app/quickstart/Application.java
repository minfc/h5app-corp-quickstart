package com.dingtalk.h5app.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动入口
 *
 * @author openapi@dingtalk
 * @date 2020/2/4
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.dingtalk.h5app.quickstart")
@EntityScan("com.dingtalk.h5app.quickstart.domain.*")
@EnableJpaRepositories("com.dingtalk.h5app.quickstart.dao")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
