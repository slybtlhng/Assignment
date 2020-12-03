/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.UserDAO;
import hangntk.dtos.UserDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class EditRecordAction {

    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private UserDTO dto;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public EditRecordAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        UserDAO dao = new UserDAO();
        dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            url = SUCCESS;
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Edit Failed");
        }
        return url;
    }

    public String getKindOfSearch() {
        return kindOfSearch;
    }

    public void setKindOfSearch(String kindOfSearch) {
        this.kindOfSearch = kindOfSearch;
    }

    public UserDTO getDto() {
        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

}
