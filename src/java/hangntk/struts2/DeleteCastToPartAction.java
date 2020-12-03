/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.PartDAO;
import hangntk.dtos.PartDTO;
import hangntk.models.ShoppingCart1;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class DeleteCastToPartAction {

    private String id;
    private String tribulationID;
    private static final String SUCCESS = "success";

    public DeleteCastToPartAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ShoppingCart1 cart = (ShoppingCart1) session.get("CART1");
        if (cart == null) {
            cart = new ShoppingCart1();
        }
        PartDAO dao = new PartDAO();
        PartDTO dto = dao.findByPrimaryKey(id);
        cart.removeCasttoPart(dto);
        session.put("CART1", cart);
        if (cart.getCart() == null) {
            session.put("CART1", null);
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
