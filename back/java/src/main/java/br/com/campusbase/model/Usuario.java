package br.com.campusbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;
    
    @Basic(optional = false)
    @Column(name = "SENHA")
    @JsonIgnore
    private String senha;
    
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "DATA_HORA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;
    
    @Column(name = "ULTIMO_ACESSO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;
    
    @Column(name = "FOTO")
    private String foto;
    
    @Basic(optional = false)
    @Enumerated
    @Column(name = "TIPO")
    private Tipo tipo;
    
    @Column(name = "FACEBOOK_ID")
    @JsonIgnore
    private Long facebookId;
    
    public Usuario() {
        this.dataHoraCriacao = new Date();
        this.tipo = Tipo.ALUNO;
    }

    public Usuario(Long id) {
        this.id = id;
        this.dataHoraCriacao = new Date();
    }

    public Usuario(Long id, String nome, String senha, String email, Date dataHoraCriacao) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.educacao.domain.Usuario[ id=" + id + " ]";
    }

    /**
     * @return the nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * @param nomeCompleto the nomeCompleto to set
     */
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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
    
    public enum Tipo
    {
        ALUNO, PROFESSOR, PROFESSOR_LIBERADO
    }

    /**
     * @return the facebookId
     */
    public Long getFacebookId() {
        return facebookId;
    }

    /**
     * @param facebookId the facebookId to set
     */
    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

}
