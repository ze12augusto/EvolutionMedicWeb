package br.com.si5.evolutionmedic.ejb;

import br.com.si5.evolutionmedic.entidades.Frase;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Naruto
 */
@Stateless
public class FraseEJB {

    @PersistenceContext(unitName = "evolution")
    private EntityManager em;

    public String salvar(Frase frase) {

        try {
            em.merge(frase);
        } catch (Exception e) {
           e.printStackTrace();
            return e.getMessage();
        }
        return null;
    }

    public String excluir(Frase frase) {
        try {
            frase = em.getReference(Frase.class, frase.getIdFrase());
            em.remove(frase);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    public Frase selecionaPorID(Integer id) {
        Query query = em.createQuery("Select f From Frase f where f.idFrase= :id");
        query.setParameter("id", id);
        return (Frase) query.getSingleResult();
    }

    public List<Frase> listaTodos() {
        return em.createQuery("from Frase f").getResultList();
    }
}
