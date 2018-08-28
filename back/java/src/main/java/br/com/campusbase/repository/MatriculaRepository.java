package br.com.campusbase.repository;

import br.com.campusbase.model.Curso;
import br.com.campusbase.model.Matricula;
import br.com.campusbase.model.MatriculaPK;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatriculaRepository  extends CrudRepository<Matricula, MatriculaPK> {

    @Query("from Matricula m join fetch m.curso join fetch m.usuario join fetch m.curso.aulas where m.matriculaPK.cursoId = :cursoId and m.matriculaPK.usuarioId = :usuarioId")
    Optional<Matricula> findById(@Param("cursoId") Long cursoId, @Param("usuarioId") Long usuarioId);

    @Query("from Matricula m join fetch m.curso join fetch m.usuario where m.matriculaPK.cursoId = :cursoId")
    Optional<List<Matricula>> alunosMatriculados(@Param("cursoId") Long cursoId);
    
    @Query("from Matricula m join fetch m.curso where m.matriculaPK.usuarioId = :usuarioId and m.curso.status in (:status)")
    List<Matricula> matriculasAluno(@Param("usuarioId") Long usuarioId, @Param("status") List<Curso.Status> status);
}
