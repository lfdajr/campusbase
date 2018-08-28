package br.com.campusbase.endpoint;

import br.com.campusbase.IUserAuthentication;
import br.com.campusbase.exception.EntidadeInexistenteException;
import br.com.campusbase.model.Agenda;
import br.com.campusbase.service.AgendaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgendaRest {
    
    @Autowired
    private AgendaService agendaService;
    
    @Autowired
    private IUserAuthentication auth;
    
    @GetMapping(value="/api/agenda")
    @ResponseBody
    public List<Agenda> buscarAgenda() {
            
        List<Agenda> resultado = this.agendaService.buscarAgenda();
        return resultado;
        //Logger.getLogger(TurmaRest.class.getName()).log(Level.SEVERE, null, ex);
    }
    
   
}
