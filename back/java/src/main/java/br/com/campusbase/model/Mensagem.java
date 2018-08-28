package br.com.campusbase.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "MENSAGEM")
public class Mensagem implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "MENSAGEM")
    private String mensagem;
    
    @Column(name = "ARQUIVOS")
    private String arquivos;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @Basic(optional = false)
    @Enumerated
    @Column(name = "TIPO")
    private Tipo tipo;
    
    @Basic(optional = false)
    @Column(name = "DESTINO")
    private long destino;
    
    @OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
    private List<Mensagem> mensagemList;
    
    @JoinColumn(name = "PAI", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mensagem pai;
    
    @JoinColumn(name = "REMETENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario remetente;

    public Mensagem() {
        this.dataHora = new Date();
    }

    public Mensagem(Long id) {
        this.id = id;
    }

    public Mensagem(Long id, String mensagem, Date dataHota, Tipo tipo, long destino) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataHora = dataHota;
        this.tipo = tipo;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagemAntiXSS(String mensagem) {
        //this.mensagem = Utilitario.truncarString(Utilitario.antiXSSString(mensagem), 1024);
        this.mensagem = mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHota) {
        this.dataHora = dataHota;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public long getDestino() {
        return destino;
    }

    public void setDestino(long destino) {
        this.destino = destino;
    }

    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }

    public Mensagem getPai() {
        return pai;
    }

    public void setPai(Mensagem pai) {
        this.pai = pai;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
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
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Mensagem[ id=" + id + " ]";
    }

    /**
     * @return the arquivos
     */
    public String getArquivos() {
        return arquivos;
    }

    /**
     * @param arquivos the arquivos to set
     */
    public void setArquivos(String arquivos) {
        this.arquivos = arquivos;
    }
    
    public enum Tipo
    {
        // NAO PODE MUDAR ORDEM
        MENSAGEM_FORUM, 
        MENSAGEM_CURSO_PROFESSOR, //Mensagem enviada pelo professor para o curso
        MENSAGEM_PRIVADA,
        MENSAGEM_GRUPO_FORUM,
        MENSAGEM_AUTOMATICA_NOVO_ARQUIVO,
        MENSAGEM_AUTOMATICA_NOVA_AGENDA
    }

}
