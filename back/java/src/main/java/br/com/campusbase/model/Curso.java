package br.com.campusbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "CURSO")
public class Curso implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    
    @Basic(optional = false)
    @Column(name = "NOME_DISCIPLINA")
    private String nomeDisciplina;
    
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacaoList;
    
    @JoinColumn(name = "PROFESSOR", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario professor;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Aula> aulas;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Matricula> matriculados;
    
    @Enumerated
    @Column(name = "STATUS")
    private Status status;
    
    @Column(name = "ORDENACAO")
    private int ordenacao;
    
    @Column(name = "SENHA")
    private String senha;
    
    @Transient
    private List<Mensagem> mensagens;

    public Curso() {
        this.dataHoraCriacao = new Date();
        descricao = "";
        status = Status.NORMAL;
        ordenacao = 0;
    }

    public Curso(Long id) {
        this.id = id;
        this.dataHoraCriacao = new Date();
    }

    public Curso(Long id, String codigo, String titulo, Date dataHoraCriacao) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo.toUpperCase();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public List<Matricula> getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(List<Matricula> matriculaList) {
        this.matriculados = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Curso[ id=" + id + " ]";
    }

    /**
     * @return the nomeDisciplina
     */
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    /**
     * @param nomeDisciplina the nomeDisciplina to set
     */
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the mensagens
     */
    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    /**
     * @param mensagens the mensagens to set
     */
    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    /**
     * @return the ordenacao
     */
    public int getOrdenacao() {
        return ordenacao;
    }

    /**
     * @param ordenacao the ordenacao to set
     */
    public void setOrdenacao(int ordenacao) {
        this.ordenacao = ordenacao;
    }
    
    public enum Status
    {
        NORMAL, //ALUNO entra para depois ser moderado
        NORMAL_MODERADO, //ALUNO pede para entrar na turma primeiro
        PUBLICA_SEM_MODERACAO, //TURMA PUBLICA, NÃO PRECISA NEM ESTAR LOGADO PARA VER
        RESERVADO3,
        RESERVADO4,
        RESERVADO5,
        RESERVADO6,
        RESERVADO7,
        RESERVADO8,
        RESERVADO9,
        ENCERRADO, //CURSO ESTÁ ENCERRADO
        DELETADO //11
    }

    /**
     * @return the aulas
     */
    public List<Aula> getAulas() {
        return aulas;
    }

    /**
     * @param aulas the aulas to set
     */
    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
