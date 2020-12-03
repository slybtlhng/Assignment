/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.dtos;

//import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class TribulationDTO implements Serializable {

    private String tribulationID;
    private String adminID, directorID, tribulationName;
    private String tribulationDescription;
    private String tribulationAddress;
    private Date tribulationStartTime;
    private Date tribulationEndTime;
    private int tribulationCount;
    private int toolCount;
    private String tribulationFile;
    private String directorName;

    public TribulationDTO(String tribulationID, String adminID, String directorID, String tribulationName, String tribulationDescription, String tribulationAddress, Date tribulationStartTime, Date tribulationEndTime, int tribulationCount, String tribulationFile) {
        this.tribulationID = tribulationID;
        this.adminID = adminID;
        this.directorID = directorID;
        this.tribulationName = tribulationName;
        this.tribulationDescription = tribulationDescription;
        this.tribulationAddress = tribulationAddress;
        this.tribulationStartTime = tribulationStartTime;
        this.tribulationEndTime = tribulationEndTime;
        this.tribulationCount = tribulationCount;
        this.tribulationFile = tribulationFile;
    }

    public TribulationDTO(String tribulationID, String tribulationName, String tribulationDescription, String tribulationAddress, Date tribulationStartTime, Date tribulationEndTime, int tribulationCount, int toolCount, String tribulationFile) {
        this.tribulationID = tribulationID;
        this.tribulationName = tribulationName;
        this.tribulationDescription = tribulationDescription;
        this.tribulationAddress = tribulationAddress;
        this.tribulationStartTime = tribulationStartTime;
        this.tribulationEndTime = tribulationEndTime;
        this.tribulationCount = tribulationCount;
        this.toolCount = toolCount;
        this.tribulationFile = tribulationFile;
    }

    public TribulationDTO(String adminID, String directorID, String tribulationName, String tribulationDescription, String tribulationAddress, Date tribulationStartTime, Date tribulationEndTime, int tribulationCount, String tribulationFile) {
        this.adminID = adminID;
        this.directorID = directorID;
        this.tribulationName = tribulationName;
        this.tribulationDescription = tribulationDescription;
        this.tribulationAddress = tribulationAddress;
        this.tribulationStartTime = tribulationStartTime;
        this.tribulationEndTime = tribulationEndTime;
        this.tribulationCount = tribulationCount;
        this.tribulationFile = tribulationFile;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }


    public String getTribulationID() {
        return tribulationID;
    }

    public void setTribulationID(String tribulationID) {
        this.tribulationID = tribulationID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public String getTribulationName() {
        return tribulationName;
    }

    public void setTribulationName(String tribulationName) {
        this.tribulationName = tribulationName;
    }

    public String getTribulationDescription() {
        return tribulationDescription;
    }

    public void setTribulationDescription(String tribulationDescription) {
        this.tribulationDescription = tribulationDescription;
    }

    public String getTribulationAddress() {
        return tribulationAddress;
    }

    public void setTribulationAddress(String tribulationAddress) {
        this.tribulationAddress = tribulationAddress;
    }

    public Date getTribulationStartTime() {
        return tribulationStartTime;
    }

    public void setTribulationStartTime(Date tribulationStartTime) {
        this.tribulationStartTime = tribulationStartTime;
    }

    public Date getTribulationEndTime() {
        return tribulationEndTime;
    }

    public void setTribulationEndTime(Date tribulationEndTime) {
        this.tribulationEndTime = tribulationEndTime;
    }

    public int getTribulationCount() {
        return tribulationCount;
    }

    public void setTribulationCount(int tribulationCount) {
        this.tribulationCount = tribulationCount;
    }

    public int getToolCount() {
        return toolCount;
    }

    public void setToolCount(int toolCount) {
        this.toolCount = toolCount;
    }

    public String getTribulationFile() {
        return tribulationFile;
    }

    public void setTribulationFile(String tribulationFile) {
        this.tribulationFile = tribulationFile;
    }

}
