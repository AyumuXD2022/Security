package com.example.seguridadGrand.auth;

import com.example.seguridadGrand.models.Usuario;
import com.example.seguridadGrand.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pdw = authentication.getCredentials().toString();
        Usuario usuario = this.usuarioRepository.findByEmail(username);
        if(usuario != null){
            if(this.passwordEncoder.matches(pdw,usuario.getPassword())){
                return new UsernamePasswordAuthenticationToken(usuario.getUsername(),usuario.getPassword(),usuario.getAuthorities());
            }else {
                throw new BadCredentialsException("Contraseña invalidad");
            }
        }else {
            throw new BadCredentialsException("No user regitrado");

        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
