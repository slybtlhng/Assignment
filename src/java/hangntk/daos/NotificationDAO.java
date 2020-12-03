/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.daos;

import hangntk.db.MyConnection;
import hangntk.dtos.NotificationDTO;
import hangntk.dtos.PropertiesDTO;
import hangntk.dtos.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NotificationDAO implements Serializable {

    Connection cn;
    PreparedStatement preStm;
    ResultSet rs;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (cn != null) {
            cn.close();
        }
    }

    public void insertNotification(String impactUserID, String userID, String action) throws Exception {
        try {
            cn = MyConnection.getConnection();
            String sql = "INSERT INTO dbo.tblNotification\n"
                    + "(\n"
                    + "    notiDescription,\n"
                    + "    userID,\n"
                    + "    dateCreate\n"
                    + ")\n"
                    + "VALUES\n"
                    + "(  \n"
                    + "    ?,      -- notiDescription - nvarchar(100)\n"
                    + "    ?,       -- userID - varchar(10)\n"
                    + "    ? -- dateCreate - date\n"
                    + "    )";
            preStm = cn.prepareStatement(sql);
            System.out.println(userID);
            String noti = impactUserID + " " + action;
            preStm.setString(1, noti);
            preStm.setString(2, userID);
            Date currentDate = new Date();
            preStm.setDate(3, new java.sql.Date(currentDate.getTime()));
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public List<NotificationDTO> findByUserID(String id) throws Exception {
        List<NotificationDTO> list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "SELECT notiDescription , dateCreate FROM tblNotification WHERE userID LIKE ? ";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String notiDescription = rs.getString("notiDescription");
                Date date = rs.getDate("dateCreate");
                NotificationDTO dto = new NotificationDTO(id, notiDescription, date);
                list.add(dto);
            }
            Collections.sort(list);
        } finally {
            closeConnection();
        }
        return list;
    }
}
