package com.diden.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.diden.config.JwtTokenUtil;
import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;
import com.diden.utils.ParsingFromURL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserApiController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping(value = "/user/api/tour/image", produces = "application/json;application/xml; charset=UTF-8")
    public String tourImageApi() {
        String url = new String(
                "http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList?serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&arrange=A");
        ParsingFromURL parsingFromURL = new ParsingFromURL();
        return parsingFromURL.getParsingURL(url);
    }

    @GetMapping(value = "/user/test", produces = "application/json; charset=UTF-8")
    public String test() throws IOException {
        ParsingFromURL parsingJSONFromURL = new ParsingFromURL();
        return parsingJSONFromURL.getParsingURL(
                "https://api.odcloud.kr/api/15003416/v1/uddi:a635e6c7-82cf-4714-b002-c7cf4cb20121_201609071527?page=1&perPage=10&serviceKey=96EIT1koaTBt2OfbhSFR9PyKGOKS%2FAMqgeugwN1XT2QwjnE97ZiG1uszeNCPJquN2y2XIYC8GX8BlAcpvUcusw%3D%3D");
    }

    @GetMapping(value = "/user/list", produces = "application/json; charset=UTF-8")
    public String userList() {
        List<UserVo> userVoList = userService.userList();
        JsonObject userJsonList = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        userVoList.stream().forEach(userVoData -> {
            Gson gson = new Gson();
            JsonElement userJsonData = new JsonParser().parse(gson.toJson(userVoData));
            jsonArray.add(userJsonData);
        });

        userJsonList.add("data", jsonArray);
        return userJsonList.toString();
    }

    @GetMapping(value = "/user/tokentest")
    public void TokenTest(HttpServletRequest request) {
        // String token = request.getParameter("token");
        // log.info(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(););
        // log.info("================================ {}",
        // jwtTokenUtil.getUsernameFromToken(token));
        // log.info("================================ {}",
        // jwtTokenUtil.getExpirationDateFromToken(token));
        // <T> T test = jwtTokenUtil.getExpirationDateFromToken(token);
        // log.info("================================ {}",
        // jwtTokenUtil.getAllClaimsFromToken(token).().toString());
    }

    // Exception 어노테이션.
    // Json Exception 공통.
    // View Exception 공통.
    @PostMapping(value = "/user/login")
    public ResponseEntity<String> userData(@RequestBody(required = false) UserVo userVo, HttpServletRequest request,
            HttpSession session) throws Exception {
        try {
            JsonObject userResult = new JsonObject();
            log.info("Session Check ============================== {}", request.getSession());

            if (Objects.isNull(userVo)) {
                throw new Exception("파라미터 null");
            }

            UserVo userVoData = userService.userInfo(userVo);
            if (Objects.isNull(userVoData)) {
                return new ResponseEntity<>(errorMethod("login", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if (Objects.toString(userVo.getUserId(), "").equals(userVoData.getUserId())) {
                if (Objects.toString(userVo.getUserPassword(), "").equals(userVoData.getUserPassword())) {
                    String token = jwtTokenUtil.makeJwtToken(userVo);

                    userResult.addProperty("result", true);
                    userResult.addProperty("token", token);

                    return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(errorMethod("login", null), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(errorMethod("login", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(errorMethod("", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/user/logout")
    public ResponseEntity<String> userLogout(@RequestBody(required = false) UserVo userVo, HttpServletRequest request)
            throws Exception {
        try {
            JsonObject userResult = new JsonObject();
            log.info("Session Check ============================== {}", request.getSession());

            if (request.getSession() != null) {
                HttpSession session = request.getSession();
                session.invalidate();

                userResult.addProperty("result", true);
                return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
            } else {
                throw new Exception("잘못된 접근.");
            }

        } catch (Exception e) {
            return new ResponseEntity<>(errorMethod("", e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/user")
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
            } else {
                userService.userUpdate(userVo);
                userResult.addProperty("result", true);
                userResult.addProperty("put", "update");
            }

        } catch (Exception e) {
            userResult.addProperty("result", false);
            userResult.addProperty("error", e.getMessage());
            return new ResponseEntity<>(userResult.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userResult.toString(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user")
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
