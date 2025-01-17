package com.xiaomi.aiot.onetest.jmeter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "onetest.jmeter")
public class JmeterProperties {
    /**
     * 是否在应用启动时初始化JMeter环境
     * 默认值：false
     */
    private boolean initializeOnStartup = false;

    /**
     * 是否在应用退出时清理临时文件
     * 默认值：true
     */
    private boolean cleanOnExit = false;

    public boolean isInitializeOnStartup() {
        return initializeOnStartup;
    }

    public void setInitializeOnStartup(boolean initializeOnStartup) {
        this.initializeOnStartup = initializeOnStartup;
    }

    public boolean isCleanOnExit() {
        return cleanOnExit;
    }

    public void setCleanOnExit(boolean cleanOnExit) {
        this.cleanOnExit = cleanOnExit;
    }
}
