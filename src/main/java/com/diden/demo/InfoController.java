package com.diden.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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

//@RestController
@Controller
public class InfoController {
    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/")
    @ResponseBody
    public String emptyPage() {
        return "Info Page";
    }

    @GetMapping("/info")
    public String info(HttpServletRequest request, Model model) throws IOException {
        List<String> logo = Files
                .list(Path.of("src/main/resources/static/img/logo"))
                .map(Path::toString)
                .map(o -> request.getServerName() + ":" + request.getServerPort() + "/" + o.substring(26))
                .collect(Collectors.toList());

        List<String> mainTour = Files
                .list(Path.of("src/main/resources/static/img/main/tour"))
                .map(Path::toString)
                .map(o -> request.getServerName() + ":" + request.getServerPort() + "/" + o.substring(26))
                .collect(Collectors.toList());

        model.addAttribute("logo", logo);
        model.addAttribute("main_tour", mainTour);

        return "info";
    }
}
