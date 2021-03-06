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

    public String excluir(Integer id) {
        try {
            Frase frase = em.getReference(Frase.class, id);
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
    
    public Frase selecionaPorDescricao(String nome) {
        TypedQuery<Frase> query = em.createQuery("select f From Frase f where f.descricao = :nome",
                Frase.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }
    
     public List<Frase> selecionaPorNome(String nome) {
        TypedQuery<Frase> query = em.createQuery("select f From Frase f where f.descricao like :nome",
                Frase.class);
        query.setParameter("nome", '%'+nome+'%');
        return query.getResultList();
    }

    public List<Frase> listaTodos() {
        
      return em.createNamedQuery("Frase.findAll").getResultList();
    }
}
