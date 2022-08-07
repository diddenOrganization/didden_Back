package com.diden.demo.user;

import com.diden.demo.config.LazyHolderObject;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserApiController {
  private final UserService userService;

  @GetMapping(value = "/info")
  public String loginCheck() {
    JsonObject obj = new JsonObject();
    obj.addProperty("process", "check");
    return obj.toString();
  }

  @GetMapping(value = "/list")
  public ResponseEntity<String> userList() {
    return ResponseEntity.ok().body(LazyHolderObject.getGson().toJson(userService.userList()));
  }

  @PostMapping
  public ResponseEntity<String> userInfo(@RequestBody(required = false) UserVo userVo) {
    UserVo userInfo = userService.userInfo(userVo);
    JsonObject userResult = new JsonObject();
    userResult.addProperty("result", userInfo != null);
    return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
  }

  @PostMapping("/api/social/json")
  public ResponseEntity<JsonObject> socialLoginLogic(@RequestBody JsonObject param) {
    log.info("{}", param);
    return ResponseEntity.ok(param);
  }

  @PostMapping("/social/login")
  public ResponseEntity<String> userSocialLogin(@RequestBody JsonObject param) {
    final boolean result = userService.socialSignup(param);

    final JsonObject obj = new JsonObject();
    obj.addProperty("result", result);

    return ResponseEntity.ok(obj.toString());
  }

  @PostMapping(value = "/logout")
  public ResponseEntity<String> userLogout(@RequestBody(required = false) UserVo userVo) {
    JsonObject userResult = new JsonObject();

    try {
      if (!Objects.toString(userVo.getUserId(), "").equals("")
          && !Objects.toString(userVo.getUserPassword(), "").equals("")) {
        UserVo getUserVo = userService.userInfo(userVo);

        getUserVo.setUserRefreshToken("");
        userService.userUpdate(getUserVo);
        userResult.addProperty("result", true);

        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
      } else {
        throw new Exception("잘못된 접근.");
      }

    } catch (Exception e) {
      return new ResponseEntity<>(errorMethod("", e), HttpStatus.OK);
    }
  }

  @PutMapping
  public ResponseEntity<String> userInsert(@RequestBody(required = false) UserVo userVo) {
    JsonObject userResult = new JsonObject();
    try {
      if (Objects.isNull(userVo)) {
        throw new Exception("파라미터 null");
      }

      UserVo userCheck = new UserVo();
      userCheck.setUserId(userVo.getUserId());
      userCheck.setUserPassword(userVo.getUserPassword());

      userCheck = userService.userInfo(userCheck);
      if (Objects.isNull(userCheck)) {
        userService.userInsert(userVo);
        userResult.addProperty("result", true);
        userResult.addProperty("put", "insert");
      }

    } catch (Exception e) {
      e.printStackTrace();
      userResult.addProperty("result", false);
      userResult.addProperty("error", e.getMessage());
      return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
  }

  @PutMapping(value = "/update")
  public ResponseEntity<String> userUpdate(@RequestBody(required = false) UserVo userVo) {
    JsonObject userResult = new JsonObject();
    try {
      if (Objects.isNull(userVo)) {
        throw new Exception("파라미터 null");
      }

      UserVo userCheck = new UserVo();
      userCheck.setUserId(userVo.getUserId());
      userCheck.setUserPassword(userVo.getUserPassword());

      userCheck = userService.userInfo(userCheck);
      if (!Objects.isNull(userCheck)) {
        userService.userUpdate(userVo);
        userResult.addProperty("result", true);
        userResult.addProperty("put", "update");
      }

    } catch (Exception e) {
      e.printStackTrace();
      userResult.addProperty("result", false);
      userResult.addProperty("error", e.getMessage());
      return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
  }

  @DeleteMapping(value = "")
  public ResponseEntity<String> userDelete(@RequestBody(required = false) UserVo userVo) {
    JsonObject userResult = new JsonObject();
    try {
      if (Objects.isNull(userVo)) {
        throw new Exception("파라미터 null");
      }

      UserVo userCheck = new UserVo();
      userCheck.setUserId(userVo.getUserId());
      userCheck.setUserPassword(userVo.getUserPassword());

      userCheck = userService.userInfo(userCheck);
      if (Objects.isNull(userCheck)) {
        throw new Exception("삭제할 계정이 존재하지 않습니다.");
      } else {
        userService.userDelete(userVo);
        userResult.addProperty("result", true);
      }

    } catch (Exception e) {
      e.printStackTrace();
      userResult.addProperty("result", false);
      userResult.addProperty("error", e.getMessage());
      return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
  }

  public String errorMethod(String errorType, Exception e) {
    JsonObject errorResult = new JsonObject();

    if (e != null) {
      errorResult.addProperty("result", false);
      errorResult.addProperty("error", e.getMessage());
    } else if ("login".equals(errorType)) {
      errorResult.addProperty("result", false);
      errorResult.addProperty("error", "계정이 존재하지 않습니다.");
    }

    return errorResult.toString();
  }
}
