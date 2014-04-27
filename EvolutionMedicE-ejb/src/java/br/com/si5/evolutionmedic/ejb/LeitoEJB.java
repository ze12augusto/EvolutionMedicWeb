package br.com.si5.evolutionmedic.ejb;

import br.com.si5.evolutionmedic.entidades.Leito;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
        TypedQuery<Leito> query = em.createQuery("select l From Leito l where l.idLeito = :id",
                Leito.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    public Leito selecionaPorDescricao(String descricao) {
        TypedQuery<Leito> query = em.createQuery("select l From Leito l where l.descricaoLeito = :descricao",
                Leito.class);
        query.setParameter("descricao", descricao);
        return query.getSingleResult();
    }

    public List<Leito> listaTodos() {
        TypedQuery<Leito> query = em.createQuery("select l From Leito l",
                Leito.class);
        return query.getResultList();
    }
}
