package br.com.si5.evolutionmedic.ejb;

import br.com.si5.evolutionmedic.entidades.Leito;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JoseAugusto
 */
@Stateless
public class LeitoEJB {

    @PersistenceContext
    private EntityManager em;

    public String salvar(Leito leito) {

        try {
            em.merge(leito);
        } catch (Exception e) {

            return e.getMessage();
        }
        return null;
    }

    public String excluir(Leito leito) {

        try {
            leito = em.getReference(Leito.class, leito.getIdLeito());
            em.remove(leito);
        } catch (Exception e) {

            return e.getMessage();
        }
        return null;
    }

    public Leito selecionaPorID(Integer id) {
        Query query = em.createQuery("Select l From Leito l where l.idLeito= :id");
        query.setParameter("id", id);
        return (Leito) query.getSingleResult();
    }

    public List<Leito> listaTodos() {
        return em.createQuery("from Leito l").getResultList();
    }
}
