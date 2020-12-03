/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.models;

import hangntk.daos.PropertiesDAO;
import hangntk.dtos.PropertiesDTO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ShoppingCart2 {

    private String tribulationID;
    private Map<PropertiesDTO, Integer> cart;

    public String getTribulationID() {
        return tribulationID;
    }

    public void setTribulationID(String tribulationID) {
        this.tribulationID = tribulationID;
    }

    public Map<PropertiesDTO, Integer> getCart() {
        return cart;
    }

    public ShoppingCart2(String tribulationID, Map<PropertiesDTO, Integer> cart) {
        this.tribulationID = tribulationID;
        this.cart = cart;
    }

    public ShoppingCart2(String tribulationID) {
        this.tribulationID = tribulationID;
    }

    public void addProptoTribu(PropertiesDTO dto, int num) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        int quantity = num;
        if (this.cart.containsKey(dto)) {
            quantity = this.cart.get(dto) + num;
        }
        this.cart.put(dto, quantity);
    }

    public void removeProptoTribu(String id) throws Exception {
        if (this.cart == null) {
            return;
        }
        PropertiesDAO dao = new PropertiesDAO();
        PropertiesDTO dtoP = dao.findByPrimaryKey(id);
        if (this.cart.containsKey(dtoP)) {
            this.cart.remove(dtoP);
            if (this.cart.isEmpty()) {
                this.cart = null;
            }
        }
    }
}
