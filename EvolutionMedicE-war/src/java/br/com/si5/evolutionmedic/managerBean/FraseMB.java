package br.com.si5.evolutionmedic.managerBean;

import br.com.si5.evolutionmedic.entidades.Frase;
import br.com.si5.evolutionmedic.ejb.FraseEJB;
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
@Named("fraseMB")
@RequestScoped
public class FraseMB {

    @EJB
    private FraseEJB fEJB;
    private Frase frase;
    private Frase fraseSelecionada;
    private List<Frase> listaParaCarregar;

    public FraseMB() {
        frase = new Frase();
    }

    public Frase getFrase() {
        return frase;
    }

    public List<Frase> getListaParaCarregar() {
        return listaParaCarregar;
    }

    public void setListaParaCarregar(List<Frase> listaParaCarregar) {
        this.listaParaCarregar = listaParaCarregar;
    }

    public void salvar() {

        String erro = fEJB.salvar(frase);

       if (erro == null) {
            FacesMessage fm
                    = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Frase salva com sucesso", null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        } else {
            FacesMessage fm
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    erro, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        frase = new Frase();
    }

    public List<Frase> carregaLista() {
        return listaParaCarregar = fEJB.listaTodos();
    }

    public Frase selecionaPorID(Integer id) {
        return frase = fEJB.selecionaPorID(id);
    }

    public void excluir(Integer id) {
        String erro = fEJB.excluir(id);
        if (erro == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Excluída com sucesso!"));
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Erro ao tentar excluir!"));
        }
    }
<<<<<<< HEAD
    
    public void editar(Integer id){
        frase = selecionaPorID(id);
=======

    public Frase getFraseSelecionada() {
        return fraseSelecionada;
    }

    public void setFraseSelecionada(Frase fraseSelecionada) {
        this.fraseSelecionada = fraseSelecionada;
    }
    
    public void editar(){
    
        frase = fraseSelecionada;
>>>>>>> 26948465f565f8f69d50adfe2ea89d04199ee2ac
    }
}
