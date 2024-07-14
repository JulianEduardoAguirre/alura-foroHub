package com.alura.forohub.controller;

import com.alura.forohub.model.DatosActualizarTopico;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable(name = "id") Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        boolean isPresent = topicoRepository.existsById(id);

        if (isPresent) {
            boolean isRepeating = topicoRepository.existsByTituloAndMensaje(datosActualizarTopico.titulo(), datosActualizarTopico.mensaje());
            if(isRepeating) {
                System.out.println("Ya existe ese tópico con ese mensaje.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe ese tópico con ese título y ese mensaje.");
            } else {
                Topico topico = topicoRepository.getReferenceById(id);
                topico.actualizarDatos(datosActualizarTopico);
                return ResponseEntity.ok(new DatosActualizarTopico(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getCurso()));
            }
        } else {
            System.out.println("No existe un tópico con ese id en la base de datos.");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable(name = "id") Long id){
        boolean isPresent = topicoRepository.existsById(id);

        if (isPresent) {
            topicoRepository.deleteById(id);
            System.out.println("Tópico eliminado");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }




//    @DeleteMapping("/{id}")
//    @Transactional
//    public void desabilitarTopico(@PathVariable(name = "id") Long id){
//        Optional<Topico> consultaTopico = topicoRepository.findById(id);
//
//        if (consultaTopico.isPresent()) {
//            Topico topico = consultaTopico.get();
//            topico.desabilitar();
//        }
//    }

//    @GetMapping("/ultimos")
//    public List<DatosListadoTopico> listarUltimos() {
//        return topicoRepository.findTop10ByFechaCreacionOrderByFechaCreacionDesc().
//                stream().map(DatosListadoTopico::new).collect(Collectors.toList());
//    }

}
