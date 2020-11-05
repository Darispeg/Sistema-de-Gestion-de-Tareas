package com.school.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @RequestMapping("/hello")
    public String hello(){
        return "Inicio del Proyecto 05 de Noviembre de 2020";
    }
}