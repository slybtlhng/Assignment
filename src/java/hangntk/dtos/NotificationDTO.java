/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class NotificationDTO implements Serializable, Comparable<NotificationDTO> {

    private String userID;
    private String notiDescription;
    private Date dateCreate;

    public NotificationDTO(String userID, String notiDescription, Date dateCreate) {
        this.userID = userID;
        this.notiDescription = notiDescription;
        this.dateCreate = dateCreate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNotiDescription() {
        return notiDescription;
    }

    public void setNotiDescription(String notiDescription) {
        this.notiDescription = notiDescription;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public int compareTo(NotificationDTO t) {
        if (this.dateCreate.compareTo(t.getDateCreate()) < 0) {
            return 1;
        } else if (this.dateCreate.compareTo(t.getDateCreate()) > 0) {
            return -1;
        }
        return 0;
    }

}
