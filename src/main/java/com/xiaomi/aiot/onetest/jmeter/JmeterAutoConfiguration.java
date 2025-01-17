package com.xiaomi.aiot.onetest.jmeter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(JmeterProperties.class)
public class JmeterAutoConfiguration {

    @Bean
    public JmeterInitializer jmeterInitializer(JmeterProperties jmeterProperties) {
        return new JmeterInitializer(jmeterProperties);
    }

    @Bean
    public JmeterApplicationRunner jmeterApplicationRunner(JmeterInitializer jmeterInitializer, JmeterProperties jmeterProperties) {
        return new JmeterApplicationRunner(jmeterInitializer, jmeterProperties);
    }
}
