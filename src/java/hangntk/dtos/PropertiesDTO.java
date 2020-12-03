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
public class PropertiesDTO implements Serializable{
    private String propID,propName,propImage,propDescription;
    private int propQuanity;

    public PropertiesDTO(String propID, String propName, String propImage, String propDescription, int propQuanity) {
        this.propID = propID;
        this.propName = propName;
        this.propImage = propImage;
        this.propDescription = propDescription;
        this.propQuanity = propQuanity;
    }

    public PropertiesDTO(String propName, String propImage, String propDescription, int propQuanity) {
        this.propName = propName;
        this.propImage = propImage;
        this.propDescription = propDescription;
        this.propQuanity = propQuanity;
    }

    public String getPropID() {
        return propID;
    }

    public void setPropID(String propID) {
        this.propID = propID;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropImage() {
        return propImage;
    }

    public void setPropImage(String propImage) {
        this.propImage = propImage;
    }

    public String getPropDescription() {
        return propDescription;
    }

    public void setPropDescription(String propDescription) {
        this.propDescription = propDescription;
    }

    public int getPropQuanity() {
        return propQuanity;
    }

    public void setPropQuanity(int propQuanity) {
        this.propQuanity = propQuanity;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof PropertiesDTO)){
            return false;
        }
        PropertiesDTO dto=(PropertiesDTO) o;
        
        if((this.propID==null && dto.propID!=null) || (this.propID!=null && !this.propID.equals(dto.propID))){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash=0;
        hash+=(propID!=null?propID.hashCode():0);
        return hash;
    }
    
    
}
