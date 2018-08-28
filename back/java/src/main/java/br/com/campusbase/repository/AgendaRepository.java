package br.com.campusbase.repository;

import br.com.campusbase.model.Agenda;
import br.com.campusbase.model.Curso;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {

    @Query("from Agenda a where a.destino in (select m.matriculaPK.cursoId from Matricula m join m.curso join m.usuario "
            + "where m.curso.status in (:status) and m.usuario.id = :usuario) and ("
            + "(a.dataHoraInicio >= :hoje and a.dataHoraFim is null ) OR (a.dataHoraInicio <= :hoje and a.dataHoraFim >= :hoje)) "
            + "order by a.dataHoraInicio")
    List<Agenda> buscarAgendaUsuario(@Param("usuario") Long id, @Param("status") List<Curso.Status> status, @Param("hoje") Date hoje);
}
