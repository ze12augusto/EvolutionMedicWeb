/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.si5.evolutionmedic.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoseAugusto
 */
@Entity
@Table(name = "frase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frase.findAll", query = "SELECT f FROM Frase f")})
public class Frase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdFrase", nullable = false)
    private Integer idFrase;
    @Basic(optional = false)
    @NotNull(message = "Informe a frase")
    @Size(min = 1, max = 120)
    @Column(name = "Descricao", nullable = false, length = 120)
    private String descricao;
    @ManyToMany(mappedBy = "fraseList")
    private List<Avaliacao> avaliacaoList;

    public Frase() {
    }

    public Frase(Integer idFrase) {
        this.idFrase = idFrase;
    }

    public Frase(Integer idFrase, String descricao) {
        this.idFrase = idFrase;
        this.descricao = descricao;
    }

    public Integer getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(Integer idFrase) {
        this.idFrase = idFrase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFrase != null ? idFrase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frase)) {
            return false;
        }
        Frase other = (Frase) object;
        if ((this.idFrase == null && other.idFrase != null) || (this.idFrase != null && !this.idFrase.equals(other.idFrase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
