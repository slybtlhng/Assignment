/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.daos;

import hangntk.db.MyConnection;
import hangntk.dtos.PropertiesDTO;
import hangntk.dtos.TribulationPropertiesDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class TribulationPropertiesDAO implements Serializable {

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

    public void saveTribuProp(Map<PropertiesDTO, Integer> list, String directorID, String tribulationID, Date start, Date end) throws Exception {
        try {
            cn = MyConnection.getConnection();
            cn.setAutoCommit(false);
            String sql = "INSERT INTO dbo.tblTribulation_Properties\n"
                    + " (\n"
                    + "     tribulationID,\n"
                    + "     propID,\n"
                    + "     propQuantity,\n"
                    + "     directorID,\n"
                    + "     timeStart,\n"
                    + "     timeEnd )\n"
                    + " VALUES\n"
                    + " (   ?, -- tribulationID - varchar(10)\n"
                    + "     ?, -- propID - varchar(10)\n"
                    + "     ?,  -- propQuantity - int\n"
                    + "     ?, -- directorID - varchar(10)\n"
                    + "     ?,"
                    + "     ?)";
            preStm = cn.prepareStatement(sql);
            for (Map.Entry<PropertiesDTO, Integer> entry : list.entrySet()) {
                PropertiesDTO key = entry.getKey();
                PropertiesDAO dao = new PropertiesDAO();
                Integer value = entry.getValue();
                key.setPropQuanity(key.getPropQuanity() - value);
                dao.updateRecord(key, key.getPropID());
                preStm.setString(1, tribulationID);
                preStm.setString(2, key.getPropID());
                preStm.setInt(3, value);
                preStm.setString(4, directorID);
                preStm.setDate(5, new java.sql.Date(start.getTime()));
                preStm.setDate(6, new java.sql.Date(end.getTime()));
                preStm.executeUpdate();
            }
            cn.commit();
        } finally {
            cn.setAutoCommit(true);
            closeConnection();
        }
    }

    public List<TribulationPropertiesDTO> loadListTribuPropdependDate(String directorID, Date start, Date end) throws Exception {
        List<TribulationPropertiesDTO> list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select tribulationID,\n"
                    + "     propID,\n"
                    + "     propQuantity,\n"
                    + "     directorID,\n"
                    + "     timeStart,\n"
                    + "     timeEnd from tblTribulation_Properties where directorID like ? and timeStart >= ? and timeEnd <= ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, directorID);
            preStm.setDate(2, new java.sql.Date(start.getTime()));
            preStm.setDate(3, new java.sql.Date(end.getTime()));
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String tribulationID = rs.getString("tribulationID");
                String propID = rs.getString("propID");
                int propQuantity = rs.getInt("propQuantity");
                Date timeStart = rs.getDate("timeStart");
                Date timeEnd = rs.getDate("timeEnd");
                TribulationPropertiesDTO dto = new TribulationPropertiesDTO(tribulationID, propID, directorID, propQuantity, timeStart, timeEnd);
                TribulationDAO dao = new TribulationDAO();
                String tribulationName = dao.findByPrimaryKey(tribulationID).getTribulationName();
                dto.setTribulationName(tribulationName);
                PropertiesDAO daoPro = new PropertiesDAO();
                String propName = daoPro.findByPrimaryKey(propID).getPropName();
                dto.setPropName(propName);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean delete(String tribulationID) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Delete dbo.tblTribulation_Properties where tribulationID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, tribulationID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
