package com.diden.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String password;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // /key/Wallet_tourdev
        // url: jdbc:oracle:thin:@tourdev_medium?TNS_ADMIN=/key/Wallet_tourdev
        // driver-class-name: oracle.jdbc.OracleDriver
        // username: admin
        // password: Tourproject123!

        log.info("===================================================== {}",
                encryptor.encrypt("jdbc:oracle:thin:@tourdev_medium?TNS_ADMIN=/key/Wallet_tourdev"));
        log.info("===================================================== {}",
                encryptor.encrypt("oracle.jdbc.OracleDriver"));
        log.info("===================================================== {}", encryptor.encrypt("admin"));
        log.info("===================================================== {}", encryptor.encrypt("Tourproject123!"));

        return encryptor;
    }
}
