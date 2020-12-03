/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionSupport;
import hangntk.daos.PropertiesDAO;
import hangntk.dtos.PropertiesDTO;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class RegisterPropAction extends ActionSupport {

    private String propName;
    private int propQuantity;
    private File propImage;
    private String propImageFileName;
    private String propImageContentType;
    private String propDescription;
    private String done;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public RegisterPropAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        PropertiesDAO dao = new PropertiesDAO();
        String filename = "";
        if (propImage != null) {
            String path = ServletActionContext.getServletContext().getRealPath("/");
            File desFile = new File(path.substring(0, path.lastIndexOf("build")) + "web//image//", propName + propImageFileName.substring(propImageFileName.lastIndexOf(".")));
            FileUtils.copyFile(propImage, desFile);
            filename = desFile.getName();
        } else {
            filename = "nullImage.jpg";
        }
        PropertiesDTO dto = new PropertiesDTO(propName, filename, propDescription, propQuantity);
        if (dao.registProperties(dto)) {
            done="Register Properties success";
            url = SUCCESS;
        }

        return url;
    }

    public String getDone() {
        return done;
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

}
