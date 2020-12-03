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
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class LoadListTribulationAction {

    List<PropertiesDTO> listProp;
    Map<String, String> listTribulation;
    private String tribulationName;
    private String id;

    public LoadListTribulationAction() throws Exception {

    }

    public String execute() throws Exception {
        TribulationDAO dao = new TribulationDAO();
        listTribulation = dao.loadListTribulation(id);
        return "addCast";
    }

    public String displayAddProp() throws Exception {
        PropertiesDAO daoPro = new PropertiesDAO();
        listProp = daoPro.findByStatus(1);
        TribulationDAO dao = new TribulationDAO();
        Map session = ActionContext.getContext().getSession();
        UserDTO dto=(UserDTO) session.get("INFO");
        listTribulation = dao.loadListTribulation(dto.getUserID());
        String tribulation = (String) session.get("TRIBULATION");
        if (tribulation != null) {
            tribulationName = dao.findByPrimaryKey(tribulation).getTribulationName();
        }
        return "addProp";
    }

    public Map<String, String> getListTribulation() {
        return listTribulation;
    }

    public String getTribulationName() {
        return tribulationName;
    }

    public void setListTribulation(Map<String, String> listTribulation) {
        this.listTribulation = listTribulation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PropertiesDTO> getListProp() {
        return listProp;
    }

    public void setListProp(List<PropertiesDTO> listProp) {
        this.listProp = listProp;
    }

  

}
