/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.dtos;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class UserDTO implements Serializable {

    private String userID, roleName;
    private String userName, userPass, userFullname, userImage, userDescription;
    private String userEmail, userPhone;

    public UserDTO(String userID, String roleName, String userName, String userFullname, String userImgae, String userDescription, String userEmail, String userPhone) {
        this.userID = userID;
        this.roleName = roleName;
        this.userName = userName;
        this.userFullname = userFullname;
        this.userImage = userImgae;
        this.userDescription = userDescription;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public UserDTO(String roleName, String userName, String userFullname, String userImage, String userDescription, String userEmail, String userPhone) {
        this.roleName = roleName;
        this.userName = userName;
        this.userFullname = userFullname;
        this.userImage = userImage;
        this.userDescription = userDescription;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleID(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO dto = (UserDTO) o;

        if ((this.userID == null && dto.userID != null) || (this.userID != null && !this.userID.equals(dto.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }
}
