package br.com.si5.evolutionmedic.ejb;

import br.com.si5.evolutionmedic.entidades.TipoLeito;
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
public class TipoLeitoEJB {

    @PersistenceContext(unitName = "evolution")
    private EntityManager em;

    public String salvar(TipoLeito tipoleito) {

        try {
            em.merge(tipoleito);
        } catch (Exception e) {

            return e.getMessage();
        }
        return null;
    }

    public String excluir(TipoLeito tipoleito) {
        try {
            tipoleito = em.getReference(TipoLeito.class, tipoleito.getIdTipoLeito());
            em.remove(tipoleito);
        } catch (Exception e) {

            return e.getMessage();
        }
        return null;
    }

    public TipoLeito selecionaPorID(Integer id) {
        TypedQuery<TipoLeito> query = em.createQuery("select t From TipoLeito t where t.idTipoLeito = :id",
                TipoLeito.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public TipoLeito selecionaPorDescricao(String descricao) {
        TypedQuery<TipoLeito> query = em.createQuery("select t From TipoLeito t where t.descricao = :descricao",
                TipoLeito.class);
        query.setParameter("descricao", descricao);
        return query.getSingleResult();
    }

    public List<TipoLeito> listaTodos() {

        TypedQuery<TipoLeito> query = em.createQuery("select t from TipoLeito t", TipoLeito.class);
        return query.getResultList();
    }
}
