package br.com.campusbase.endpoint;

import br.com.campusbase.IUserAuthentication;
import br.com.campusbase.model.Usuario;
import br.com.campusbase.service.FileSystemStorageService;
import br.com.campusbase.service.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioRest {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private IUserAuthentication auth;
    
    @Autowired
    private FileSystemStorageService fs;
    
    @PostMapping(value="/api/newUser")
    public @ResponseBody Usuario novoUsuario(@RequestBody UsuarioDTO usuario) 
        throws IOException, ServletException {
        
        return usuarioService.novoUsuario(usuario.toEntity());
    }    
    
   
}

class UsuarioDTO {
    private String nome;
    private String senha;
    private String email;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        
        return usuario;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
