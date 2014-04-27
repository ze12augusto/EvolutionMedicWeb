/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.si5.evolutionmedic.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JoseAugusto
 */
@Entity
@Table(name = "leito")

public class Leito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdLeito", nullable = false)
    private Short idLeito;
    @Basic(optional = false)
    @Column(name = "DescricaoLeito", nullable = false)
    private String descricaoLeito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leito")
    private List<Avaliacao> avaliacaoList;
    @JoinColumn(name = "IdTipo_Leito", referencedColumnName = "IdTipo_Leito", nullable = false)
    @ManyToOne(optional = false)
    private TipoLeito tipoLeito;

    public Leito() {
    }

    public Leito(Short idLeito) {
        this.idLeito = idLeito;
    }

    public Short getIdLeito() {
        return idLeito;
    }

    public String getDescricaoLeito() {
        return descricaoLeito;
    }

    public void setDescricaoLeito(String descricaoLeito) {
        this.descricaoLeito = descricaoLeito;
    }
    
    public void setIdLeito(Short idLeito) {
        this.idLeito = idLeito;
    }

    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }


    public TipoLeito getTipoLeito() {
        return tipoLeito;
    }

    public void setTipoLeito(TipoLeito tipoLeito) {
        this.tipoLeito = tipoLeito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLeito != null ? idLeito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leito)) {
            return false;
        }
        Leito other = (Leito) object;
        if ((this.idLeito == null && other.idLeito != null) || (this.idLeito != null && !this.idLeito.equals(other.idLeito))) {
            return false;
        }
        return true;
    }
}
