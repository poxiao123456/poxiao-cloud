package com.poxiao;

import com.poxiao.api.system.annotation.EnablePxFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qinqi
 * @date 2020/9/14
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.poxiao.system.mapper")
@EnablePxFeignClients
public class PoxiaoSystemApplication {
    
    public static void main(String[] args){
        SpringApplication.run(PoxiaoSystemApplication.class,args);
    }
}
