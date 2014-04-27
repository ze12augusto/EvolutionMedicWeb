package br.com.si5.evolutionmedic.ejb;

import br.com.si5.evolutionmedic.entidades.Avaliacao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author joseaugusto
 */
@Stateless
public class AvaliacaoEJB {

    @PersistenceContext(unitName = "evolution")
    private EntityManager em;

    public String salvar(Avaliacao avaliacao) {

        try {
            em.merge(avaliacao);
        } catch (Exception e) {
           e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    public String excluir(Avaliacao avaliacao) {
        try {
            avaliacao = em.getReference(Avaliacao.class, avaliacao.getIdAvaliacao());
            em.remove(avaliacao);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    public Avaliacao selecionaPorID(Integer id) {
         TypedQuery<Avaliacao> query = em.createQuery("select a From Avaliacao a where a.idAvaliacao = :id",
                Avaliacao.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    public List<Avaliacao> listaTodos() {
        
        TypedQuery<Avaliacao> query = em.createQuery("select a From Avaliacao a",
                Avaliacao.class);
        return query.getResultList();
    }
}
