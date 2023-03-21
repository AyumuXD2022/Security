package com.example.seguridadGrand.services;

import com.example.seguridadGrand.models.Usuario;
import com.example.seguridadGrand.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario save(Usuario usuario){
        String pdw = this.passwordEncoder.encode(usuario.getPdw());
        usuario.setRol("ROLE_USER");
        usuario.setPdw(pdw);
        return this.usuarioRepository.save(usuario);
    }
}
