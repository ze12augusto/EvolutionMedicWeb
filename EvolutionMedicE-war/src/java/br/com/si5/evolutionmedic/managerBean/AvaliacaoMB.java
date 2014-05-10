package br.com.si5.evolutionmedic.managerBean;

import br.com.si5.evolutionmedic.ejb.AvaliacaoEJB;
import br.com.si5.evolutionmedic.entidades.Avaliacao;
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
    private AvaliacaoEJB aEJB;
    private Avaliacao avaliacao;
    private List<Avaliacao> listaParaCarregar;

    public AvaliacaoMB() {
        avaliacao = new Avaliacao();
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public List<Avaliacao> getListaParaCarregar() {
        return listaParaCarregar;
    }

    public void setListaParaCarregar(List<Avaliacao> listaParaCarregar) {
        this.listaParaCarregar = listaParaCarregar;
    }

    public void salvar() {

        String erro = aEJB.salvar(avaliacao);

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
    }

    public List<Avaliacao> carregaLista() {
        return listaParaCarregar = aEJB.listaTodos();
    }

    public Avaliacao selecionaPorID(Integer id) {
        return avaliacao = aEJB.selecionaPorID(id);
    }

    public void excluir(Integer id) {
        String erro = aEJB.excluir(id);
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
