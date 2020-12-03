/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import hangntk.daos.PropertiesDAO;
import hangntk.dtos.PropertiesDTO;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class UpdatePropAction {

    private String propName;
    private int propQuantity;
    private File propImage;
    private String propImageFileName;
    private String propImageContentType;
    private String propDescription;
    private String filename;
    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private String done;
    private String notdone;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public UpdatePropAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        PropertiesDAO dao = new PropertiesDAO();
        if (propImage != null) {
            String path = ServletActionContext.getServletContext().getRealPath("/");
            File desFile = new File(path.substring(0, path.lastIndexOf("build")) + "web//image//", propName + propImageFileName.substring(propImageFileName.lastIndexOf(".")));
            FileUtils.copyFile(propImage, desFile);
            filename = desFile.getName();
        }
        PropertiesDTO dto = new PropertiesDTO(propName, filename, propDescription, propQuantity);
        if (dao.updateRecord(dto, id)) {
            done = "Update " + propName + " success";
            url = SUCCESS;
        } else {
            notdone = "Update " + propName + " failed";
        }

        return url;
    }

    public String getDone() {
        return done;
    }

    public String getNotdone() {
        return notdone;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public int getPropQuantity() {
        return propQuantity;
    }

    public void setPropQuantity(int propQuantity) {
        this.propQuantity = propQuantity;
    }

    public File getPropImage() {
        return propImage;
    }

    public void setPropImage(File propImage) {
        this.propImage = propImage;
    }

    public String getPropImageFileName() {
        return propImageFileName;
    }

    public void setPropImageFileName(String propImageFileName) {
        this.propImageFileName = propImageFileName;
    }

    public String getPropImageContentType() {
        return propImageContentType;
    }

    public void setPropImageContentType(String propImageContentType) {
        this.propImageContentType = propImageContentType;
    }

    public String getPropDescription() {
        return propDescription;
    }

    public void setPropDescription(String propDescription) {
        this.propDescription = propDescription;
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
