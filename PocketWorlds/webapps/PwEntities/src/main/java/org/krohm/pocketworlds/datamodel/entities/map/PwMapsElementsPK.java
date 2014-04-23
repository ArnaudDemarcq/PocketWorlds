/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.datamodel.entities.map;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Arnaud
 */
@Embeddable
public class PwMapsElementsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PW_MAP_ID")
    private int pwMapId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "X_POSITION")
    private int xPosition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Y_POSITION")
    private int yPosition;

    public PwMapsElementsPK() {
    }

    public PwMapsElementsPK(int pwMapId, int xPosition, int yPosition) {
        this.pwMapId = pwMapId;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getPwMapId() {
        return pwMapId;
    }

    public void setPwMapId(int pwMapId) {
        this.pwMapId = pwMapId;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pwMapId;
        hash += (int) xPosition;
        hash += (int) yPosition;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PwMapsElementsPK)) {
            return false;
        }
        PwMapsElementsPK other = (PwMapsElementsPK) object;
        if (this.pwMapId != other.pwMapId) {
            return false;
        }
        if (this.xPosition != other.xPosition) {
            return false;
        }
        if (this.yPosition != other.yPosition) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.krohm.pocketworlds.datamodel.entities.map.PwMapsElementsPK[ pwMapId=" + pwMapId + ", xPosition=" + xPosition + ", yPosition=" + yPosition + " ]";
    }
    
}
