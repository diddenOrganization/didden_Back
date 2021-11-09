package com.diden.demo;

import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String password;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig conf = new SimpleStringPBEConfig();
        System.out.println("encryptor: " + password);
        conf.setPoolSize(1);
        conf.setPassword(password);
        conf.setStringOutputType("base64");
        conf.setKeyObtentionIterations(1000);
        conf.setProvider(new BouncyCastlePQCProvider());
        conf.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");
        conf.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        encryptor.setConfig(conf);
        return encryptor;
    }
}
