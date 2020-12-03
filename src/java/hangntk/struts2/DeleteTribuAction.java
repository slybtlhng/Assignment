/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionSupport;
import hangntk.daos.CastRegistrationDAO;
import hangntk.daos.NotificationDAO;
import hangntk.daos.PartDAO;
import hangntk.daos.TribulationDAO;
import hangntk.daos.TribulationPropertiesDAO;
import hangntk.dtos.TribulationDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DeleteTribuAction extends ActionSupport {

    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private String done;
    private String notdone;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public DeleteTribuAction() {

    }

    public String execute() throws Exception {
        String url = FAIL;
        TribulationDAO daoTribu = new TribulationDAO();
        //xoa gio hang
        TribulationPropertiesDAO daoTribuProp = new TribulationPropertiesDAO();

        daoTribuProp.delete(id);

        //lay ten dien vien de thong bao
        List<String> listCastID = new ArrayList<>();
        //lay ten dao dien phu trach
        TribulationDTO dto = daoTribu.findByPrimaryKey(id);
        String directorID = dto.getDirectorID();

        //xoa gio hang dien vien
        PartDAO daoPart = new PartDAO();
        List<String> listPartID = daoPart.findPartTribulationID(id);
        CastRegistrationDAO daoCR = new CastRegistrationDAO();

        for (String partID : listPartID) {
            String castID = daoCR.findCastByPart(partID);
            daoCR.deleteByPartID(partID);
            if (!castID.equals("failed")) {
                if (!listCastID.contains(castID)) {
                    listCastID.add(castID);
                }
            }
        }

        if (daoTribu.deleteRecord(id)) {
            url = SUCCESS;
            done = "Delete success";
            NotificationDAO daoNoti = new NotificationDAO();
            for (String castID : listCastID) {
                daoNoti.insertNotification("Admin", castID, "Delete your tribulation that you act for ");
            }
            daoNoti.insertNotification("Admin", directorID, "Delete your tribulation that you have reponsible");
        } else {
            notdone = "Delete fail";
        }
        return url;
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

}
