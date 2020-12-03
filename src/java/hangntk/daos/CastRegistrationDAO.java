/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.daos;

import hangntk.db.MyConnection;
import hangntk.dtos.PartDTO;
import hangntk.dtos.PropertiesDTO;
import hangntk.dtos.TribulationDTO;
import hangntk.dtos.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class CastRegistrationDAO implements Serializable {

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

    public void saveRegistration(Map<PartDTO, UserDTO> list, String directorID) throws Exception {
        try {
            cn = MyConnection.getConnection();
            cn.setAutoCommit(false);
            String sql = "INSERT INTO dbo.tblCastRegistration\n"
                    + "	(\n"
                    + "	    castID,\n"
                    + "	    partID,\n"
                    + "	    directorID\n"
                    + "	)\n"
                    + "	VALUES\n"
                    + "	(   ?, -- castID - varchar(10)\n"
                    + "	    ?, -- partID - varchar(10)\n"
                    + "	    ?  -- directorID - varchar(10)\n"
                    + "	    )";
            preStm = cn.prepareStatement(sql);
            for (Map.Entry<PartDTO, UserDTO> entry : list.entrySet()) {
                PartDTO key = entry.getKey();
                UserDTO value = entry.getValue();
                preStm.setString(1, value.getUserID());
                preStm.setString(2, key.getPartID());
                preStm.setString(3, directorID);
                preStm.executeUpdate();
                NotificationDAO daoNoti = new NotificationDAO();
                UserDAO dao = new UserDAO();
                UserDTO dto = dao.findByPrimaryKey(directorID);
                TribulationDAO daoTribu = new TribulationDAO();
                TribulationDTO dtoTribu = daoTribu.findByPrimaryKey(key.getTribulationID());
                daoNoti.insertNotification(dto.getUserFullname(), value.getUserID(), "Add you to Part " + key.getPartName() + " of " + dtoTribu.getTribulationName());
            }
            cn.commit();
        } finally {
            cn.setAutoCommit(true);
            closeConnection();
        }
    }

    public String findByFK(String userID) throws Exception {
        String partID = "failed";
        try {
            cn = MyConnection.getConnection();
            String sql = "SELECT partID FROM tblCastRegistration WHERE castID LIKE ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                partID = rs.getString("partID");
            }
        } finally {
            closeConnection();
        }
        return partID;
    }

    public String findCastByPart(String partID) throws Exception {
        String castID = "failed";
        try {
            cn = MyConnection.getConnection();
            String sql = "SELECT castID FROM tblCastRegistration WHERE partID LIKE ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, partID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                castID = rs.getString("castID");
            }
        } finally {
            closeConnection();
        }
        return castID;
    }

    public boolean delete(String userID) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Delete dbo.tblCastRegistration where castID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, userID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteByPartID(String partID) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Delete dbo.tblCastRegistration where partID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, partID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
