# didden_Back

This is didden projects for Back-End

# 2021-12-04(토) 현재 진행상황

## 1. 관광 데이터

```java
관광정보목록 URL 변경.
@GetMapping(value = "/user/test") -> @GetMapping(value = "/tour/api/info")

관광이미지 목록 URL 변경.
@GetMapping(value = "/tour/api/image") -> @GetMapping(value = "/tour/api/info/image")
```

## 2. 로그인/로그아웃 토큰 기능 추가

```java
2.1. 로그인시 파라미터 전달.
@PostMapping(value = "/user/login")

userResult.addProperty("result", true);
userResult.addProperty("token_acc", token.getAccessJwsToken());   // 억세스 토큰 발급.
userResult.addProperty("token_ref", token.getRefreshJwsToken());  // 리프레쉬 토큰 발급.

userVo.setUserRefreshToken(token.getRefreshJwsToken());
userService.userRefTokenUpdate(userVo);                           // 계정별 리프레쉬 토큰 저장.

2.2. 로그아웃시 리프레쉬 토큰 삭제.
// 아이디와 패스워드 값을 전달받아 해당 계정의 리프레쉬 토큰 값을 가져옴.
@PostMapping(value = "/user/logout")

if (!Objects.toString(userVo.getUserId(), "").equals("") && !Objects.toString(userVo.getUserPassword(), "").equals("")) {

UserVo getUserVo = userService.userInfo(userVo);
getUserVo.setUserRefreshToken("");                                // 리프레쉬 토큰 공백처리.
userService.userUpdate(getUserVo);

```

## 3. 토큰.

## 4. 파싱.

# 2021-11-04(목) 현재 진행상황

## 1. 관광 데이터 테스트 URL

```java
@GetMapping(value = "/user/test")
```

> 관광 데이터 10건 호출.

```java
rootobj = root.getAsJsonObject(); // 관광데이터
rootobj.addProperty("result", true);
```

> URL 호출 성공시

```java
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> URL 호출 실패시

# 2021-11-02(화) 현재 진행상황

## 1. 사용자 정보 VO

UserVo.java

```java
package com.diden.user.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserVo {
    private String userId = null;
    private String userName = null;
    private String userPassword = null;
    private String userNickname = null;
    private String userBirthday = null;
    private String userGender = null;
    private String userEmail = null;
    private String userPhoneNumber = null;
    private String userCreateDate = null;
    private String userUpdateDate = null;
    private String userPrivacyConsent = null;
}
```

> 파라미터 Key 값

```json
{
  "userId": "user1",
  "userName": "test",
  "userPassword": "test",
  "userNickname": "test",
  "userBirthday": "test",
  "userGender": "test",
  "userEmail": "test",
  "userPhoneNumber": "test",
  "userCreateDate": "test",
  "userUpdateDate": "test",
  "userPrivacyConsent": "1"
}
```

> Json 형식으로 데이터를 전달해줘야 합니다.

---

## 2. 사용자 정보 리스트

/user-list.json -> /user/list URL 변경.

```JAVA
@GetMapping(value = "/user/list", produces = "application/json; charset=UTF-8")
```

> 사용자 리스트 정보

---

## 3. 사용자 로그인

```java
@PostMapping(value = "/user/login", produces = "application/json; charset=UTF-8")
```

> 로그인 URL

```JAVA
userResult.addProperty("result", true);
```

> 로그인 성공시

```JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> 로그인 실패시

---

## 4. 사용자 등록 및 수정

```JAVA
@PutMapping(value = "/user", produces = "application/json; charset=UTF-8")
```

> 회원정보 Insert/Update URL

```JAVA
userResult.addProperty("result", true);
userResult.addProperty("put", "insert");
```

> 회원정보 Insert 성공시

```JAVA
userResult.addProperty("result", true);
userResult.addProperty("put", "update");
```

> 회원정보 Update 성공시

```JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> 회원정보 Insert/Update 실패시

---

## 5. 사용자 정보 삭제

```JAVA
@DeleteMapping(value = "/user", produces = "application/json; charset=UTF-8")
```

> 사용자 정보 삭제 URL

```JAVA
userResult.addProperty("result", true);
```

> 사용자 정보 삭제 성공시

```JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
```

> 사용자 정보 삭제 실패시

---

# 2021-10-30(토) 현재 진행상황

```java
RequestMapping(/user/list)
```

```sql
SELECT USER_ID
     , USER_NAME
     , USER_PASSWORD
     , USER_NICKNAME
     , USER_BIRTHDAY
     , USER_GENDER
     , USER_EMAIL
     , USER_PHONE_NUMBER
     , USER_CREATE_DATE
     , USER_UPDATE_DATE
     , USER_PRIVACY_CONSENT
  FROM TB_USER
```

> 데이터가 정상 출력되는지만 작업해놓은 상태.  
> API(Json) 형식으로 데이터를 전달하느 부분은 미완료.
