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
@Table(name = "pw_maps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PwMaps.findAll", query = "SELECT p FROM PwMaps p"),
    @NamedQuery(name = "PwMaps.findByPwMapId", query = "SELECT p FROM PwMaps p WHERE p.pwMapId = :pwMapId"),
    @NamedQuery(name = "PwMaps.findByPwMapName", query = "SELECT p FROM PwMaps p WHERE p.pwMapName = :pwMapName"),
    @NamedQuery(name = "PwMaps.findByPwMapSizeX", query = "SELECT p FROM PwMaps p WHERE p.pwMapSizeX = :pwMapSizeX"),
    @NamedQuery(name = "PwMaps.findByPwMapSizeY", query = "SELECT p FROM PwMaps p WHERE p.pwMapSizeY = :pwMapSizeY")})
public class PwMaps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PW_MAP_ID")
    private Integer pwMapId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PW_MAP_NAME")
    private String pwMapName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PW_MAP_SIZE_X")
    private int pwMapSizeX;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PW_MAP_SIZE_Y")
    private int pwMapSizeY;
    @OneToMany(mappedBy = "pwMapParentId")
    private Collection<PwMaps> pwMapsCollection;
    @JoinColumn(name = "PW_MAP_PARENT_ID", referencedColumnName = "PW_MAP_ID")
    @ManyToOne
    private PwMaps pwMapParentId;
    @JoinColumn(name = "PW_MAP_TYPE_KEY", referencedColumnName = "PW_MAP_TYPE_KEY")
    @ManyToOne(optional = false)
    private PwMapTypes pwMapTypeKey;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pwMaps")
    private Collection<PwMapsElements> pwMapsElementsCollection;

    public PwMaps() {
    }

    public PwMaps(Integer pwMapId) {
        this.pwMapId = pwMapId;
    }

    public PwMaps(Integer pwMapId, String pwMapName, int pwMapSizeX, int pwMapSizeY) {
        this.pwMapId = pwMapId;
        this.pwMapName = pwMapName;
        this.pwMapSizeX = pwMapSizeX;
        this.pwMapSizeY = pwMapSizeY;
    }

    public Integer getPwMapId() {
        return pwMapId;
    }

    public void setPwMapId(Integer pwMapId) {
        this.pwMapId = pwMapId;
    }

    public String getPwMapName() {
        return pwMapName;
    }

    public void setPwMapName(String pwMapName) {
        this.pwMapName = pwMapName;
    }

    public int getPwMapSizeX() {
        return pwMapSizeX;
    }

    public void setPwMapSizeX(int pwMapSizeX) {
        this.pwMapSizeX = pwMapSizeX;
    }

    public int getPwMapSizeY() {
        return pwMapSizeY;
    }

    public void setPwMapSizeY(int pwMapSizeY) {
        this.pwMapSizeY = pwMapSizeY;
    }

    @XmlTransient
    public Collection<PwMaps> getPwMapsCollection() {
        return pwMapsCollection;
    }

    public void setPwMapsCollection(Collection<PwMaps> pwMapsCollection) {
        this.pwMapsCollection = pwMapsCollection;
    }

    public PwMaps getPwMapParentId() {
        return pwMapParentId;
    }

    public void setPwMapParentId(PwMaps pwMapParentId) {
        this.pwMapParentId = pwMapParentId;
    }

    public PwMapTypes getPwMapTypeKey() {
        return pwMapTypeKey;
    }

    public void setPwMapTypeKey(PwMapTypes pwMapTypeKey) {
        this.pwMapTypeKey = pwMapTypeKey;
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
        hash += (pwMapId != null ? pwMapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PwMaps)) {
            return false;
        }
        PwMaps other = (PwMaps) object;
        if ((this.pwMapId == null && other.pwMapId != null) || (this.pwMapId != null && !this.pwMapId.equals(other.pwMapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.krohm.pocketworlds.datamodel.entities.map.PwMaps[ pwMapId=" + pwMapId + " ]";
    }
    
}
