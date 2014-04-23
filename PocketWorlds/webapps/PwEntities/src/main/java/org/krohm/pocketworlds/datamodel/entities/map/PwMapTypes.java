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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pw_map_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PwMapTypes.findAll", query = "SELECT p FROM PwMapTypes p"),
    @NamedQuery(name = "PwMapTypes.findByPwMapTypeId", query = "SELECT p FROM PwMapTypes p WHERE p.pwMapTypeId = :pwMapTypeId"),
    @NamedQuery(name = "PwMapTypes.findByPwMapTypeKey", query = "SELECT p FROM PwMapTypes p WHERE p.pwMapTypeKey = :pwMapTypeKey"),
    @NamedQuery(name = "PwMapTypes.findByPwMapTypeName", query = "SELECT p FROM PwMapTypes p WHERE p.pwMapTypeName = :pwMapTypeName")})
public class PwMapTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PW_MAP_TYPE_ID")
    private Integer pwMapTypeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PW_MAP_TYPE_KEY")
    private int pwMapTypeKey;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PW_MAP_TYPE_NAME")
    private String pwMapTypeName;
    @JoinColumn(name = "PW_MAP_TYPE_LEVEL_KEY", referencedColumnName = "PW_MAP_TYPE_LEVEL_KEY")
    @ManyToOne(optional = false)
    private PwMapTypeLevels pwMapTypeLevelKey;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pwMapTypeKey")
    private Collection<PwMaps> pwMapsCollection;

    public PwMapTypes() {
    }

    public PwMapTypes(Integer pwMapTypeId) {
        this.pwMapTypeId = pwMapTypeId;
    }

    public PwMapTypes(Integer pwMapTypeId, int pwMapTypeKey, String pwMapTypeName) {
        this.pwMapTypeId = pwMapTypeId;
        this.pwMapTypeKey = pwMapTypeKey;
        this.pwMapTypeName = pwMapTypeName;
    }

    public Integer getPwMapTypeId() {
        return pwMapTypeId;
    }

    public void setPwMapTypeId(Integer pwMapTypeId) {
        this.pwMapTypeId = pwMapTypeId;
    }

    public int getPwMapTypeKey() {
        return pwMapTypeKey;
    }

    public void setPwMapTypeKey(int pwMapTypeKey) {
        this.pwMapTypeKey = pwMapTypeKey;
    }

    public String getPwMapTypeName() {
        return pwMapTypeName;
    }

    public void setPwMapTypeName(String pwMapTypeName) {
        this.pwMapTypeName = pwMapTypeName;
    }

    public PwMapTypeLevels getPwMapTypeLevelKey() {
        return pwMapTypeLevelKey;
    }

    public void setPwMapTypeLevelKey(PwMapTypeLevels pwMapTypeLevelKey) {
        this.pwMapTypeLevelKey = pwMapTypeLevelKey;
    }

    @XmlTransient
    public Collection<PwMaps> getPwMapsCollection() {
        return pwMapsCollection;
    }

    public void setPwMapsCollection(Collection<PwMaps> pwMapsCollection) {
        this.pwMapsCollection = pwMapsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pwMapTypeId != null ? pwMapTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PwMapTypes)) {
            return false;
        }
        PwMapTypes other = (PwMapTypes) object;
        if ((this.pwMapTypeId == null && other.pwMapTypeId != null) || (this.pwMapTypeId != null && !this.pwMapTypeId.equals(other.pwMapTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.krohm.pocketworlds.datamodel.entities.map.PwMapTypes[ pwMapTypeId=" + pwMapTypeId + " ]";
    }
    
}
