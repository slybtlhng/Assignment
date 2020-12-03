/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.PartDAO;
import hangntk.daos.TribulationDAO;
import hangntk.daos.UserDAO;
import hangntk.dtos.UserDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class LoadListPartandCastAction {
    List<UserDTO> listCast;
    Map<String, String> listPart;
    
    private String tribulationID;//id cua tribu
    private String tribulationName;
    
    public LoadListPartandCastAction() {
    }
    
    public String execute() throws Exception {
        UserDAO dao = new UserDAO();
        listCast = dao.loadListCast();
        PartDAO daoPart=new PartDAO();
        listPart =daoPart.loadListPart(tribulationID);
        TribulationDAO daoTri=new TribulationDAO();
        tribulationName=daoTri.findByPrimaryKey(tribulationID).getTribulationName();
        return "none";
    }

    public List<UserDTO> getListCast() {
        return listCast;
    }

    public void setListCast(List<UserDTO> listCast) {
        this.listCast = listCast;
    }

    public Map<String, String> getListPart() {
        return listPart;
    }

    public void setListPart(Map<String, String> listPart) {
        this.listPart = listPart;
    }

    public String getTribulationID() {
        return tribulationID;
    }

    public void setTribulationID(String tribulationID) {
        this.tribulationID = tribulationID;
    }

    public String getTribulationName() {
        return tribulationName;
    }

    public void setTribulationName(String tribulationName) {
        this.tribulationName = tribulationName;
    }



    
}
