package com.alura.forohub.controller;

import com.alura.forohub.model.DatosListadoTopico;
import com.alura.forohub.model.DatosRegistroTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {

        var topicoExiste = topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());

        if (topicoExiste) {
            System.out.println("Tópico y mensaje repetidos.");
        } else {
            System.out.println("Nuevo tópico salvado en la base de datos");
            topicoRepository.save(new Topico(datosRegistroTopico));
        }

    }

    @GetMapping
    public Page<DatosListadoTopico> listadoTopicos(@PageableDefault(size = 25, sort = "creado", direction = Sort.Direction.DESC) Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public DatosListadoTopico buscarTopicoPorId(@PathVariable(name = "id", required = true) Long id) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(id);

        if(topicoBuscado.isPresent()) {
            System.out.println("Tópico encontrado");
            return new DatosListadoTopico(topicoBuscado.get());
        } else {
            System.out.println("No se encontró el tópico");
            return null;
        }


    }

//    @GetMapping("/ultimos")
//    public List<DatosListadoTopico> listarUltimos() {
//        return topicoRepository.findTop10ByFechaCreacionOrderByFechaCreacionDesc().
//                stream().map(DatosListadoTopico::new).collect(Collectors.toList());
//    }

}
