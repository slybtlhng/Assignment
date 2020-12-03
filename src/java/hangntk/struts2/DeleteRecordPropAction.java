/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.PropertiesDAO;
import hangntk.daos.TribulationPropertiesDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class DeleteRecordPropAction {

    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private String notdone;
    private String done;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public DeleteRecordPropAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        PropertiesDAO daoPro = new PropertiesDAO();
        TribulationPropertiesDAO daoTriProp = new TribulationPropertiesDAO();
        daoTriProp.delete(id);
        if (daoPro.deleteRecord(id)) {
            url = SUCCESS;
            done = "Delete success!!";
        } else {
            notdone = "Delete fail!!!";
        }
        return url;
    }

    public String getNotdone() {
        return notdone;
    }

    public String getDone() {
        return done;
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

}
