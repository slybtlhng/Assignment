/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.TribulationDAO;
import hangntk.daos.UserDAO;
import hangntk.dtos.TribulationDTO;
import hangntk.dtos.UserDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class EditTribulationAction {

    private String id;
    private String lastSearchValue;
    private String kindOfSearch;

    private String starTime;
    private String endTime;

    private TribulationDTO dto;

    private final String SUCCESS = "success";
    private final String FAIL = "error";

    public EditTribulationAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        TribulationDAO dao = new TribulationDAO();
        dto = dao.findByPrimaryKey(id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        starTime = format.format(dto.getTribulationStartTime());
        endTime = format.format(dto.getTribulationEndTime());
        if (dto != null) {
            url = SUCCESS;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("ERROR", "Edit Failed");
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

    public TribulationDTO getDto() {
        return dto;
    }

    public void setDto(TribulationDTO dto) {
        this.dto = dto;
    }

    public String getStarTime() {
        return starTime;
    }

    public String getEndTime() {
        return endTime;
    }

}
