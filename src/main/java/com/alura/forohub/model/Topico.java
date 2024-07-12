package com.alura.forohub.model;

import java.util.List;

public class Topico {
    private Long id;
    private String titulo;
    private String mensaje;
    private String fechaCreacion;
    private Boolean status;
    private Usuario autor;
    private Curso curso;
    private List<Respuesta> respuestas;
}
