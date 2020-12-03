/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.UserDAO;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class LoadListDirectorAction {
    
    
    Map<String,String> listDirector;
    
    public LoadListDirectorAction() {
    }
    
    public String execute() throws Exception {
        UserDAO dao=new UserDAO();
        
        listDirector=dao.loadListDirector();

        return "regist";
    }
    
    public String displayUpdate() throws Exception {
        UserDAO dao=new UserDAO();
        
        listDirector=dao.loadListDirector();
        
        return "update";
    }

 
    public Map<String,String> getListDirector() {
        return listDirector;
    }
    
    
}
