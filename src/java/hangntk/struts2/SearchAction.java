/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.PropertiesDAO;
import hangntk.daos.TribulationDAO;
import hangntk.daos.UserDAO;
import hangntk.dtos.PropertiesDTO;
import hangntk.dtos.TribulationDTO;
import hangntk.dtos.UserDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class SearchAction {

    private String kindOfSearch;
    private String searchValue;
    private final String SUCCESS = "success";
    private final String ERROR = "error";
    private List<UserDTO> listAccount;
    private List<TribulationDTO> listTribulation;
    private List<PropertiesDTO> listProperties;
    private String done;
    private String notdone;

    public SearchAction() {
    }

    public String execute() throws Exception {
        String url = ERROR;
        if (kindOfSearch != null) {
            if (kindOfSearch.equals("User")) {
                UserDAO dao = new UserDAO();
                dao.findByLikeName(searchValue);
                listAccount = dao.getList();
                url = SUCCESS;
            } else if (kindOfSearch.equals("Tribulation")) {
                TribulationDAO dao = new TribulationDAO();
                listTribulation = dao.findByLikeName(searchValue);
                for (TribulationDTO tribulationDTO : listTribulation) {
                    String directorID = tribulationDTO.getDirectorID();
                    UserDAO daoUser = new UserDAO();
                    UserDTO dto = daoUser.findByPrimaryKey(directorID);
                    if (dto != null) {
                        tribulationDTO.setDirectorName(dto.getUserFullname());
                    }
                }
                url = SUCCESS;
            } else {
                PropertiesDAO dao = new PropertiesDAO();
                listProperties = dao.findByLikeName(searchValue);
                url = SUCCESS;
            }
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("ERROR", "Invalid action");
        return url;
    }

    public List<TribulationDTO> getListTribulation() {
        return listTribulation;
    }

    public List<PropertiesDTO> getListProperties() {
        return listProperties;
    }

    public String getKindOfSearch() {
        return kindOfSearch;
    }

    public void setKindOfSearch(String kindOfSearch) {
        this.kindOfSearch = kindOfSearch;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<UserDTO> getListAccount() {
        return listAccount;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getNotdone() {
        return notdone;
    }

    public void setNotdone(String notdone) {
        this.notdone = notdone;
    }




}
