package com.javaweb.atividade.views;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.atividade.models.Cidade;

import jakarta.validation.Valid;


@Controller
public class CidadeController {

    private Set<Cidade> cidades;
    
    public CidadeController(){
        cidades = new HashSet<>();
    }

    @GetMapping("/")
    public String index(Model memoria) {
        memoria.addAttribute("listarCidades", cidades);
        return "base";
    }

    @PostMapping("/criar")
    public String criar(@Valid Cidade cidade, BindingResult validacao){
        if (validacao.hasErrors()) {
            validacao.getFieldErrors().forEach(error ->
                                        System.out.println(
                                            String.format("O atributo %s emitiu a seguinte mensagem de erro %s",
                                            error.getField(),
                                            error.getDefaultMessage()
                                                )
                                            )
                                        );

        } else {
            cidades.add(cidade);
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(
        @RequestParam String nome,
        @RequestParam String uf
    ) {
        cidades.removeIf(cidadeAtual -> 
            cidadeAtual.getNome().equals(nome) &&
            cidadeAtual.getUf().equals(uf)
        );

        return "redirect:/";
    }

    @GetMapping("/preparaAlterar")
    public String preparaAlterar(
        @RequestParam String nome,
        @RequestParam String uf,
        Model memoria
    ){
        var cidadeAtual = cidades
                            .stream()
                            .filter(cidade -> 
                                            cidade.getNome().equals(nome) && 
                                            cidade.getUf().equals(uf)
                            ).findAny();

            if(cidadeAtual.isPresent()){
                memoria.addAttribute("cidadeAtual", cidadeAtual.get());
                memoria.addAttribute("listarCidades", cidades);
            }

        return "base";
    }

    @PatchMapping("/alterar")
    public String alterar(
        @RequestParam String nomeAtual,
        @RequestParam String ufAtual,
        @Valid Cidade cidade
    ) {
        cidades.removeIf(cidadeAtual -> 
                                cidadeAtual.getNome().equals(nomeAtual) && 
                                cidadeAtual.getUf().equals(ufAtual));
        cidades.add(cidade);

        return "redirect:/";
    }

}