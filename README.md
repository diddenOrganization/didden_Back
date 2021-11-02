# didden_Back
This is didden projects for Back-End

# 2021-10-30(토) 현재 진행상황

~~~java
RequestMapping(/user/list)
~~~
~~~sql
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
~~~
  
> 데이터가 정상 출력되는지만 작업해놓은 상태.   
> API(Json) 형식으로 데이터를 전달하느 부분은 미완료.
  
# 2021-11-02(화) 현재 진행상황

~~~JAVA
@GetMapping(value = "/user/list", produces = "application/json; charset=UTF-8")
~~~
> 사용자 리스트 정보
--------------------------------------------------------------
~~~java
@PostMapping(value = "/user/login", produces = "application/json; charset=UTF-8")
~~~
> 로그인 URL

~~~JAVA
userResult.addProperty("result", true);
~~~
> 로그인 성공시 

~~~JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
~~~
> 로그인 실패시
--------------------------------------------------------------
~~~JAVA
@PutMapping(value = "/user", produces = "application/json; charset=UTF-8")
~~~
> 회원정보 Insert/Update URL

~~~JAVA
userResult.addProperty("result", true);
userResult.addProperty("put", "insert");
~~~
> 회원정보 Insert 성공시

~~~JAVA
userResult.addProperty("result", true);
userResult.addProperty("put", "update");
~~~
> 회원정보 Update 성공시

~~~JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
~~~
> 회원정보 Insert/Update 실패시
--------------------------------------------------------------
~~~JAVA
@DeleteMapping(value = "/user", produces = "application/json; charset=UTF-8")
~~~
> 사용자 정보 삭제 URL

~~~JAVA
userResult.addProperty("result", true);
~~~
> 사용자 정보 삭제 성공시

~~~JAVA
userResult.addProperty("result", false);
userResult.addProperty("error", e.getMessage());
~~~
> 사용자 정보 삭제 실패시
--------------------------------------------------------------
