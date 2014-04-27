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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoseAugusto
 */
@Entity
@Table(name = "tipo_leito")
public class TipoLeito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipo_Leito", nullable = false)
    private Short idTipoLeito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descricao", nullable = false, length = 30)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoLeito")
    private List<Leito> leitoList;

    public TipoLeito() {
    }

    public TipoLeito(Short idTipoLeito) {
        this.idTipoLeito = idTipoLeito;
    }

    public TipoLeito(Short idTipoLeito, String descricao) {
        this.idTipoLeito = idTipoLeito;
        this.descricao = descricao;
    }

    public Short getIdTipoLeito() {
        return idTipoLeito;
    }

    public void setIdTipoLeito(Short idTipoLeito) {
        this.idTipoLeito = idTipoLeito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Leito> getLeitoList() {
        return leitoList;
    }

    public void setLeitoList(List<Leito> leitoList) {
        this.leitoList = leitoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoLeito != null ? idTipoLeito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLeito)) {
            return false;
        }
        TipoLeito other = (TipoLeito) object;
        if ((this.idTipoLeito == null && other.idTipoLeito != null) || (this.idTipoLeito != null && !this.idTipoLeito.equals(other.idTipoLeito))) {
            return false;
        }
        return true;
    }  
}
