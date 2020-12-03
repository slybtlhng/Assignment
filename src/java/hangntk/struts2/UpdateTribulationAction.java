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
import hangntk.dtos.TribulationDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class UpdateTribulationAction extends ActionSupport {

    private String tribulationName;
    private String director;//directorID la value cua director
    private String description;
    private String address;
    private String startime;
    private String endtime;
    private int numberOfFilming;
    private String fileTribulation;
    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private String adminID;
    private String done;
    private String notdone;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public UpdateTribulationAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        TribulationDAO dao = new TribulationDAO();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date star = formater.parse(startime);
        Date end = formater.parse(endtime);
        TribulationDTO dto = new TribulationDTO(id, adminID, director, tribulationName, description, address, star, end, numberOfFilming, fileTribulation);
        if (dao.updateRecord(dto, id)) {
            done = "Update " + tribulationName + " success";
            url = SUCCESS;
            List<String> listCastID = new ArrayList<>();

            PartDAO daoPart = new PartDAO();
            List<String> listPartID = daoPart.findPartTribulationID(id);
            CastRegistrationDAO daoCR = new CastRegistrationDAO();

            for (String partID : listPartID) {
                String castID = daoCR.findCastByPart(partID);
                if (!castID.equals("failed")) {
                    if (!listCastID.contains(castID)) {
                        listCastID.add(castID);
                    }
                }
            }
            NotificationDAO daoNoti = new NotificationDAO();
            for (String castID : listCastID) {
                daoNoti.insertNotification("Admin", castID, "Update profile " + tribulationName);
            }
            daoNoti.insertNotification("Admin", director, "Update profile " + tribulationName);
        } else {
            notdone = "Update " + tribulationName + " failed";
        }
        return url;
    }

    public String getDone() {
        return done;
    }

    public String getNotdone() {
        return notdone;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getTribulationName() {
        return tribulationName;
    }

    public void setTribulationName(String tribulationName) {
        this.tribulationName = tribulationName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getNumberOfFilming() {
        return numberOfFilming;
    }

    public void setNumberOfFilming(int numberOfFilming) {
        this.numberOfFilming = numberOfFilming;
    }

    public String getFileTribulation() {
        return fileTribulation;
    }

    public void setFileTribulation(String fileTribulation) {
        this.fileTribulation = fileTribulation;
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

    @Override
    public void validate() {
        super.validate(); //To change body of generated methods, choose Tools | Templates.
        if (startime != null && endtime != null) {
            if (endtime.compareTo(startime) < 0) {
                addFieldError("startime", "Date End must after Date Start");
            }
        }
    }
}
