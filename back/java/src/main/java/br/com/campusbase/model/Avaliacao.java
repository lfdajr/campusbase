package br.com.campusbase.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "AVALIACAO")
public class Avaliacao implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private Estado estado;
    
    @Column(name = "DATA_HORA_FIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraFim;
    
    @Column(name = "DATA_HORA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInicio;
    
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso curso;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avaliacao", fetch = FetchType.LAZY)
    private List<Resolucao> resolucaoList;
    
    @Enumerated
    @Column(name = "STATUS")
    private Status status;    

    public Avaliacao() {
        status = Status.NORMAL;
    }

    public Avaliacao(Long id) {
        this.id = id;
    }

    public Avaliacao(Long id, Estado estado) {
        this.id = id;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso cursoId) {
        this.curso = cursoId;
    }

    public List<Resolucao> getResolucaoList() {
        return resolucaoList;
    }

    public void setResolucaoList(List<Resolucao> resolucaoList) {
        this.resolucaoList = resolucaoList;
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
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Avaliacao[ id=" + id + " ]";
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
    
    public enum Estado{
        AVALIACAO_CRIADA,
        AVALIACAO_RECEBENDO_RESOLUCOES,
        AVALIACAO_ENCERRADA
    }

    public enum Status
    {
        NORMAL, 
        RESERVADO1,
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
