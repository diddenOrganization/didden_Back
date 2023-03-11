package com.diden.demo.user;

import com.diden.demo.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

  @Autowired
  UserService userService;

  @Test
  void pageable() {
  }
}
