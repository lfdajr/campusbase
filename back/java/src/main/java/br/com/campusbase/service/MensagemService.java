package br.com.campusbase.service;

import br.com.campusbase.IUserAuthentication;
import br.com.campusbase.model.Matricula;
import br.com.campusbase.model.Mensagem;
import br.com.campusbase.model.Usuario;
import br.com.campusbase.repository.MensagemRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {
    
    private final MensagemRepository msgRep;
    
    @Autowired
    private IUserAuthentication auth;
    
    @Autowired
    private FileSystemStorageService fs;
    

    @Autowired
    public MensagemService(MensagemRepository r) {
        this.msgRep = r;
    }
    
    public List<Mensagem> buscarMensagensTurma(Long cursoId)
    {
        return msgRep.buscarMensagensTurma(cursoId, Arrays.asList(Mensagem.Tipo.MENSAGEM_PRIVADA, Mensagem.Tipo.MENSAGEM_GRUPO_FORUM));
    }
    
    public void salvarMensagemTurma(Mensagem mensagem) {
        mensagem.setTipo(Mensagem.Tipo.MENSAGEM_FORUM);
        mensagem.setRemetente(new Usuario(auth.getPrincipal().getId()));
        
        msgRep.save(mensagem);
    }
    

}
