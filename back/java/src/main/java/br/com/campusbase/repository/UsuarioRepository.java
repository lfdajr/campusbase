package br.com.campusbase.repository;

import br.com.campusbase.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("from Usuario where email=:username")
    public Optional<Usuario> findByUsername(@Param("username") String username);
    
    @Query("from Usuario where email=:username and senha = SHA1(:senha)")
    public Optional<Usuario> findByUsername(@Param("username") String username, @Param("senha") String senha);    

}
