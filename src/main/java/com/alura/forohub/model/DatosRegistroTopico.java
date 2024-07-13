package com.alura.forohub.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "Debe ingresar un título para el tópico.")
        String titulo,

        @NotNull(message = "Debe ingresar el autor.")
        String autor,

        @NotBlank(message = "Debe ingresar el mensaje.")
        String mensaje,

        @NotNull(message = "Debe ingresar el curso.")
        String curso
) {
}
