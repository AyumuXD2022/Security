package com.example.seguridadGrand.services;

import com.example.seguridadGrand.models.Usuario;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioService {



    Usuario save(Usuario usuario);
}
