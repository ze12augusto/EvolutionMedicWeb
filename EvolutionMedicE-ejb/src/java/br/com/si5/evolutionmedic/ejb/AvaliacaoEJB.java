package br.com.si5.evolutionmedic.ejb;

import br.com.si5.evolutionmedic.entidades.Avaliacao;
import br.com.si5.evolutionmedic.entidades.Frase;
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

    public String excluir(Integer id) {
        try {
            Avaliacao avaliacao = em.getReference(Avaliacao.class, id);
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

<<<<<<< HEAD
=======
    public String salvar(Frase frase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
>>>>>>> 26948465f565f8f69d50adfe2ea89d04199ee2ac
}
