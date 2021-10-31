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
  
