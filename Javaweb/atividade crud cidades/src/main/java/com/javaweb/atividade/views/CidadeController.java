package com.javaweb.atividade.views;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.javaweb.atividade.models.Cidade;


@Controller
public class CidadeController {
    
    @GetMapping("/")
    public String index(Model memoria) {

        var cidade = Set.of(
            new Cidade("São Paulo", "SP"),
            new Cidade("Rio de Janeiro", "RJ"),
            new Cidade("Belo Horizonte", "MG")
        );
        memoria.addAttribute("listarCidades", cidade);
        return "base";
    }

}
