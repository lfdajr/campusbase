package br.com.campusbase.service;

import br.com.campusbase.IUserAuthentication;
import br.com.campusbase.model.Agenda;
import br.com.campusbase.model.Curso;
import br.com.campusbase.model.Usuario;
import br.com.campusbase.repository.AgendaRepository;
import br.com.campusbase.repository.AulaRepository;
import br.com.campusbase.repository.MatriculaRepository;
import br.com.campusbase.repository.MidiaRepository;
import br.com.campusbase.repository.TurmaRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {
    
    private final TurmaRepository turmaRep;
    private final MatriculaRepository matriculaRep;
    private final AgendaRepository agendaRep;
    private final AulaRepository aulaRep;
    private final MidiaRepository midiaRep;
    
    @Autowired
    private IUserAuthentication auth;
    
    @Autowired
    public AgendaService(TurmaRepository rep, 
            MatriculaRepository matRep, 
            AgendaRepository agendaRep,
            AulaRepository aulaRep,
            MidiaRepository midiaRep) {
        this.turmaRep = rep;
        this.matriculaRep = matRep;
        this.aulaRep = aulaRep;
        this.midiaRep = midiaRep;
        this.agendaRep = agendaRep;
    }
    
    public List<Agenda> buscarAgenda()
    {
        ArrayList<Curso.Status> param = new ArrayList<Curso.Status>(2);
        param.add(Curso.Status.NORMAL);
        param.add(Curso.Status.NORMAL_MODERADO);
        
        Usuario usuario = new Usuario(auth.getPrincipal().getId());
        
        return this.agendaRep.buscarAgendaUsuario(usuario.getId(), param, new Date());
    }
    
    

}
