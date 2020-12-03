/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.CastRegistrationDAO;
import hangntk.dtos.PartDTO;
import hangntk.dtos.UserDTO;
import hangntk.models.ShoppingCart1;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class SaveCart1Action {

    private static final String SUCCESS = "success";
    private String done;
    private String notdone;

    public SaveCart1Action() {
    }

    public String execute() throws Exception {

        CastRegistrationDAO dao = new CastRegistrationDAO();
        Map session = ActionContext.getContext().getSession();
        ShoppingCart1 cart = (ShoppingCart1) session.get("CART1");
        UserDTO dto = (UserDTO) session.get("INFO");

        dao.saveRegistration(cart.getCart(), dto.getUserID());
        session.put("CART1", null);

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("ERROR", "Can't save");
        done="Save success!!";
        return SUCCESS;
    }

    public String getDone() {
        return done;
    }

    public String getNotdone() {
        return notdone;
    }

}
