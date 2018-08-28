package br.com.campusbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "AULA")
public class Aula implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;
    
    @JsonIgnore
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso curso;
    
    @Enumerated
    @Column(name = "STATUS")
    private Status status;
    
    @OneToMany
    @JoinTable(
            name="MIDIA",
            joinColumns = @JoinColumn( name="DESTINO"),
            inverseJoinColumns = @JoinColumn( name="ID") )
    private List<Midia> arquivos;
    
    
    public Aula() {
        dataHora = new Date();
        dataHoraCriacao = new Date();
        descricao = "";
        status = Status.EM_ELABORACAO;
    }

    public Aula(Long id) {
        dataHora = new Date();
        dataHoraCriacao = new Date();
        descricao = "";
        status = Status.EM_ELABORACAO;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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
        if (!(object instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Aula[ id=" + id + " ]";
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
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
     * @return the dataHoraCriacao
     */
    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    /**
     * @param dataHoraCriacao the dataHoraCriacao to set
     */
    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
    
    public List<Midia> getArquivos()
    {
        if (this.arquivos == null)
            this.arquivos = new ArrayList<Midia>();
        return this.arquivos;
    }
    
    public enum Status
    {
        EM_ELABORACAO, 
        PUBLICADA,
        RESERVADO2,
        RESERVADO3,
        RESERVADO4,
        RESERVADO5,
        RESERVADO6,
        RESERVADO7,
        RESERVADO8,
        RESERVADO9,
        RESERVADO10,
        DELETADO
    }
    
}
