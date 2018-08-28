package br.com.campusbase.repository;

import br.com.campusbase.model.Mensagem;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MensagemRepository  extends CrudRepository<Mensagem, Long> {
    @Query("from Mensagem where pai is null and destino = :cursoId and tipo not in (:tipo)")
    List<Mensagem> buscarMensagensTurma(@Param("cursoId") Long cursoId, @Param("tipo") List<Mensagem.Tipo> tipo);
}

/*
    public ActionForward enviar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        Usuario usuario = (Usuario) request.getSession().getAttribute(Globals.USUARIO);
        Long idCurso = Long.parseLong(request.getParameter("cid"));
        Curso curso = (Curso) Fachada.instancia().consulta(Curso.class, idCurso);
        
        boolean comentario = false;
        
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(usuario);
        mensagem.setTipo(Mensagem.Tipo.MENSAGEM_FORUM);
        mensagem.setDestino(curso.getId());
        mensagem.setMensagemAntiXSS(request.getParameter("texto"));
        
        if (request.getParameter("pai") != null)
        {
            long pai = Long.parseLong(request.getParameter("pai"));
            Mensagem mensagemPai = (Mensagem) Fachada.instancia().consulta(Mensagem.class, pai);
            mensagem.setPai(mensagemPai);
            comentario = true;
        }
        Fachada.instancia().inserir(mensagem);
        
        try
        {
            if (mensagem.getPai() == null && mensagem.getRemetente().equals(curso.getProfessor())) //não é um comentário é uma mensagem normal e é do professor
                Fachada.instancia().enviarEmail(usuario, curso, mensagem);
            else if (mensagem.getPai() != null)
                Fachada.instancia().enviarEmailComentario(usuario, curso, mensagem);
                
        }
        catch (Exception e)
        {
            
        }
        
        request.setAttribute("mensagem", mensagem);
        
        if (comentario)
        {
            return mapping.findForward("enviarT");
        }
        else
        {
            request.setAttribute("curso", curso);
            return mapping.findForward("enviar");
        }
    }
*/
/*
    public List<Mensagem> buscarMensagens(Curso curso, int pagina)
            throws Exception {
        EntityManager em = EMUtil.getEntityManager();
        EntityTransaction tx = null;
        List<Mensagem> retorno = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            
            Query q = em.createQuery("from Mensagem where pai is null and destino = :destino and tipo = :tipo order by dataHora desc");
            q.setParameter("destino", curso.getId());
            q.setParameter("tipo", Mensagem.Tipo.MENSAGEM_FORUM);
            q.setMaxResults(6);
            q.setFirstResult(pagina*6);
            retorno = q.getResultList();
            
            Query q2 = em.createQuery("from Mensagem where pai.id = :pai and destino = :destino and tipo = :tipo order by dataHora asc");
            
            for (Mensagem m : retorno)
            {
                q2.setParameter("pai", m.getId());
                q2.setParameter("destino", curso.getId());
                q2.setParameter("tipo", Mensagem.Tipo.MENSAGEM_FORUM);
                List<Mensagem> filhas = q2.getResultList();
                
                m.setMensagemList(filhas);
            }
            
            tx.commit();
        } catch (javax.persistence.NoResultException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new UsuarioInexistenteException();
        } finally {
            em.close();
        }
        
        return retorno;
    }*/