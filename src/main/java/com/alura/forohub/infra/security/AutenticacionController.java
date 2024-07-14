package com.alura.forohub.infra.security;

import com.alura.forohub.model.DatosAutenticacionUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity autenticarUsuario(DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication token = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.nombre(),
                datosAutenticacionUsuario.contrasenia());

        authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }


}
