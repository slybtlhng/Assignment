/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.PropertiesDAO;
import hangntk.dtos.PropertiesDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class EditPropAction {

    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private PropertiesDTO dto;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public EditPropAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        PropertiesDAO dao = new PropertiesDAO();
        dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            url = SUCCESS;
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERROR", "Edit Failed");
        }
        return url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public String getKindOfSearch() {
        return kindOfSearch;
    }

    public void setKindOfSearch(String kindOfSearch) {
        this.kindOfSearch = kindOfSearch;
    }

    public PropertiesDTO getDto() {
        return dto;
    }

    public void setDto(PropertiesDTO dto) {
        this.dto = dto;
    }

}
