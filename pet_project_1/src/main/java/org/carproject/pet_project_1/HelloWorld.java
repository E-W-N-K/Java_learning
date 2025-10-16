package org.carproject.pet_project_1;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
public class HelloWorld {
    @GetMapping("/hello")
    public String sayHello() {
        return "Привет от Spring Boot!";
    }
    @GetMapping("/")
    public String mainPage() {
        return "Main page";
    }
    @GetMapping("/api/info")
    public IdeaTemplate getInfoAboutOurApi() {
        return new IdeaTemplate(
                "Я люблю чебуреки",
                "Чебуреки очень вкусные",
                LocalDate.now().toString()
        );
    }


}
