/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.PropertiesDAO;
import hangntk.models.ShoppingCart2;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class DeletePropToTribuAction {

    private String id;
    private String tribulationID;
    private static final String SUCCESS = "success";

    public DeletePropToTribuAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ShoppingCart2 cart = (ShoppingCart2) session.get("CART2");
        if (cart == null) {
            cart = new ShoppingCart2(tribulationID);
        }
        cart.removeProptoTribu(id);
        session.put("CART2", cart);
        if (cart.getCart() == null) {
            session.put("CART2", null);
        }
        return SUCCESS;
    }

    public String getTribulationID() {
        return tribulationID;
    }

    public void setTribulationID(String tribulationID) {
        this.tribulationID = tribulationID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
