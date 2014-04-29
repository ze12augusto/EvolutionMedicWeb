/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.si5.evolutionmedic.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JoseAugusto
 */
@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAvaliacao", nullable = false)
    private Integer idAvaliacao;
    @Basic(optional = false)
    @NotNull(message = "Informe o médico")
    @Column(name = "NomeMedico", nullable = false, length = 60)
    private String nomeMedico;
    @Basic(optional = false)
    @NotNull(message = "Informe o paciente")
    @Column(name = "NomePaciente", nullable = false, length = 60)
    private String nomePaciente;

    @Basic(optional = false)
    @NotNull
    @Column(name = "dataAvaliacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAvaliacao;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "avaliacao_frase", joinColumns = {
        @JoinColumn(name = "IdAvaliacao", referencedColumnName = "IdAvaliacao", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "IdFrase", referencedColumnName = "IdFrase", nullable = false)})
    private List<Frase> fraseList;
    
    @JoinColumn(name = "IdLeito", referencedColumnName = "IdLeito", nullable = false)
    @ManyToOne(optional = false)
    private Leito leito;

    public Avaliacao() {
    }

    public Avaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Avaliacao(Integer idAvaliacao, String nomeMedico, String nomePaciente) {
        this.idAvaliacao = idAvaliacao;
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public List<Frase> getFraseList() {
        return fraseList;
    }

    public void setFraseList(List<Frase> fraseList) {
        this.fraseList = fraseList;
    }

    public Leito getLeito() {
        return leito;
    }

    public void setLeito(Leito leito) {
        this.leito = leito;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAvaliacao != null ? idAvaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.idAvaliacao == null && other.idAvaliacao != null) || (this.idAvaliacao != null && !this.idAvaliacao.equals(other.idAvaliacao))) {
            return false;
        }
        return true;
    }

}
