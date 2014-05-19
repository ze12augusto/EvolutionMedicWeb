package br.com.si5.evolutionmedic.managerBean;

import br.com.si5.evolutionmedic.ejb.AvaliacaoEJB;
import br.com.si5.evolutionmedic.ejb.FraseEJB;
import br.com.si5.evolutionmedic.ejb.LeitoEJB;
import br.com.si5.evolutionmedic.entidades.Avaliacao;
import br.com.si5.evolutionmedic.entidades.Frase;
import br.com.si5.evolutionmedic.entidades.Leito;
import br.com.si5.evolutionmedic.session.SessionUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Naruto
 */
@Named("avaliacaoMB")
@RequestScoped
public class AvaliacaoMB {

    @EJB
    private AvaliacaoEJB avaliacaoEJB;
    @EJB
    private FraseEJB fraseEjb;
    private Avaliacao avaliacao;
    private List<Frase> frases;
    private Leito leito;
    @EJB
    private LeitoEJB leitoEjb;
    private String descricao;
    private List<Avaliacao> avaliacoes;

    public AvaliacaoMB() {
        avaliacao = new Avaliacao();
        frases = new ArrayList<>();
        avaliacoes = new ArrayList<>();
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public List<Frase> getFrases() {
        return frases;
    }

    public void setFrases(List<Frase> frases) {
        this.frases = frases;
    }

    public void recuperarLeito(String leitoEscolhido) {

        leito = leitoEjb.selecionaPorDescricao(leitoEscolhido);
    }

    public void adicionarFrase() {

    }

    public void salvar() {
        recuperarLeito(getSessionParam("leito"));
        Frase frase = fraseEjb.selecionaPorDescricao(descricao);
        frases.add(frase);
        avaliacao.setDataAvaliacao(new Date());
        avaliacao.setFraseList(frases);
        avaliacao.setLeito(leito);
        avaliacoes.add(avaliacao);
        leito.setAvaliacaoList(avaliacoes);
        String erro = leitoEjb.salvar(leito);

        if (erro == null) {
            FacesMessage fm
                    = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Avaliação salva com sucesso", null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        } else {
            FacesMessage fm
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            erro, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        avaliacao = new Avaliacao();
        avaliacoes = new ArrayList<>();
        frases = new ArrayList<>();
    }

    public List<Avaliacao> carregaLista() {
        return avaliacaoEJB.listaTodos();
    }

    public List<Avaliacao> carregaListaPorLeito() {
        return avaliacaoEJB.listarPorLeito(getSessionParam("leito"));
    }

    public String getSessionParam(String nome) {

        return SessionUtil.getParam(nome).toString();
    }

    public List<Frase> carregaListaFrase() {

        return frases;
    }

    public Avaliacao selecionaPorID(Integer id) {
        return avaliacao = avaliacaoEJB.selecionaPorID(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void excluir(Integer id) {
        String erro = avaliacaoEJB.excluir(id);
        if (erro == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Excluída com sucesso!"));
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Erro ao tentar excluir!"));
        }
    }

    public void setSessionParamLeito(String valor) {

        SessionUtil.setParam("leito", valor);
    }

    public void editar() {
        Integer id = Integer.valueOf(getSessionParam("idAvaliacao"));
        if (id != 0) {
            avaliacao = selecionaPorID(id);
            setSessionParamIdAvaliacao(0);
        }
    }

    public void setSessionParamIdAvaliacao(Integer id) {

        SessionUtil.setParam("idAvaliacao", id);
    }
}
