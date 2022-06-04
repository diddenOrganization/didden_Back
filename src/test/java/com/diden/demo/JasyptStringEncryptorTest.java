package com.diden.demo;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JasyptStringEncryptorTest {

    private String password = "Tourproject123!";

    @Test
    public void JasyptStringEncryptor() {
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
        System.out.println("테스트");
        // /key/Wallet_tourdev
        // url: jdbc:oracle:thin:@tourdev_medium?TNS_ADMIN=/key/Wallet_tourdev
        // driver-class-name: oracle.jdbc.OracleDriver
        // username: admin
        // password: Tourproject123!

        log.error("{}", encryptor.encrypt("jdbc:log4jdbc:oracle:thin:@tourdev_medium?TNS_ADMIN=/key/Wallet_tourdev"));
        log.error("{}", encryptor.encrypt("oracle.jdbc.OracleDriver"));
        log.error("{}", encryptor.encrypt("admin"));
        log.error("{}", encryptor.encrypt("Tourproject123!"));

        log.info("===================================================== {}",
                encryptor.encrypt(
                        "jdbc:log4jdbc:oracle:thin:@tourdev_medium?TNS_ADMIN=/Users/ohjeung/key/Wallet_tourdev"));
        log.info("===================================================== {}",
                encryptor.encrypt("net.sf.log4jdbc.sql.jdbcapi.DriverSpy"));
        log.info("===================================================== {}", encryptor.encrypt("admin"));
        log.info("===================================================== {}", encryptor.encrypt("Tourproject123!"));
    }
}
