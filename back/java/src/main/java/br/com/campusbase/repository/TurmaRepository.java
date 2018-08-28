package br.com.campusbase.repository;

import br.com.campusbase.model.Curso;
import br.com.campusbase.model.Matricula;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TurmaRepository extends CrudRepository<Curso, Long> {

    @Query("from Curso t where t.status in (:status) and (t.id in (select m.matriculaPK.cursoId from Matricula m "
            + "where m.matriculaPK.usuarioId = :usuario and m.situacao = :situacao)) order by t.titulo")
    List<Curso> buscarTurmasPorStatus(
            @Param("usuario") Long id,
            @Param("situacao") Matricula.Situacao matriculaSituacao,
            @Param("status") List<Curso.Status> status);
    
    @Query("from Curso t where t.status in (:status) and (t.id in (select m.matriculaPK.cursoId from Matricula m "
            + "where m.matriculaPK.usuarioId = :usuario)) order by t.titulo")
    List<Curso> buscarTurmasPorStatus(
            @Param("usuario") Long id,
            @Param("status") List<Curso.Status> status);    
    
    //@Query("select m from Matricula m join fetch m.curso where m.matriculaPK.cursoId = :cursoId and m.matriculaPK.usuarioId = :usuarioId")
    //Optional<Curso> findById(Long cursoId, Long usuarioId);

    @Query("from Curso where codigo = :codigo and status in (:status)")
    Optional<List<Curso>> buscarCursoPorCodigo(@Param("codigo") String codigoCurso, @Param("status") List<Curso.Status> status);
    
    @Query("from Curso where senha = :senha and status in (:status)")
    Optional<List<Curso>> buscarCursoPorSenha(@Param("senha") String senhaCurso, @Param("status") List<Curso.Status> status);
}
