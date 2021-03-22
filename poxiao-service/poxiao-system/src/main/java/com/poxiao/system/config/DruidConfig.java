package com.poxiao.system.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.poxiao.common.core.datasource.DynamicDataSource;
import com.poxiao.common.core.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinqi
 * @date 2020/9/14
 */
@Configuration
public class DruidConfig {

    @Autowired
    private DruidProperties druidProperties;

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(druidDataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    //todo ConditionalOnProperty注解和ConfigurationProperties注解的关系
    public DataSource slaveDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(druidDataSource);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(),masterDataSource());
        if (druidProperties.slaveEnable) {
            targetDataSources.put(DataSourceType.SLAVE.name(),slaveDataSource());
        }
        return new DynamicDataSource(masterDataSource(),targetDataSources);
    }
}
