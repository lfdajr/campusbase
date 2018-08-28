package br.com.campusbase.service;

import br.com.campusbase.IUserAuthentication;
import br.com.campusbase.exception.EntidadeInexistenteException;
import br.com.campusbase.exception.UsuarioNaoMatriculadoException;
import br.com.campusbase.model.Aula;
import br.com.campusbase.model.Curso;
import br.com.campusbase.model.Matricula;
import br.com.campusbase.model.Midia;
import br.com.campusbase.model.Usuario;
import br.com.campusbase.repository.AulaRepository;
import br.com.campusbase.repository.MatriculaRepository;
import br.com.campusbase.repository.MidiaRepository;
import br.com.campusbase.repository.TurmaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TurmaService {
    
    private final TurmaRepository turmaRep;
    private final MatriculaRepository matriculaRep;
    private final AulaRepository aulaRep;
    private final MidiaRepository midiaRep;
    
    @Autowired
    private IUserAuthentication auth;
    
    @Autowired
    private FileSystemStorageService fs;

    @Autowired
    public TurmaService(TurmaRepository rep, 
            MatriculaRepository matRep, 
            AulaRepository aulaRep,
            MidiaRepository midiaRep) {
        this.turmaRep = rep;
        this.matriculaRep = matRep;
        this.aulaRep = aulaRep;
        this.midiaRep = midiaRep;
    }
    
    public List<Matricula> buscarMinhasTurmas(Long idUsuario)
    {
        ArrayList<Curso.Status> param = new ArrayList<Curso.Status>(2);
        param.add(Curso.Status.NORMAL);
        param.add(Curso.Status.NORMAL_MODERADO);
        //return turmaRep.buscarTurmasPorStatus(idUsuario, Matricula.Situacao.CONFIRMADO, param);
        return this.matriculaRep.matriculasAluno(idUsuario, param);
    }
    
    public Matricula detalharTurma(Long cursoId) throws UsuarioNaoMatriculadoException {
        //Optional<Curso> curso = matriculaRep.findById(cursoId, auth.getPrincipal().getId()).map(Matricula::getCurso);
        Matricula matricula = matriculaRep.findById(cursoId, auth.getPrincipal().getId()).orElseThrow(() -> new UsuarioNaoMatriculadoException());
        
        /*List<Aula.Status> param = Arrays.asList(Aula.Status.PUBLICADA);
        
        if (ehProfessor(matricula))
        {
            param.add(Aula.Status.EM_ELABORACAO);
            aulaRep.buscarAulasPorStatus(cursoId, param);
        }
        
        List<Aula> aulas = aulaRep.buscarAulasPorStatus(cursoId, param);*/
        
        return matricula;
    }
    
    public Aula detalharAula(Long aulaId, Long cursoId) throws EntidadeInexistenteException {
        Aula aula = aulaRep.findByIds(aulaId, cursoId).orElseThrow(() -> new EntidadeInexistenteException());
        
        if (aula.getCurso().getId().longValue() != cursoId.longValue())
            throw new EntidadeInexistenteException();
        
        return aula;
    }
    
    private boolean ehProfessor(Matricula matricula) {
        if (matricula.getCurso().getProfessor().getId().longValue() == auth.getPrincipal().getId().longValue())
            return true;
        else
            return false;
    }

    public Curso novaTurma(Curso curso) {
        
        Usuario usuario = new Usuario(auth.getPrincipal().getId());
        
        curso.setProfessor(new Usuario(auth.getPrincipal().getId()));
        
        turmaRep.save(curso);
        
        List<Matricula> matriculaInicial = new ArrayList<Matricula>();
        
        Matricula matricula = new Matricula();
        matricula.setCurso(curso);
        matricula.setUsuario(usuario);
        matricula.setSituacao(Matricula.Situacao.CONFIRMADO);
        matriculaInicial.add(matricula);
        matricula.setMatriculaPK(usuario.getId(), curso.getId());
        
        matriculaRep.save(matricula);
        curso.setMatriculados(matriculaInicial);
        
        return curso;
    }
    
    public Optional<Aula> novaAula(Aula aula, Long cursoId) throws EntidadeInexistenteException
    {
        Usuario usuario = new Usuario(auth.getPrincipal().getId());
        Optional<Aula> aulaRetorno = Optional.empty();
        Curso curso = turmaRep.findById(cursoId).orElseThrow(EntidadeInexistenteException::new);
        
        if (curso.getProfessor().getId().longValue() == usuario.getId().longValue())
        {
            aula.setCurso(curso);
            aulaRep.save(aula);
            aulaRetorno = Optional.of(aula);
        }
        
        return aulaRetorno;
    }
    
    public Optional<Midia> uploadArquivoTurmaAula(MultipartFile file, Long cursoId, Long aulaId) throws Exception {
        fs.store(file);
        
        Optional<Midia> midiaRetorno = Optional.empty();
        
        Midia midia = new Midia();
        midia.setId(null);
        midia.setDestino(aulaId);
        midia.setTipoDestino(Midia.TipoDestino.CURSO_AULA);
        midia.setStatus(Midia.Status.NORMAL);
        midia.setRemetente(new Usuario(auth.getPrincipal().getId()));
        midia.setCaminho(file.getOriginalFilename());
        midia.setDescricao(file.getOriginalFilename());
        
        //TODO verificar se aula é do curso informado e se o usuário logado é o professor do curso para postar arquivo
 
        this.midiaRep.save(midia);
        
        midiaRetorno = Optional.of(midia);
        
        return midiaRetorno;
    }

    public Optional<List<Matricula>> buscarMatriculados(Long cursoId) {
        Optional<List<Matricula>> alunosMatriculados = this.matriculaRep.alunosMatriculados(cursoId);
        return alunosMatriculados;
    }

    public Matricula matricular(String senhaCurso) throws EntidadeInexistenteException {
        //Buscar curso informado pelo código
        ArrayList<Curso.Status> param = new ArrayList<Curso.Status>(3);
        param.add(Curso.Status.NORMAL);
        param.add(Curso.Status.NORMAL_MODERADO);
        param.add(Curso.Status.PUBLICA_SEM_MODERACAO);
        
        Optional<List<Curso>> _cursos = this.turmaRep.buscarCursoPorSenha(senhaCurso.toUpperCase(), param);
        
        _cursos.orElseThrow(() -> new EntidadeInexistenteException());
        
        Curso curso = _cursos.get().get(0);
        
        Matricula matricula = new Matricula(curso, new Usuario(auth.getPrincipal().getId()));
        this.matriculaRep.save(matricula);
        
        return matricula;
    }
    
}
