package com.xiaomi.aiot.onetest.jmeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JmeterApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(JmeterApplicationRunner.class);
    
    private final JmeterInitializer jmeterInitializer;
    private final JmeterProperties jmeterProperties;

    public JmeterApplicationRunner(JmeterInitializer jmeterInitializer, JmeterProperties jmeterProperties) {
        this.jmeterInitializer = jmeterInitializer;
        this.jmeterProperties = jmeterProperties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (jmeterProperties.isInitializeOnStartup()) {
            logger.info("Enabled JMeter initialization on startup");
            jmeterInitializer.initEnv();
        } else {
            logger.info("JMeter initialization on startup is disabled");
        }
    }
}
