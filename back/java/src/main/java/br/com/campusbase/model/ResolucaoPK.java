package br.com.campusbase.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ResolucaoPK implements Serializable 
{
    @Basic(optional = false)
    @Column(name = "AVALIACAO_ID")
    private long avaliacaoId;
    
    @Basic(optional = false)
    @Column(name = "USUARIO_ID")
    private long usuarioId;

    public ResolucaoPK() {
    }

    public ResolucaoPK(long avaliacaoId, long usuarioId) {
        this.avaliacaoId = avaliacaoId;
        this.usuarioId = usuarioId;
    }

    public long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) avaliacaoId;
        hash += (int) usuarioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResolucaoPK)) {
            return false;
        }
        ResolucaoPK other = (ResolucaoPK) object;
        if (this.avaliacaoId != other.avaliacaoId) {
            return false;
        }
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.ResolucaoPK[ avaliacaoId=" + avaliacaoId + ", usuarioId=" + usuarioId + " ]";
    }

}
