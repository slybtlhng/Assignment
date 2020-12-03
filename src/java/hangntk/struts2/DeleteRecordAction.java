/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.CastRegistrationDAO;
import hangntk.daos.TribulationDAO;
import hangntk.daos.UserDAO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class DeleteRecordAction {

    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private String role;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String done;
    private String notdone;
    
    public DeleteRecordAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        UserDAO dao = new UserDAO();
        System.out.println(role);
        if (role.equals("Cast")) {
            CastRegistrationDAO daoCR = new CastRegistrationDAO();
            String partID = daoCR.findByFK(id);
            if (partID.equals("failed")) {
                dao.deleteRecord(id);
                url = SUCCESS;
            } else {
                daoCR.delete(id);
                dao.deleteRecord(id);
                url = SUCCESS;
            }
        } else {
            TribulationDAO daoTri = new TribulationDAO();
            Map<String,String> list=daoTri.loadListTribulation(id);
            if (list.size() >0) {
                notdone="Can't delete because this director belong to Tribulation";
                url = FAIL;
            } else {
                dao.deleteRecord(id);
                url = SUCCESS;
            }
        }
        if (url.equals(SUCCESS)) {
            done="Delete success!!!";
        }
        return url;
    }

    public String getDone() {
        return done;
    }

    public String getNotdone() {
        return notdone;
    }

    public String getKindOfSearch() {
        return kindOfSearch;
    }

    public void setKindOfSearch(String kindOfSearch) {
        this.kindOfSearch = kindOfSearch;
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

    public void setRole(String role) {
        this.role = role;
    }

}
