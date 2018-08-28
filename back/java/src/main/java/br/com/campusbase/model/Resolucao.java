package br.com.campusbase.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "RESOLUCAO")
public class Resolucao implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ResolucaoPK resolucaoPK;
    @Basic(optional = false)
    
    @Column(name = "DATA_HORA_ENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEnvio;
    
    @Column(name = "CAMINHO_ARQUIVO_RESOLUCAO")
    private String caminhoArquivoResolucao;
    
    @Lob
    @Column(name = "RESOLUCAO")
    private String resolucao;
    
    @Column(name = "CAMINHO_ARQUIVO_CORRECAO")
    private String caminhoArquivoCorrecao;
    
    @Lob
    @Column(name = "CORRECAO")
    private String correcao;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NOTA")
    private BigDecimal nota;
    
    @Column(name = "DATA_HORA_CORRECAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCorrecao;
    
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    
    @JoinColumn(name = "AVALIACAO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Avaliacao avaliacao;
    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private Estado estado;

    public Resolucao() {
        this.resolucao = "";
        this.correcao = "";
        this.dataHoraEnvio = new Date();
        this.estado = Estado.CRIADA;
    }

    public Resolucao(long avaliacaoId, long usuarioId) {
        this.resolucaoPK = new ResolucaoPK(avaliacaoId, usuarioId);
        this.resolucao = "";
        this.correcao = "";
        this.dataHoraEnvio = new Date();
        this.estado = Estado.CRIADA;
    }

    public ResolucaoPK getResolucaoPK() {
        return resolucaoPK;
    }

    public void setResolucaoPK(ResolucaoPK resolucaoPK) {
        this.resolucaoPK = resolucaoPK;
    }

    public Date getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public void setDataHoraEnvio(Date dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    public String getCaminhoArquivoResolucao() {
        return caminhoArquivoResolucao;
    }

    public void setCaminhoArquivoResolucao(String caminhoArquivoResolucao) {
        this.caminhoArquivoResolucao = caminhoArquivoResolucao;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public String getCaminhoArquivoCorrecao() {
        return caminhoArquivoCorrecao;
    }

    public void setCaminhoArquivoCorrecao(String caminhoArquivoCorrecao) {
        this.caminhoArquivoCorrecao = caminhoArquivoCorrecao;
    }

    public String getCorrecao() {
        return correcao;
    }

    public void setCorrecao(String correcao) {
        this.correcao = correcao;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Date getDataHoraCorrecao() {
        return dataHoraCorrecao;
    }

    public void setDataHoraCorrecao(Date dataHoraCorrecao) {
        this.dataHoraCorrecao = dataHoraCorrecao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resolucaoPK != null ? resolucaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resolucao)) {
            return false;
        }
        Resolucao other = (Resolucao) object;
        if ((this.resolucaoPK == null && other.resolucaoPK != null) || (this.resolucaoPK != null && !this.resolucaoPK.equals(other.resolucaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Resolucao[ resolucaoPK=" + resolucaoPK + " ]";
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public enum Estado
    {
        CRIADA, ENVIADA, CORRIGIDA
    }

}
