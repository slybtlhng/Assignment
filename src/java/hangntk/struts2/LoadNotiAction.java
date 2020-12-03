/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import hangntk.daos.NotificationDAO;
import hangntk.dtos.NotificationDTO;
import hangntk.dtos.UserDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class LoadNotiAction {

    private List<NotificationDTO> list;

    public LoadNotiAction() {
    }

    public String execute() throws Exception {
        NotificationDAO dao = new NotificationDAO();
        Map session = ActionContext.getContext().getSession();
        UserDTO dto = (UserDTO) session.get("INFO");
        list = dao.findByUserID(dto.getUserID());
        return "success";
    }

    public List<NotificationDTO> getList() {
        return list;
    }

    public void setList(List<NotificationDTO> list) {
        this.list = list;
    }

}
