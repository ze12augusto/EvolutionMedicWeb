package br.com.si5.evolutionmedic.padroes;

import br.com.si5.evolutionmedic.ejb.TipoLeitoEJB;
import br.com.si5.evolutionmedic.entidades.Leito;
import br.com.si5.evolutionmedic.ejb.LeitoEJB;
import br.com.si5.evolutionmedic.entidades.TipoLeito;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author joseaugusto
 */
@Named("criadorTipoLeitosPadrao")
@RequestScoped
public class CriadorTipoLeitosPadrao {

    @EJB
    private TipoLeitoEJB tipoLeitoEjb;
    private TipoLeito tipoLeito;
    private List<TipoLeito> tiposLeito;
    private Leito leito;
    @EJB
    private LeitoEJB leitoEjb;
    private List<Leito> leitosExtras, leitosNormais, leitosIsolados;

    private void salvarTipoLeito(String descricao) {

        tipoLeito = new TipoLeito();
        tipoLeito.setDescricao(descricao);

        String erro = tipoLeitoEjb.salvar(tipoLeito);

        if (erro == null) {

            System.out.println("salvando tipo leito -> " + tipoLeito.getDescricao());

        } else {

            System.out.println("erro ao salvar tipo leito -> " + tipoLeito.getDescricao());
        }
    }

    public void criadorTipoLeito() {

        tiposLeito = tipoLeitoEjb.listaTodos();
        leitosNormais = leitoEjb.listaTodos();

        if (tiposLeito.isEmpty()) {
            salvarTipoLeito("Extra");
            salvarTipoLeito("Normal");
            salvarTipoLeito("Isolado");

        }

        if (leitosNormais.isEmpty()) {

            criarLeitos();
        }
    }

    private void criarLeitos() {

        leitosExtras = new ArrayList<>();
        leitosNormais = new ArrayList<>();
        leitosIsolados = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            salvarLeito("Extra");
        }
        tipoLeito = buscaTipoLeito("Extra");
        tipoLeito.setLeitoList(leitosExtras);
        tipoLeitoEjb.salvar(tipoLeito);

        for (int i = 0; i < 10; i++) {

            salvarLeito("Normal");
        }
        tipoLeito = buscaTipoLeito("Normal");
        tipoLeito.setLeitoList(leitosNormais);
        tipoLeitoEjb.salvar(tipoLeito);

        for (int i = 0; i < 4; i++) {

            salvarLeito("Isolado");
        }
        tipoLeito = buscaTipoLeito("Isolado");
        tipoLeito.setLeitoList(leitosIsolados);
        tipoLeitoEjb.salvar(tipoLeito);
    }

    private void salvarLeito(String descricao) {

        leito = new Leito();
        leito.setTipoLeito(buscaTipoLeito(descricao));
        adicionarNaLista(leito, descricao);
    }

    private void adicionarNaLista(Leito leito, String descricao) {

        switch (descricao) {
            case "Extra":
                leitosExtras.add(leito);
                break;
            case "Normal":
                leitosNormais.add(leito);
                break;
            default:
                leitosIsolados.add(leito);
                break;
        }
    }

    private TipoLeito buscaTipoLeito(String descricao) {

        return tipoLeitoEjb.selecionaPorDescricao(descricao);
    }
}
