package br.com.campusbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "AGENDA")
public class Agenda implements Serializable 
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
    @Column(name = "TEXTO")
    private String texto;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInicio;
    
    @Column(name = "DATA_HORA_FIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraFim;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;
    
    @Basic(optional = false)
    @Column(name = "TIPO_DESTINO")
    private TipoDestino tipoDestino;
    
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Status status;
    
    @Basic(optional = false)
    @Column(name = "TIPO")
    private Tipo tipo; //informa o tipo do evento, agenda: congresso, prova, exame
    
    @Column(name = "DESTINO")
    private Long destino;
    
    /*@JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)*/
    @Column(name = "USUARIO_ID")
    private Long usuarioId;

    public Agenda() {
        this.texto = "";
        this.dataHoraCriacao = new Date();
        this.status = Agenda.Status.NORMAL;
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public TipoDestino getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(TipoDestino tipo) {
        this.tipoDestino = tipo;
    }

    public Long getDestino() {
        return destino;
    }

    public void setDestino(Long destino) {
        this.destino = destino;
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
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Agenda[ id=" + id + " ]";
    }

    /**
     * @return the usuario
     */
    public Long getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuarioId(Long usuario) {
        this.usuarioId = usuario;
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
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    private String formatDataHoraUTC(Date data)
        throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(data) + "T" + sdf2.format(data);
    }      
    
    @JsonIgnore
    public String getDataInicioUTC() throws Exception
    {
        return formatDataHoraUTC(dataHoraInicio);
    }
    
    @JsonIgnore
    public String getDataFimUTC() throws Exception
    {
        return formatDataHoraUTC(dataHoraFim);
    }
    
    
    public enum TipoDestino {
        GLOBAL, //EVENTO GLOBAL
        TURMA, //EVENTO DE UMA TURMA
        RESERVADO2,
        RESERVADO3,
        RESERVADO4,
        RESERVADO5,
        RESERVADO6,
        RESERVADO7,
        RESERVADO8,
        RESERVADO9,
        RESERVADO10,
        RESERVADO11 //11
    }        
    
    public enum Status {
        NORMAL, 
        ENCERRADO, //Passou o tempo do evento
        RESERVADO2,
        RESERVADO3,
        RESERVADO4,
        RESERVADO5,
        RESERVADO6,
        RESERVADO7,
        RESERVADO8,
        RESERVADO9,
        RESERVADO10,
        DELETADO //11
    }        
    
    public enum Tipo {
        PROVA,
        TRABALHO,
        EXERCICIO,
        TREINAMENTO,
        AULA,
        RESERVADO3,
        EVENTO,
        OUTRO
    }

}
