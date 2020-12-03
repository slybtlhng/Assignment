/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import hangntk.daos.NotificationDAO;
import hangntk.daos.UserDAO;
import hangntk.dtos.UserDTO;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class UpdateAction extends ActionSupport {

    private String username, password, confirm, role, email, phone;
    private File fileImage;
    private String filename;
    private String fileImageContentType;
    private String fileImageFileName;
    private String description;
    private String fullname;
    private String id;
    private String lastSearchValue;
    private String kindOfSearch;
    private String done;
    private String notdone;
    private final String SUCCESS = "success";

    public UpdateAction() {

    }

    public String execute() throws Exception {
        String url = "fail";
        UserDAO dao = new UserDAO();
        if (fileImage != null) {
            String path = ServletActionContext.getServletContext().getRealPath("/");
            File desFile = new File(path.substring(0, path.indexOf("build")) + "web//image//", username);
            FileUtils.copyFile(fileImage, desFile);
            filename = desFile.getName();
        }
        UserDTO dto = new UserDTO(role, username, fullname, filename, description, email, phone);
        dto.setUserPass(password);

        if (dao.updateRecord(dto, id)) {
            NotificationDAO daoNoti = new NotificationDAO();
            daoNoti.insertNotification("Admin", id, "Update your profile");
            done = "Update " + username + " success";
            url = SUCCESS;
        } else {
            notdone = "Update " + username + " failed";
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    @FieldExpressionValidator(
            expression = "confirm==password",
            message = "Confirm must match Password")
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public File getFileImage() {
        return fileImage;
    }

    public void setFileImage(File fileImage) {
        this.fileImage = fileImage;
    }

    public String getFileImageContentType() {
        return fileImageContentType;
    }

    public void setFileImageContentType(String fileImageContentType) {
        this.fileImageContentType = fileImageContentType;
    }

    public String getFileImageFileName() {
        return fileImageFileName;
    }

    public void setFileImageFileName(String fileImageFileName) {
        this.fileImageFileName = fileImageFileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
