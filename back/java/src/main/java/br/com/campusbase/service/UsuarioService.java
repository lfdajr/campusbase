package br.com.campusbase.service;

import br.com.campusbase.model.Usuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.campusbase.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService {
    
    private final UsuarioRepository repositorio;
    
    @Autowired
    public UsuarioService(UsuarioRepository userRepository) {
        this.repositorio = userRepository;
    }

    public UsuarioRepository getUserRepository() {
        return repositorio;
    }

    public Optional<Usuario> getByUsername(String username) {
        return this.repositorio.findByUsername(username);
    }
    
    public Optional<Usuario> getByUsername(String username, String senha) {
    return this.repositorio.findByUsername(username, senha);
    }

    public Usuario novoUsuario(Usuario usuario) {
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        usuario.setNomeCompleto(usuario.getNome());
        usuario.setTipo(Usuario.Tipo.ALUNO);
        String senhaEncriptada = bpe.encode(usuario.getSenha());
        usuario.setSenha(senhaEncriptada);
        return this.repositorio.save(usuario);
    }
    
}
