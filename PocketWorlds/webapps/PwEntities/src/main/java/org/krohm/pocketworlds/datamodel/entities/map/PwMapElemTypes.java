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
@Table(name = "pw_map_elem_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PwMapElemTypes.findAll", query = "SELECT p FROM PwMapElemTypes p"),
    @NamedQuery(name = "PwMapElemTypes.findByPwMapElemTypeId", query = "SELECT p FROM PwMapElemTypes p WHERE p.pwMapElemTypeId = :pwMapElemTypeId"),
    @NamedQuery(name = "PwMapElemTypes.findByPwMapElemTypeKey", query = "SELECT p FROM PwMapElemTypes p WHERE p.pwMapElemTypeKey = :pwMapElemTypeKey"),
    @NamedQuery(name = "PwMapElemTypes.findByPwMapElemTypeName", query = "SELECT p FROM PwMapElemTypes p WHERE p.pwMapElemTypeName = :pwMapElemTypeName")})
public class PwMapElemTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PW_MAP_ELEM_TYPE_ID")
    private Integer pwMapElemTypeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PW_MAP_ELEM_TYPE_KEY")
    private int pwMapElemTypeKey;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PW_MAP_ELEM_TYPE_NAME")
    private String pwMapElemTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pwMapElemTypeKey")
    private Collection<PwMapsElements> pwMapsElementsCollection;

    public PwMapElemTypes() {
    }

    public PwMapElemTypes(Integer pwMapElemTypeId) {
        this.pwMapElemTypeId = pwMapElemTypeId;
    }

    public PwMapElemTypes(Integer pwMapElemTypeId, int pwMapElemTypeKey, String pwMapElemTypeName) {
        this.pwMapElemTypeId = pwMapElemTypeId;
        this.pwMapElemTypeKey = pwMapElemTypeKey;
        this.pwMapElemTypeName = pwMapElemTypeName;
    }

    public Integer getPwMapElemTypeId() {
        return pwMapElemTypeId;
    }

    public void setPwMapElemTypeId(Integer pwMapElemTypeId) {
        this.pwMapElemTypeId = pwMapElemTypeId;
    }

    public int getPwMapElemTypeKey() {
        return pwMapElemTypeKey;
    }

    public void setPwMapElemTypeKey(int pwMapElemTypeKey) {
        this.pwMapElemTypeKey = pwMapElemTypeKey;
    }

    public String getPwMapElemTypeName() {
        return pwMapElemTypeName;
    }

    public void setPwMapElemTypeName(String pwMapElemTypeName) {
        this.pwMapElemTypeName = pwMapElemTypeName;
    }

    @XmlTransient
    public Collection<PwMapsElements> getPwMapsElementsCollection() {
        return pwMapsElementsCollection;
    }

    public void setPwMapsElementsCollection(Collection<PwMapsElements> pwMapsElementsCollection) {
        this.pwMapsElementsCollection = pwMapsElementsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pwMapElemTypeId != null ? pwMapElemTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PwMapElemTypes)) {
            return false;
        }
        PwMapElemTypes other = (PwMapElemTypes) object;
        if ((this.pwMapElemTypeId == null && other.pwMapElemTypeId != null) || (this.pwMapElemTypeId != null && !this.pwMapElemTypeId.equals(other.pwMapElemTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.krohm.pocketworlds.datamodel.entities.map.PwMapElemTypes[ pwMapElemTypeId=" + pwMapElemTypeId + " ]";
    }
    
}
