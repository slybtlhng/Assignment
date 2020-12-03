/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import hangntk.daos.UserDAO;
import hangntk.dtos.UserDTO;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class RegisterAction extends ActionSupport {

    private String username, password, confirm, role, email, phone;
    private File fileImage;
    private String fileImageContentType;
    private String fileImageFileName;
    private String description;
    private String fullname;
    private String done;

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public RegisterAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        String filename = "";
        UserDAO dao = new UserDAO();
        if (fileImage != null) {
            String path = ServletActionContext.getServletContext().getRealPath("/");
            System.out.println(path);
            File desFile = new File(path.substring(0, path.lastIndexOf("build")) + "web//image//", username + fileImageFileName.substring(fileImageFileName.lastIndexOf(".")));
            FileUtils.copyFile(fileImage, desFile);
            filename = desFile.getName();
        } else {
            filename = "userAva.jpg";
        }
        UserDTO dto = new UserDTO(role, username, fullname, filename, description, email, phone);
        dto.setUserPass(password);

        if (dao.registUser(dto)) {
            done="Register success!!";
            url = SUCCESS;
        }
        return url;
    }

    public String getDone() {
        return done;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
