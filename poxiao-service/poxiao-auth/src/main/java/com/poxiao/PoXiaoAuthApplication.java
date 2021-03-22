package com.poxiao;

import com.poxiao.api.system.annotation.EnablePxFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qinqi
 * @date 2020/9/17
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnablePxFeignClients
public class PoXiaoAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoXiaoAuthApplication.class, args);
    }
}
