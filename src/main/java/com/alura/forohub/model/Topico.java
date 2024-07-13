package com.alura.forohub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Tópico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String fechaCreacion;
    private Boolean status;
//    private String autor;
//    @Embedded //Dado que Topico y Respuesta usarían a usuario?
//    private Usuario autor;
    private String curso;
//    private Curso curso;
//    private List<String> respuestas;
//    private List<Respuesta> respuestas;
}
