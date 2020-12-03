/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.dtos;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class CastRegistrationDTO implements Serializable{
    private String castID,partID,directorID;

    public String getCastID() {
        return castID;
    }

    public void setCastID(String castID) {
        this.castID = castID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public CastRegistrationDTO(String castID, String partID, String directorID) {
        this.castID = castID;
        this.partID = partID;
        this.directorID = directorID;
    }
    
}
