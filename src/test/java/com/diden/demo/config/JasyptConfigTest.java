package com.diden.demo.config;

import com.diden.demo.TestStartConfig;
import com.diden.demo.common.config.JasyptConfig;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JasyptConfigTest extends TestStartConfig {
  @Autowired
  JasyptConfig jasyptConfig;

  @Test
  void passwordEncryptor() {
    StringEncryptor encryptor = jasyptConfig.stringEncryptor();
    String pwd = "Tourproject1234!";
    String encrypt = encryptor.encrypt(pwd);
    String decrypt = encryptor.decrypt(encrypt);

    System.out.println(encrypt);
    assertThat(pwd).isEqualTo(decrypt);
  }

  @Test
  void usernameEncryptor() {
    StringEncryptor encryptor = jasyptConfig.stringEncryptor();
    String username = "admin";
    String encrypt = encryptor.encrypt(username);
    String decrypt = encryptor.decrypt(encrypt);

    System.out.println(encrypt);
    assertThat(username).isEqualTo(decrypt);
  }

  @Test
  void databaseUrlEncryptor() {
    StringEncryptor encryptor = jasyptConfig.stringEncryptor();
    String uri = "jdbc:oracle:thin:@tourdev_medium?TNS_ADMIN=/Users/ohjeung/key/Wallet_tourdev";
    String encrypt = encryptor.encrypt(uri);
    String decrypt = encryptor.decrypt(encrypt);

    System.out.println(encrypt);
    assertThat(uri).isEqualTo(decrypt);
  }

  @Test
  void driverEncryptor() {
    StringEncryptor encryptor = jasyptConfig.stringEncryptor();
    String driver = "oracle.jdbc.OracleDriver";
    String encrypt = encryptor.encrypt(driver);
    String decrypt = encryptor.decrypt(encrypt);

    System.out.println(encrypt);
    assertThat(driver).isEqualTo(decrypt);
  }
}
