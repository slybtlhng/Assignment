/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.models;

import hangntk.dtos.PartDTO;
import hangntk.dtos.UserDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ShoppingCart1 implements Serializable{
    private String tribulationID;
    private Map<PartDTO,UserDTO> cart;

    public String getTribulationID() {
        return tribulationID;
    }

    public void setTribulationID(String tribulationID) {
        this.tribulationID = tribulationID;
    }

    public Map<PartDTO, UserDTO> getCart() {
        return cart;
    }

    public ShoppingCart1() {
    }

    public void addCasttoPart(PartDTO dtoP,UserDTO dtoU){
        if(this.cart==null){
            this.cart =new HashMap<>();
        }
        this.cart.put(dtoP, dtoU);
    }
    
    public void removeCasttoPart(PartDTO dtoP){
        if(this.cart==null){
            return;
        }
        if(this.cart.containsKey(dtoP)){
            this.cart.remove(dtoP);
            if(this.cart.isEmpty()){
                this.cart=null;
            }
        }
    }
}
