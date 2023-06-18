package com.diden.demo.api.info.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Tag(name = "이미지 경로 Controller", description = "서버에 저장된 이미지 경로를 확인하는 Controller 입니다.")
@Slf4j
@Controller
public class InfoController {

  @GetMapping("/")
  @ResponseBody
  public String emptyPage() {
    return "Info Page";
  }

  @GetMapping("/info")
  public String info(HttpServletRequest request, Model model) {
    try {
      List<String> logo =
          Files.list(Path.of("src/main/resources/static/img/logo"))
              .filter(o -> o.toFile().isFile())
              .map(Path::toString)
              .map(
                  o ->
                      request.getServerName()
                          + ":"
                          + request.getServerPort()
                          + "/"
                          + o.substring(26))
              .collect(Collectors.toList());

      List<String> mainTour =
          Files.list(Path.of("src/main/resources/static/img/main/tour"))
              .filter(o -> o.toFile().isFile())
              .map(Path::toString)
              .map(
                  o ->
                      request.getServerName()
                          + ":"
                          + request.getServerPort()
                          + "/"
                          + o.substring(26))
              .collect(Collectors.toList());

      List<String> icon =
          Files.list(Path.of("src/main/resources/static/img/icon"))
              .filter(o -> o.toFile().isFile())
              .map(Path::toString)
              .map(
                  o ->
                      request.getServerName()
                          + ":"
                          + request.getServerPort()
                          + "/"
                          + o.substring(26))
              .collect(Collectors.toList());

      model.addAttribute("logo", logo);
      model.addAttribute("main_tour", mainTour);
      model.addAttribute("icon", icon);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "info";
  }
}
