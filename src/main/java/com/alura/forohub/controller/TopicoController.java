package com.alura.forohub.controller;

import com.alura.forohub.model.DatosRegistroTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico) {

        var repetido = topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());

        if (repetido) {
            System.out.println("Tópico y mensaje repetidos.");
        } else {
            System.out.println("Nuevo tópico salvado en la base de datos");
            topicoRepository.save(new Topico(datosRegistroTopico));
        }

    }
}
