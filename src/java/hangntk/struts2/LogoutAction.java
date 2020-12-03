/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class LogoutAction {

    private static final String SUCCESS = "success";

    public LogoutAction() {
    }

    public String execute() throws Exception {
        
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();

        session.clear();
        return SUCCESS;
    }

}
