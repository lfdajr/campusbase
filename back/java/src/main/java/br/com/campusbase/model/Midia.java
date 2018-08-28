package br.com.campusbase.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.persistence.*;

@Entity
@Table(name = "MIDIA")
public class Midia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Basic(optional = false)
    @Column(name = "CAMINHO")
    private String caminho;
    
    @Basic(optional = false)
    @Enumerated
    @Column(name = "TIPO")
    private Tipo tipo;
    
    @Basic(optional = false)
    @Enumerated
    @Column(name = "TIPO_DESTINO")
    private TipoDestino tipoDestino;
    
    @Basic(optional = false)
    @Column(name = "DESTINO")
    private long destino;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @JoinColumn(name = "REMETENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario remetente;
    
    @Enumerated
    @Column(name = "STATUS")
    private Status status;    

    public Midia() {
        this.dataHora = new Date();
        this.tipo = Tipo.ARQUIVO;
        this.tipoDestino = TipoDestino.CURSO;
        status = Status.NORMAL;
    }

    public Midia(Long id) {
        this.id = id;
    }

    public Midia(Long id, String caminho, Tipo tipo, TipoDestino tipoDestino, long destino, Date dataHora) {
        this.id = id;
        this.caminho = caminho;
        this.tipo = tipo;
        this.tipoDestino = tipoDestino;
        this.destino = destino;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminhoArquivo) {
        this.caminho = caminhoArquivo;
         acertarCaminho();
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
        acertarCaminho();
    }

    public long getDestino() {
        return destino;
    }

    public void setDestino(long destino) {
        this.destino = destino;
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
        if (!(object instanceof Midia)) {
            return false;
        }
        Midia other = (Midia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Midia[ id=" + id + " ]";
    }

    /**
     * @return the remetente
     */
    public Usuario getRemetente() {
        return remetente;
    }

    /**
     * @param remetente the remetente to set
     */
    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    /**
     * @return the tipoDestino
     */
    public TipoDestino getTipoDestino() {
        return tipoDestino;
    }

    /**
     * @param tipoDestino the tipoDestino to set
     */
    public void setTipoDestino(TipoDestino tipoDestino) {
        this.tipoDestino = tipoDestino;
    }
    
    private void acertarCaminho()
    {
        if (caminho == null)
            return;
        
        if (this.tipo == Tipo.LINK)
        {
            if (!caminho.startsWith("http://"))
            {
                if (!caminho.startsWith("https://"))
                {
                    caminho = "http://" + caminho;
                }
            }
        }
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
    
    /*
     * Tipo da Midia
     */
    public enum Tipo
    {
        ARQUIVO, LINK, VIDEO, FOTO, AUDIO
    }
    
    
    /*
     * Tipo do destino/relacionamento da midia
     */
    public enum TipoDestino
    {
        CURSO, CURSO_FORUM, GRUPO, GRUPO_FORUM, CURSO_AULA, CURSO_AVALIACAO
    }

    public enum Status
    {
        NORMAL, 
        COPIA,
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
