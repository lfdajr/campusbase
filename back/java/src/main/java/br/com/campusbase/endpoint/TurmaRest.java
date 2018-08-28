package br.com.campusbase.endpoint;

import br.com.campusbase.IUserAuthentication;
import br.com.campusbase.exception.EntidadeInexistenteException;
import br.com.campusbase.exception.UsuarioNaoMatriculadoException;
import br.com.campusbase.model.Aula;
import br.com.campusbase.model.Curso;
import br.com.campusbase.model.Matricula;
import br.com.campusbase.model.Midia;
import br.com.campusbase.service.FileSystemStorageService;
import br.com.campusbase.service.TurmaService;
import com.svlada.security.model.UserContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TurmaRest {
    
    @Autowired
    private TurmaService turmaService;
    
    @Autowired
    private IUserAuthentication auth;
    
    @Autowired
    private FileSystemStorageService fs;
    
    @GetMapping(value="/api/turma/{cursoId}/aula/{id}")
    @ResponseBody
    public Aula detalharAula(@PathVariable("id") Long id, 
            @PathVariable("cursoId") Long cursoId) 
            throws EntidadeInexistenteException {
            Aula aula = turmaService.detalharAula(id, cursoId);
            return aula;
            //Logger.getLogger(TurmaRest.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    @GetMapping(value="/api/turma/{id}")
    @ResponseBody
    public Matricula detalharTurma(@PathVariable("id") Long id) {
        try {
            Matricula matricula = turmaService.detalharTurma(id);
            return matricula;
        } catch (UsuarioNaoMatriculadoException ex) {
            Logger.getLogger(TurmaRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @GetMapping(value="/api/turmas")
    public @ResponseBody List<Matricula> minhasTurmas() 
        throws IOException, ServletException {
        
        /*UserContext uc = auth.getPrincipal();
        List<Curso> ret = turmaService.buscarMinhasTurmas(uc.getId());
        for (Curso c : ret)
            c.setAulas(null);
        return ret;*/
        
        UserContext uc = auth.getPrincipal();
        List<Matricula> ret = turmaService.buscarMinhasTurmas(uc.getId());
        return ret;        
    }    
    
    @PostMapping(value="/api/turma")
    public @ResponseBody Curso novaTurma(@RequestBody Curso curso) 
        throws IOException, ServletException {
        UserContext uc = auth.getPrincipal();
        
        return turmaService.novaTurma(curso);
    }    
    
    @PostMapping(value="/api/turma/{id}/aula")
    public @ResponseBody Aula novaAula(@RequestBody Aula aula, @PathVariable("id") Long cursoId) 
        throws IOException, ServletException, EntidadeInexistenteException, Exception {
        //UserContext uc = auth.getPrincipal();        
        return turmaService.novaAula(aula, cursoId).orElseThrow(() -> new Exception("Não foi possivel criar aula"));
    }    
    
    @PostMapping(value="/api/turma/{cursoId}/aula/{aulaId}/upload")
    public @ResponseBody Midia enviarArquivosParaTurma(@RequestParam("file") MultipartFile file, 
            @PathVariable("cursoId") Long cursoId, @PathVariable("aulaId") Long aulaId) 
        throws IOException, ServletException, EntidadeInexistenteException, Exception {

        return this.turmaService.uploadArquivoTurmaAula(file, cursoId, aulaId).orElseThrow(() -> new Exception("Não foi possivel gravar arquivo"));
    }    

    @GetMapping("/api/turma/aula/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws FileNotFoundException {

        Resource file = fs.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

   @PostMapping(value="/api/turma/{id}/configuracoes")
    public @ResponseBody List<Matricula> matriculados(@PathVariable("id") Long cursoId) 
        throws IOException, ServletException, EntidadeInexistenteException, Exception {
        //UserContext uc = auth.getPrincipal();        
        return turmaService.buscarMatriculados(cursoId).orElseThrow(() -> new Exception("Não foi possivel criar aula"));
    }    
    
   @PutMapping(value="/api/turma/{id}")
    public @ResponseBody Matricula matricular(@PathVariable("id") String senhaCurso) 
        throws IOException, ServletException, EntidadeInexistenteException, Exception {
        return turmaService.matricular(senhaCurso);
    }        
    
    
    /*@GetMapping(value = "/api/me")
    public @ResponseBody
    UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }*/
    
}
