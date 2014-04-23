/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.datamodel.entities.map;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arnaud
 */
@Entity
@Table(name = "pw_map_type_levels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PwMapTypeLevels.findAll", query = "SELECT p FROM PwMapTypeLevels p"),
    @NamedQuery(name = "PwMapTypeLevels.findByPwMapTypeLevelId", query = "SELECT p FROM PwMapTypeLevels p WHERE p.pwMapTypeLevelId = :pwMapTypeLevelId"),
    @NamedQuery(name = "PwMapTypeLevels.findByPwMapTypeLevelKey", query = "SELECT p FROM PwMapTypeLevels p WHERE p.pwMapTypeLevelKey = :pwMapTypeLevelKey"),
    @NamedQuery(name = "PwMapTypeLevels.findByPwMapTypeLevelName", query = "SELECT p FROM PwMapTypeLevels p WHERE p.pwMapTypeLevelName = :pwMapTypeLevelName")})
public class PwMapTypeLevels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PW_MAP_TYPE_LEVEL_ID")
    private Integer pwMapTypeLevelId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PW_MAP_TYPE_LEVEL_KEY")
    private int pwMapTypeLevelKey;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PW_MAP_TYPE_LEVEL_NAME")
    private String pwMapTypeLevelName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pwMapTypeLevelKey")
    private Collection<PwMapTypes> pwMapTypesCollection;

    public PwMapTypeLevels() {
    }

    public PwMapTypeLevels(Integer pwMapTypeLevelId) {
        this.pwMapTypeLevelId = pwMapTypeLevelId;
    }

    public PwMapTypeLevels(Integer pwMapTypeLevelId, int pwMapTypeLevelKey, String pwMapTypeLevelName) {
        this.pwMapTypeLevelId = pwMapTypeLevelId;
        this.pwMapTypeLevelKey = pwMapTypeLevelKey;
        this.pwMapTypeLevelName = pwMapTypeLevelName;
    }

    public Integer getPwMapTypeLevelId() {
        return pwMapTypeLevelId;
    }

    public void setPwMapTypeLevelId(Integer pwMapTypeLevelId) {
        this.pwMapTypeLevelId = pwMapTypeLevelId;
    }

    public int getPwMapTypeLevelKey() {
        return pwMapTypeLevelKey;
    }

    public void setPwMapTypeLevelKey(int pwMapTypeLevelKey) {
        this.pwMapTypeLevelKey = pwMapTypeLevelKey;
    }

    public String getPwMapTypeLevelName() {
        return pwMapTypeLevelName;
    }

    public void setPwMapTypeLevelName(String pwMapTypeLevelName) {
        this.pwMapTypeLevelName = pwMapTypeLevelName;
    }

    @XmlTransient
    public Collection<PwMapTypes> getPwMapTypesCollection() {
        return pwMapTypesCollection;
    }

    public void setPwMapTypesCollection(Collection<PwMapTypes> pwMapTypesCollection) {
        this.pwMapTypesCollection = pwMapTypesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pwMapTypeLevelId != null ? pwMapTypeLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PwMapTypeLevels)) {
            return false;
        }
        PwMapTypeLevels other = (PwMapTypeLevels) object;
        if ((this.pwMapTypeLevelId == null && other.pwMapTypeLevelId != null) || (this.pwMapTypeLevelId != null && !this.pwMapTypeLevelId.equals(other.pwMapTypeLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.krohm.pocketworlds.datamodel.entities.map.PwMapTypeLevels[ pwMapTypeLevelId=" + pwMapTypeLevelId + " ]";
    }
    
}
