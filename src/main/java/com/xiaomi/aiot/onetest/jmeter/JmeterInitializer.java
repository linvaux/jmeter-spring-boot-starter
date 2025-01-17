package com.xiaomi.aiot.onetest.jmeter;

import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JmeterInitializer {
    private final JmeterProperties jmeterProperties;
    private File tempDir;

    public JmeterInitializer(JmeterProperties jmeterProperties) {
        this.jmeterProperties = jmeterProperties;
    }
    private static final Logger logger = LoggerFactory.getLogger(JmeterInitializer.class);

    public void initEnv() throws IOException {
        logger.info("┌────────────────────────────────────────────────────────────────────────────┐");
        logger.info("│ [JMeter Init] Starting JMeter environment initialization                   │");
        logger.info("├────────────────────────────────────────────────────────────────────────────┤");
        
        // Step 1: Create temporary directory
        this.tempDir = Files.createTempDirectory("jmeter").toFile();
        this.tempDir.deleteOnExit();
        logger.info("│ → [1/7] Created temp dir: {}", tempDir.getAbsolutePath());
        
        // Step 2: Copy properties files
        copyPropertiesToTempDir(tempDir);
        logger.info("│ → [2/7] Copied properties files");
        
        // Step 3: Set JMeter home
        JMeterUtils.setJMeterHome(tempDir.getAbsolutePath());
        logger.info("│ → [3/7] Set JMeter home: {}", tempDir.getAbsolutePath());
        
        // Step 4: Load JMeter properties
        JMeterUtils.loadJMeterProperties(new File(tempDir, "jmeter.properties").getAbsolutePath());
        logger.info("│ → [4/7] Loaded properties from: {}/jmeter.properties", tempDir.getAbsolutePath());
        
        // Step 5: Initialize locale
        JMeterUtils.initLocale();
        logger.info("│ → [5/7] Initialized locale: {}", JMeterUtils.getLocale());
        
        // Step 6: Load save service properties
        SaveService.loadProperties();
        logger.info("│ → [6/7] Loaded save service properties");
        
        // Step 7: Verify JMeter home
        String jmeterHome = JMeterUtils.getJMeterHome();
        logger.info("│ → [7/7] Verified JMeter home: {}", jmeterHome);
        logger.info("└────────────────────────────────────────────────────────────────────────────┘");
        
        // Register shutdown hook for cleanup
        if (jmeterProperties.isCleanOnExit()) {
            logger.info("┌────────────────────────────────────────────────────────────────────────────┐");
            logger.info("│ [JMeter Cleanup] Registering shutdown hook for temporary files cleanup     │");
            logger.info("└────────────────────────────────────────────────────────────────────────────┘");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (tempDir != null) {
                    try {
                        logger.info("Cleaning up JMeter temporary files, temporary directory: {}", tempDir.getAbsolutePath());
                        deleteDirectory(tempDir);
                        logger.info("Successfully cleaned up JMeter temporary files");
                    } catch (IOException e) {
                        logger.error("Failed to clean up JMeter temporary files", e);
                    }
                }
            }));
        }
        logger.info("┌────────────────────────────────────────────────────────────────────────────┐");
        logger.info("│ [JMeter Init] Environment initialized successfully                         │");
        logger.info("└────────────────────────────────────────────────────────────────────────────┘");
    }

    private void copyPropertiesToTempDir(File tempDir) throws IOException {
        logger.debug("Copying JMeter properties files to temporary directory");
        
        String[] propertiesFiles = {
            "jmeter.properties",
            "saveservice.properties",
            "upgrade.properties", 
            "user.properties"
        };

        for (String fileName : propertiesFiles) {
            try (InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("jmeter/bin/" + fileName)) {
                if (inputStream != null) {
                    File targetFile = new File(tempDir, fileName);
                    Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    logger.trace("Copied property file: {}", fileName);
                }
            }
        }
    }

    private void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        Files.delete(file.toPath());
                    }
                }
            }
            Files.delete(directory.toPath());
        }
    }
}
