/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.TribulationDAO;
import hangntk.daos.TribulationPropertiesDAO;
import hangntk.dtos.PropertiesDTO;
import hangntk.dtos.TribulationDTO;
import hangntk.dtos.UserDTO;
import hangntk.models.ShoppingCart2;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class SaveCart2Action {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private String done;
    private String notdone;

    public SaveCart2Action() {
    }

    public String execute() throws Exception {
        TribulationPropertiesDAO dao = new TribulationPropertiesDAO();
        Map session = ActionContext.getContext().getSession();
        ShoppingCart2 cart = (ShoppingCart2) session.get("CART2");
        UserDTO dto = (UserDTO) session.get("INFO");
        HttpServletRequest request = ServletActionContext.getRequest();

        for (Map.Entry<PropertiesDTO, Integer> entry : cart.getCart().entrySet()) {
            PropertiesDTO key = entry.getKey();
            Integer value = entry.getValue();
            if (value > key.getPropQuanity()) {
                notdone="The quantity you want exceeds the allowed amount";
                return ERROR;
            }
        }
        TribulationDAO daoTribu = new TribulationDAO();
        TribulationDTO dtoTribu = daoTribu.findByPrimaryKey(cart.getTribulationID());

        dao.saveTribuProp(cart.getCart(), dto.getUserID(), cart.getTribulationID(), dtoTribu.getTribulationStartTime(), dtoTribu.getTribulationEndTime());
        session.put("CART2", null);

        request.setAttribute("ERROR", "Can't save");
        done = "Save success";
        return SUCCESS;
    }

    public String getDone() {
        return done;
    }

    public String getNotdone() {
        return notdone;
    }

}
