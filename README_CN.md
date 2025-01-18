# JMeter Spring Boot Starter 中文文档


用于在Spring Boot应用中集成JMeter的Starter组件

## 功能特性

- 🚀 轻松集成到Spring Boot应用
- 🔧 自动初始化JMeter环境
- 🧹 应用关闭时自动清理临时文件
- 📝 可通过配置文件进行配置

## 环境要求

- Java 8+
- Spring Boot 2.7.x
- JMeter 5.6.3

## 安装

在`pom.xml`中添加依赖：

```xml
<dependency>
    <groupId>com.wick.onetest</groupId>
    <artifactId>jmeter-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 配置

在`application.yml`中添加以下配置：

```yaml
onetest:
  jmeter:
    initialize-on-startup: false  # 是否在应用启动时初始化JMeter环境
    clean-on-exit: true           # 是否在应用退出时清理临时文件
```


## 日志输出

初始化过程中会输出详细日志：
```
┌────────────────────────────────────────────────────────────────────────────┐
│ [JMeter Init] Starting JMeter environment initialization                   │
├────────────────────────────────────────────────────────────────────────────┤
│ → [1/7] Created temp dir: /tmp/jmeter12345
│ → [2/7] Copied properties files
│ → [3/7] Set JMeter home: /tmp/jmeter12345
│ → [4/7] Loaded properties from: /tmp/jmeter12345/jmeter.properties
│ → [5/7] Initialized locale: en_US
│ → [6/7] Loaded save service properties
│ → [7/7] Verified JMeter home: /tmp/jmeter12345
└────────────────────────────────────────────────────────────────────────────┘
```
