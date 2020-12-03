/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.PartDAO;
import hangntk.daos.TribulationDAO;
import hangntk.daos.UserDAO;
import hangntk.dtos.PartDTO;
import hangntk.dtos.UserDTO;
import hangntk.models.ShoppingCart1;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class AddCastToPartAction {

    private String cast;
    private String part;
    private static final String SUCCESS = "success";
    List<UserDTO> listCast;
    private Map<String, String> listPart;
    private String tribulationName;
    private String done;

    public AddCastToPartAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ShoppingCart1 cart = (ShoppingCart1) session.get("CART1");
        if (cart == null) {
            cart = new ShoppingCart1();
        }
        UserDAO dao = new UserDAO();
        UserDTO dto = dao.findByPrimaryKey(cast);
        PartDAO daoP = new PartDAO();
        System.out.println("hiiiiiiiiiiiiiii"+part);
        PartDTO dtoP = daoP.findByPrimaryKey(part);
        cart.addCasttoPart(dtoP, dto);
        session.put("CART1", cart);

        TribulationDAO daoTribu = new TribulationDAO();

        listCast = dao.loadListCast();
        listPart = daoP.loadListPart(dtoP.getTribulationID());
        tribulationName = daoTribu.findByPrimaryKey(dtoP.getTribulationID()).getTribulationName();
        done = "Add Cast to Part success";
        return SUCCESS;
    }

    public String getDone() {
        return done;
    }

    public String getTribulationName() {
        return tribulationName;
    }

    public List<UserDTO> getListCast() {
        return listCast;
    }

    public Map<String, String> getListPart() {
        return listPart;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

}
