/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class TribulationPropertiesDTO implements Serializable {

    private String tribulationID, propID, directorID;
    private int propQuantity;
    private Date timeStart;
    private Date timeEnd;
    private String tribulationName;
    private String propName;

    public String getTribulationName() {
        return tribulationName;
    }

    public void setTribulationName(String tribulationName) {
        this.tribulationName = tribulationName;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }
    
    public String getTribulationID() {
        return tribulationID;
    }

    public void setTribulationID(String tribulationID) {
        this.tribulationID = tribulationID;
    }

    public String getPropID() {
        return propID;
    }

    public void setPropID(String propID) {
        this.propID = propID;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public int getPropQuantity() {
        return propQuantity;
    }

    public void setPropQuantity(int propQuantity) {
        this.propQuantity = propQuantity;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public TribulationPropertiesDTO(String tribulationID, String propID, String directorID, int propQuantity, Date timeStart, Date timeEnd) {
        this.tribulationID = tribulationID;
        this.propID = propID;
        this.directorID = directorID;
        this.propQuantity = propQuantity;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

}
