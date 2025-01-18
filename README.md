# JMeter Spring Boot Starter

[简体中文](README_CN.md)

Spring Boot Starter for integrating JMeter into your Spring Boot applications.

## Features

- 🚀 Easy integration with Spring Boot applications
- 🔧 Automatic JMeter environment initialization
- 🧹 Cleanup temporary files on application shutdown
- 📝 Configurable through application properties

## Requirements

- Java 8+
- Spring Boot 2.7.x
- JMeter 5.6.3

## Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.wick.onetest</groupId>
    <artifactId>jmeter-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Configuration

Add the following properties to your `application.yml`:

```yaml
onetest:
  jmeter:
    initialize-on-startup: false  # Whether to initialize JMeter environment on application startup
    clean-on-exit: true           # Whether to clean up temporary files on application exit
```


## Logging

The starter provides detailed logging during initialization:

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
