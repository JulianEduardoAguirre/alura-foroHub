package com.alura.forohub.model;

import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable //dado que lo usan otras clases??
public class Usuario {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasenia;
    private List<Perfil> perfiles;
}
