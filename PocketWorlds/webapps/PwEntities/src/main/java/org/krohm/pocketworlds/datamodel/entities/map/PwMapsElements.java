/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.datamodel.entities.map;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arnaud
 */
@Entity
@Table(name = "pw_maps_elements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PwMapsElements.findAll", query = "SELECT p FROM PwMapsElements p"),
    @NamedQuery(name = "PwMapsElements.findByPwMapId", query = "SELECT p FROM PwMapsElements p WHERE p.pwMapsElementsPK.pwMapId = :pwMapId"),
    @NamedQuery(name = "PwMapsElements.findByXPosition", query = "SELECT p FROM PwMapsElements p WHERE p.pwMapsElementsPK.xPosition = :xPosition"),
    @NamedQuery(name = "PwMapsElements.findByYPosition", query = "SELECT p FROM PwMapsElements p WHERE p.pwMapsElementsPK.yPosition = :yPosition")})
public class PwMapsElements implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PwMapsElementsPK pwMapsElementsPK;
    @JoinColumn(name = "PW_MAP_ELEM_TYPE_KEY", referencedColumnName = "PW_MAP_ELEM_TYPE_KEY")
    @ManyToOne(optional = false)
    private PwMapElemTypes pwMapElemTypeKey;
    @JoinColumn(name = "PW_MAP_ID", referencedColumnName = "PW_MAP_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PwMaps pwMaps;

    public PwMapsElements() {
    }

    public PwMapsElements(PwMapsElementsPK pwMapsElementsPK) {
        this.pwMapsElementsPK = pwMapsElementsPK;
    }

    public PwMapsElements(int pwMapId, int xPosition, int yPosition) {
        this.pwMapsElementsPK = new PwMapsElementsPK(pwMapId, xPosition, yPosition);
    }

    public PwMapsElementsPK getPwMapsElementsPK() {
        return pwMapsElementsPK;
    }

    public void setPwMapsElementsPK(PwMapsElementsPK pwMapsElementsPK) {
        this.pwMapsElementsPK = pwMapsElementsPK;
    }

    public PwMapElemTypes getPwMapElemTypeKey() {
        return pwMapElemTypeKey;
    }

    public void setPwMapElemTypeKey(PwMapElemTypes pwMapElemTypeKey) {
        this.pwMapElemTypeKey = pwMapElemTypeKey;
    }

    public PwMaps getPwMaps() {
        return pwMaps;
    }

    public void setPwMaps(PwMaps pwMaps) {
        this.pwMaps = pwMaps;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pwMapsElementsPK != null ? pwMapsElementsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PwMapsElements)) {
            return false;
        }
        PwMapsElements other = (PwMapsElements) object;
        if ((this.pwMapsElementsPK == null && other.pwMapsElementsPK != null) || (this.pwMapsElementsPK != null && !this.pwMapsElementsPK.equals(other.pwMapsElementsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.krohm.pocketworlds.datamodel.entities.map.PwMapsElements[ pwMapsElementsPK=" + pwMapsElementsPK + " ]";
    }
    
}
