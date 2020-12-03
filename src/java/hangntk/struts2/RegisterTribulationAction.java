/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import hangntk.daos.NotificationDAO;
import hangntk.daos.PartDAO;
import hangntk.daos.TribulationDAO;
import hangntk.dtos.TribulationDTO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class RegisterTribulationAction extends ActionSupport {

    private String adminID;
    private String tribulationName;
    private String director;//directorID la value cua director
    private String description;
    private String address;
    private String startime;
    private String endtime;
    private int numberOfFilming;
    private File fileTribulation;
    private String fileTribulationFileName;
    private String fileTribulationContentType;
    private String done;
    private String notdone;

    public RegisterTribulationAction() {
    }

    public String execute() throws Exception {
        TribulationDAO dao = new TribulationDAO();

        String path = ServletActionContext.getServletContext().getRealPath("/");
        File desFile = new File(path.substring(0, path.lastIndexOf("build")) + "web//fileTribulation//", fileTribulationFileName);
        FileUtils.copyFile(fileTribulation, desFile);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date star = formater.parse(startime);
        Date end = formater.parse(endtime);
        TribulationDTO dto = new TribulationDTO(adminID, director, tribulationName, description, address, star, end, numberOfFilming, desFile.getName());
        if (dao.registTribulation(dto)) {
            String tribulationID = dao.getID(dto.getTribulationName());
            PartDAO daoPart = new PartDAO();
            if (daoPart.loadFileToDB(desFile, tribulationID)) {
                NotificationDAO daoNoti = new NotificationDAO();
                daoNoti.insertNotification("Admin", director, "Choose you for Tribulation " + tribulationName);
                done = "Register Tribulation success";
            } else {
                notdone = "Can't load file Part to database";
                dao.deleteRecord(tribulationID);
            }
        }
        return "success";
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

    public File getFileTribulation() {
        return fileTribulation;
    }

    public void setFileTribulation(File fileTribulation) {
        this.fileTribulation = fileTribulation;
    }

    public String getFileTribulationFileName() {
        return fileTribulationFileName;
    }

    public void setFileTribulationFileName(String fileTribulationFileName) {
        this.fileTribulationFileName = fileTribulationFileName;
    }

    public String getFileTribulationContentType() {
        return fileTribulationContentType;
    }

    public void setFileTribulationContentType(String fileTribulationContentType) {
        this.fileTribulationContentType = fileTribulationContentType;
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
