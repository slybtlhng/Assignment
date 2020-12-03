/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import hangntk.daos.UserDAO;
import hangntk.dtos.UserDTO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class LoginAction extends ActionSupport {

    private String username;
    private String password;
    private final String ERROR = "error";
    private final String ADMIN = "admin";
    private final String CAST = "cast";
    private final String DIRECTOR = "director";

    public LoginAction() {
    }

    public String execute() throws Exception {
        UserDAO dao = new UserDAO();
        String url = ERROR;
        UserDTO dto = dao.checkLogin(username, password);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (dto == null) {
            request.setAttribute("ERROR", "Invalid UserName or Password");
        } else {
            Map session = ActionContext.getContext().getSession();
            session.put("INFO", dto);
            if (dto.getRoleName().equals("Admin")) {
                url = ADMIN;
            } else if (dto.getRoleName().equals("Cast")) {
                url = CAST;
            } else if (dto.getRoleName().equals("Director")) {
                url = DIRECTOR;
            } else {
                request.setAttribute("ERROR", "Your role is invald");
            }
        }
        return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
