/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.PropertiesDAO;
import hangntk.daos.TribulationDAO;
import hangntk.dtos.PropertiesDTO;
import hangntk.dtos.UserDTO;
import hangntk.models.ShoppingCart2;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class AddPropToTribuAction {

    private String prop;
    private String tribulation;
    private static final String SUCCESS = "success";
    private Map<String, String> listTribulation;
    private List<PropertiesDTO> listProp;
    private String tribulationName;
    private String quantity;
    private String done;
    private String notdone;

    public AddPropToTribuAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ShoppingCart2 cart = (ShoppingCart2) session.get("CART2");
        if (cart == null) {
            cart = new ShoppingCart2(tribulation);
        }
        PropertiesDAO dao = new PropertiesDAO();
        PropertiesDTO dto = dao.findByPrimaryKey(prop);
        listProp = dao.findByStatus(1);

        if (dto.getPropQuanity() - new Integer(quantity) < 0) {
            notdone = "The quantity you want exceeds the allowed amount";
            return SUCCESS;
        }
        cart.addProptoTribu(dto, new Integer(quantity));
        session.put("CART2", cart);

        TribulationDAO daoTribu = new TribulationDAO();
        UserDTO dtoU = (UserDTO) session.get("INFO");
        listTribulation = daoTribu.loadListTribulation(dtoU.getUserID());
        tribulationName = daoTribu.findByPrimaryKey(tribulation).getTribulationName();
        session.put("TRIBULATION", tribulation);
        done = "Add success";
        return SUCCESS;
    }

    public String getNotdone() {
        return notdone;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDone() {
        return done;
    }

    public String getTribulationName() {
        return tribulationName;
    }

    public void setTribulationName(String tribulationName) {
        this.tribulationName = tribulationName;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getTribulation() {
        return tribulation;
    }

    public void setTribulation(String tribulation) {
        this.tribulation = tribulation;
    }

    public Map<String, String> getListTribulation() {
        return listTribulation;
    }

    public void setListTribulation(Map<String, String> listTribulation) {
        this.listTribulation = listTribulation;
    }

    public List<PropertiesDTO> getListProp() {
        return listProp;
    }

    public void setListProp(List<PropertiesDTO> listProp) {
        this.listProp = listProp;
    }

}
