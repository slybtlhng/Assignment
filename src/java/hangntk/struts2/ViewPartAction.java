/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.PartDAO;
import hangntk.dtos.PartDTO;
import hangntk.dtos.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ViewPartAction {

    private String idTribulation;
    private String nameTribulation;
    private List<PartDTO> listPart;

    public ViewPartAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        UserDTO dtoUser = (UserDTO) session.get("INFO");
        String castID = dtoUser.getUserID();

        PartDAO daoPart = new PartDAO();
        List<String> listPartID = daoPart.findPartbyCastIDandTribulationID(castID, idTribulation);
        listPart = new ArrayList<>();
        for (String partID : listPartID) {
            listPart.add(daoPart.findByPrimaryKey(partID));
        }
        return "success";
    }

    public String getIdTribulation() {
        return idTribulation;
    }

    public void setIdTribulation(String idTribulation) {
        this.idTribulation = idTribulation;
    }

    public List<PartDTO> getListPart() {
        return listPart;
    }

    public String getNameTribulation() {
        return nameTribulation;
    }

    public void setNameTribulation(String nameTribulation) {
        this.nameTribulation = nameTribulation;
    }

}
