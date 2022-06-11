package com.diden.demo;

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
            List<String> logo = Files
                    .list(Path.of("src/main/resources/static/img/logo"))
                    .filter(o -> o.toFile().isFile())
                    .map(Path::toString)
                    .map(o -> request.getServerName() + ":" + request.getServerPort() + "/" + o.substring(26))
                    .collect(Collectors.toList());

            List<String> mainTour = Files
                    .list(Path.of("src/main/resources/static/img/main/tour"))
                    .filter(o -> o.toFile().isFile())
                    .map(Path::toString)
                    .map(o -> request.getServerName() + ":" + request.getServerPort() + "/" + o.substring(26))
                    .collect(Collectors.toList());

            model.addAttribute("logo", logo);
            model.addAttribute("main_tour", mainTour);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }


        return "info";
    }
}
