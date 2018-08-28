package br.com.campusbase.endpoint;

import br.com.campusbase.IUserAuthentication;
import br.com.campusbase.exception.EntidadeInexistenteException;
import br.com.campusbase.model.Aula;
import br.com.campusbase.model.Mensagem;
import br.com.campusbase.service.FileSystemStorageService;
import br.com.campusbase.service.MensagemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensageriaRest {
    
    @Autowired
    private MensagemService mensagemService;
    
    @Autowired
    private IUserAuthentication auth;
    
    @Autowired
    private FileSystemStorageService fs;
    
    @GetMapping(value="/api/mensagem/{cursoId}")
    @ResponseBody
    public List<Mensagem> mensagensTurma(@PathVariable("cursoId") Long cursoId) 
            throws EntidadeInexistenteException {
            List<Mensagem> mensagens = this.mensagemService.buscarMensagensTurma(cursoId);
            return mensagens;
    }
    
    @PostMapping(value="/api/mensagem/{cursoId}")
    @ResponseBody
    public Mensagem postMensagemTurma(@RequestBody Mensagem mensagem) 
            throws EntidadeInexistenteException {
            this.mensagemService.salvarMensagemTurma(mensagem);
            return null;
    }    
}
