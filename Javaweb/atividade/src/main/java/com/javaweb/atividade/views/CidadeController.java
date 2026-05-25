package com.javaweb.atividade.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CidadeController {
    
    @GetMapping("/")
    public String index() {
        return "base.html";
    }

}
