package com.alura.forohub.controller;

import com.alura.forohub.model.DatosRegistroTopico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @PostMapping
    public void registrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico) {
        System.out.println("Exito");
        System.out.println(datosRegistroTopico);
        System.out.println(datosRegistroTopico.mensaje());
    }
}
