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
public class PartDTO implements Serializable {

    private String partID,
            partName,
            partDescription,
            tribulationID;

    public PartDTO(String partID, String partName, String partDescription, String tribulationID) {
        this.partID = partID;
        this.partName = partName;
        this.partDescription = partDescription;
        this.tribulationID = tribulationID;
    }

    public PartDTO(String partName, String partDescription, String tribulationID) {
        this.partName = partName;
        this.partDescription = partDescription;
        this.tribulationID = tribulationID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getTribulationID() {
        return tribulationID;
    }

    public void setTribulationID(String tribulationID) {
        this.tribulationID = tribulationID;
    }
    
        @Override
    public boolean equals(Object o) {
        if(!(o instanceof PartDTO)){
            return false;
        }
        PartDTO dto=(PartDTO) o;
        
        if((this.partID==null && dto.partID!=null) || (this.partID!=null && !this.partID.equals(dto.partID))){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash=0;
        hash+=(partID!=null?partID.hashCode():0);
        return hash;
    }
}
