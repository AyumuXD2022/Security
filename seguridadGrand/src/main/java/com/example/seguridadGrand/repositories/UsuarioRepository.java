package com.example.seguridadGrand.repositories;

import com.example.seguridadGrand.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario  findByEmail(String email);
}
