package com.alura.forohub.model;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime creado,
        boolean estado,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCreado(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
