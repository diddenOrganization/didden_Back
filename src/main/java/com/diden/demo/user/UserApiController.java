package com.diden.demo.user;

import com.diden.demo.utils.HttpResponse;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserApiController {
  private final UserService userService;

  @GetMapping(value = "/list")
  public HttpResponse<List<UserVo>> userList() {
    return HttpResponse.toResponse(HttpStatus.OK, "사용자 목록 데이터", userService.userList());
  }

  @GetMapping("/email-check")
  public HttpResponse<Void> emailDuplicateCheck(
      @NotNull(message = "이메일이 존재하지 않습니다.") @Email(message = "이메일 형식이 아닙니다.")
          final String userEmail) {
    if (userService.emailDuplicateCheck(userEmail)) {
      return HttpResponse.toResponse(HttpStatus.OK, "이미 가입된 이메일 입니다.");
    } else {
      return HttpResponse.toResponse(HttpStatus.ACCEPTED, "가입 가능한 이메일 입니다.");
    }
  }

  @GetMapping
  public HttpResponse<UserVo> userInfo(@RequestBody(required = false) UserVo userVo) {
    UserVo userInfo = userService.userInfo(userVo);
    if (userInfo == null) {
      return HttpResponse.toResponse(HttpStatus.NO_CONTENT, "사용자가 존재하지 않습니다.", null);
    } else {
      return HttpResponse.toResponse(HttpStatus.OK, "사용자가 존재합니다.", userInfo);
    }
  }

  @PostMapping("/social/login")
  public ResponseEntity<String> userSocialLogin(@RequestBody final JsonObject param) {
    final boolean result = userService.socialSignup(param);

    final JsonObject obj = new JsonObject();
    obj.addProperty("result", result);

    return ResponseEntity.ok(obj.toString());
  }

  @PostMapping
  public HttpResponse<Void> userInsert(
      @RequestBody @Valid @NotNull(message = "사용자 정보가 존재하지 않습니다.") final UserVo userVo) {
    userService.userInsert(userVo);
    return HttpResponse.toResponse(HttpStatus.CREATED, "회원가입이 되었습니다.");
  }

  @PatchMapping
  public HttpResponse<Void> userUpdate(
      @RequestBody(required = false) @NotNull(message = "사용자 정보가 존재하지 않습니다.") final UserVo userVo) {
    userService.userUpdate(userVo);
    return HttpResponse.toResponse(HttpStatus.OK, "회원정보가 수정되었습니다.");
  }

  @DeleteMapping
  public HttpResponse<Void> userDelete(@RequestBody(required = false) UserVo userVo) {
    return HttpResponse.toResponse(HttpStatus.NO_CONTENT, "테스트");
  }
}
