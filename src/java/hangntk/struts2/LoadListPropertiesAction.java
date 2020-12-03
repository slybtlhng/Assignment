/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.PropertiesDAO;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class LoadListPropertiesAction {
    Map<String, String> listProp;
    
    public LoadListPropertiesAction() {
    }
    
    public String execute() throws Exception {
        PropertiesDAO dao=new PropertiesDAO();
        
        listProp=dao.loadListProp();
        
        return "none";
    }

    public Map<String, String> getListProp() {
        return listProp;
    }

    public void setListProp(Map<String, String> listProp) {
        this.listProp = listProp;
    }
    
}
