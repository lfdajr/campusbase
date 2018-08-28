package br.com.campusbase.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MatriculaPK implements Serializable 
{
    @Basic(optional = false)
    @Column(name = "USUARIO_ID")
    private long usuarioId;
    @Basic(optional = false)
    @Column(name = "CURSO_ID")
    private long cursoId;

    public MatriculaPK() {
    }

    public MatriculaPK(long usuarioId, long cursoId) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getCursoId() {
        return cursoId;
    }

    public void setCursoId(long cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuarioId;
        hash += (int) cursoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculaPK)) {
            return false;
        }
        MatriculaPK other = (MatriculaPK) object;
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        if (this.cursoId != other.cursoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.MatriculaPK[ usuarioId=" + usuarioId + ", cursoId=" + cursoId + " ]";
    }

}
