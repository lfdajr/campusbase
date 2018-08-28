package br.com.campusbase.repository;

import br.com.campusbase.model.Aula;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AulaRepository  extends CrudRepository<Aula, Long> {
    @Query("from Aula a where curso.id = :curso and status in (:status) order by dataHora desc")
    List<Aula> buscarAulasPorStatus(
            @Param("curso") Long id,
            @Param("status") List<Aula.Status> status);
    
    @Query("from Aula a join fetch a.arquivos where a.id = :id and a.curso.id = :cursoId")
    Optional<Aula> findByIds(
                @Param("id") Long id,
                @Param("cursoId") Long cursoId
    );
    
    //Verificar por que está sendo gerado vários JOINs para buscar os arquivos (MIDIA) da classe aula
}
