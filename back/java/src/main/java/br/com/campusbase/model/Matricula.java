package br.com.campusbase.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "MATRICULA")
public class Matricula implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected MatriculaPK matriculaPK;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;
    
    @Basic(optional = false)
    @Enumerated
    @Column(name = "SITUACAO")
    private Situacao situacao;
    
    @Column(name = "NOTA_FINAL")
    private BigDecimal notaFinal;
    
    @Column(name = "CODIGO")
    private String codigo;
    
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso curso;
    
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Matricula() {
        this.dataHoraCriacao = new Date();
    }

    public Matricula(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public Matricula(MatriculaPK matriculaPK, Date dataHoraCriacao, Situacao situacao) {
        this.matriculaPK = matriculaPK;
        this.dataHoraCriacao = dataHoraCriacao;
        this.situacao = situacao;
    }

    public Matricula(Curso curso, Usuario usuario) {
        this();
        this.curso = curso;
        this. usuario = usuario;
        this.matriculaPK = new MatriculaPK(usuario.getId(), curso.getId());
        
        this.definirSituacaoMatricula();
    }

    public MatriculaPK getMatriculaPK() {
        return matriculaPK;
    }

    public void setMatriculaPK(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }
    
    public void setMatriculaPK(Long usuarioId, Long cursoId)
    {
        this.matriculaPK = new MatriculaPK(usuarioId, cursoId);
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
    
    /*
     * seta a situação automaticamente da matricula
     */
    public void setSituacao()
    {
        if (curso == null)
            this.situacao = Situacao.PENDENTE;
        else if (curso.getStatus() == Curso.Status.NORMAL)
            this.situacao = Situacao.CONFIRMADO;
        else 
            this.situacao = Situacao.PENDENTE;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    private void definirSituacaoMatricula() {
        if (this.curso.getStatus() != null)
        {
            if (this.curso.getStatus() == Curso.Status.NORMAL)
                this.situacao = Situacao.CONFIRMADO;
            else
                this.situacao = Situacao.PENDENTE;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaPK != null ? matriculaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.matriculaPK == null && other.matriculaPK != null) || (this.matriculaPK != null && !this.matriculaPK.equals(other.matriculaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Matricula[ matriculaPK=" + matriculaPK + " ]";
    }
    
    public enum Situacao {
        VALOR_RESERVADO,
        PENDENTE, //aluno não confirmado no curso
        CONFIRMADO,
        REJEITADO,
        APROVADO,
        REPROVADO
    }
    
    /*
     * Marcar data/hora
     */
    public void timeStamp()
    {
        this.dataHoraCriacao = new Date();
    }

}
