package com.example.seguridadGrand.controllers;

import com.example.seguridadGrand.models.Usuario;
import com.example.seguridadGrand.repositories.UsuarioRepository;
import com.example.seguridadGrand.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioControllers {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody @Valid Usuario usuario){
        Map<String,Object> response = new HashMap<>();
        Usuario usuarioNew = usuarioService.save(usuario);

        response.put("mensaje","Given user details are successfully registered");
        response.put("usuario",usuarioNew);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Usuario getUserDetailsAfterLogin(Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
        if (usuario != null) {
            return usuario;
        } else {
            return null;
        }

    }
}
