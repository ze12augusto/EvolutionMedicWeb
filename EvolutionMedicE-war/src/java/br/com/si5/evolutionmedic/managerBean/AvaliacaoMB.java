package br.com.si5.evolutionmedic.managerBean;

import br.com.si5.evolutionmedic.ejb.AvaliacaoEJB;
<<<<<<< HEAD
import br.com.si5.evolutionmedic.ejb.LeitoEJB;
import br.com.si5.evolutionmedic.ejb.FraseEJB;
import br.com.si5.evolutionmedic.entidades.Avaliacao;
import br.com.si5.evolutionmedic.entidades.Frase;
import br.com.si5.evolutionmedic.entidades.Leito;
import java.util.ArrayList;
import java.util.Date;
=======
import br.com.si5.evolutionmedic.entidades.Avaliacao;
>>>>>>> d1d777f11263432c590b5b0295dbf6123d4de861
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
<<<<<<< HEAD
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
    private String leitoEscolhido;

    public AvaliacaoMB() {
        avaliacao = new Avaliacao();
        frases = new ArrayList<>();
        avaliacoes = new ArrayList<>();
        leitoEscolhido = "";
=======
    private AvaliacaoEJB aEJB;
    private Avaliacao avaliacao;
    private List<Avaliacao> listaParaCarregar;

    public AvaliacaoMB() {
        avaliacao = new Avaliacao();
>>>>>>> d1d777f11263432c590b5b0295dbf6123d4de861
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

<<<<<<< HEAD
    public List<Frase> getFrases() {
        return frases;
    }

    public void setFrases(List<Frase> frases) {
        this.frases = frases;
    }

    public String getLeitoEscolhido() {
        return leitoEscolhido;
    }

    public void setLeitoEscolhido(String leitoEscolhido) {
        this.leitoEscolhido = leitoEscolhido;
    }
    
    public void recuperarLeito(String leitoEscolhido){
    
        leito = leitoEjb.selecionaPorDescricao(leitoEscolhido);
    }
    
    public void adicionarFrase(){
    
        Frase frase = fraseEjb.selecionaPorDescricao(descricao);
        frases.add(frase);
    }
    
    public void salvar() {
        
        recuperarLeito(leitoEscolhido);
        avaliacao.setDataAvaliacao(new Date());
        avaliacao.setFraseList(frases);
        avaliacao.setIdAvaliacao(Integer.MAX_VALUE);
        avaliacao.setLeito(leito);
        avaliacoes.add(avaliacao);
        leito.setAvaliacaoList(avaliacoes);
        String erro = leitoEjb.salvar(leito);
=======
    public List<Avaliacao> getListaParaCarregar() {
        return listaParaCarregar;
    }

    public void setListaParaCarregar(List<Avaliacao> listaParaCarregar) {
        this.listaParaCarregar = listaParaCarregar;
    }

    public void salvar() {

        String erro = aEJB.salvar(avaliacao);
>>>>>>> d1d777f11263432c590b5b0295dbf6123d4de861

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
<<<<<<< HEAD
        avaliacoes = new ArrayList<>();
        frases = new ArrayList<>();
    }

    public List<Avaliacao> carregaLista() {
        return avaliacaoEJB.listaTodos();
    }

    public List<Frase> carregaListaFrase(){
    
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
=======
    }

    public List<Avaliacao> carregaLista() {
        return listaParaCarregar = aEJB.listaTodos();
    }

    public Avaliacao selecionaPorID(Integer id) {
        return avaliacao = aEJB.selecionaPorID(id);
    }

    public void excluir(Integer id) {
        String erro = aEJB.excluir(id);
>>>>>>> d1d777f11263432c590b5b0295dbf6123d4de861
        if (erro == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Excluída com sucesso!"));
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Erro ao tentar excluir!"));
        }
    }

    public void editar(Integer id){
    
        avaliacao = selecionaPorID(id);
    }
}
