package br.com.si5.evolutionmedic.ejb;

import br.com.si5.evolutionmedic.entidades.Frase;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
        TypedQuery<Frase> query = em.createQuery("select f From Frase f where f.idFrase = :id",
                Frase.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Frase> listaTodos() {
        TypedQuery<Frase> query = em.createQuery("select f From Frase f",
                Frase.class);
        return query.getResultList();
    }
}
