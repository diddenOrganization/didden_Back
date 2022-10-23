package com.diden.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceImplTest {

  @Autowired
  UserService userService;

  @Test
  void pageable() {
    List<UserVo> pageable = userService.pageable(0);
    System.out.println(pageable);
  }
}
