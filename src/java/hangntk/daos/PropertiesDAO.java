/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.daos;

import hangntk.db.MyConnection;
import hangntk.dtos.PropertiesDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class PropertiesDAO implements Serializable {

    Connection cn;
    PreparedStatement preStm;
    ResultSet rs;
    List<PropertiesDTO> list;

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

    public PropertiesDAO() {

    }

    public List<PropertiesDTO> findByLikeName(String id) throws Exception {
        list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select propID,\n"
                    + "    propName,\n"
                    + "    propImage,\n"
                    + "    propDescription,\n"
                    + "    propQuantity \n"
                    + "    From dbo.tblProperties Where propName like ? and propStatus=1";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, "%" + id + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String propID = rs.getString("propID");
                String propName = rs.getString("propName");
                String image = rs.getString("propImage");
                String Description = rs.getString("propDescription");
                int quantity = rs.getInt("propQuantity");
                PropertiesDTO dto = new PropertiesDTO(propID, propName, image, Description, quantity);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public PropertiesDTO findByPrimaryKey(String id) throws Exception {
        PropertiesDTO dto = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select propID,\n"
                    + "    propName,\n"
                    + "    propImage,\n"
                    + "    propDescription,\n"
                    + "    propQuantity \n"
                    + "    From dbo.tblProperties Where propID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String propID = rs.getString("propID");
                String propName = rs.getString("propName");
                String image = rs.getString("propImage");
                String Description = rs.getString("propDescription");
                int quantity = rs.getInt("propQuantity");
                dto = new PropertiesDTO(propID, propName, image, Description, quantity);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<PropertiesDTO> findByStatus(int status) throws Exception {
        List<PropertiesDTO> list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select propID,\n"
                    + "    propName,\n"
                    + "    propImage,\n"
                    + "    propDescription,\n"
                    + "    propQuantity \n"
                    + "    From dbo.tblProperties Where propStatus = ?";
            preStm = cn.prepareStatement(sql);
            preStm.setInt(1, status);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String propID = rs.getString("propID");
                String propName = rs.getString("propName");
                String image = rs.getString("propImage");
                String Description = rs.getString("propDescription");
                int quantity = rs.getInt("propQuantity");
                PropertiesDTO dto = new PropertiesDTO(propID, propName, image, Description, quantity);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean registProperties(PropertiesDTO dto) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "INSERT INTO dbo.tblProperties\n"
                    + "		(\n"
                    + "		    propName,\n"
                    + "		    propImage,\n"
                    + "		    propDescription,\n"
                    + "		    propQuantity,\n"
                    + "		    propStatus\n"
                    + "		)\n"
                    + "		VALUES\n"
                    + "		  (  ?,  -- propName - nvarchar(100)\n"
                    + "		    ?, -- propImage - varbinary(max)\n"
                    + "		    ?,  -- propDescription - nvarchar(100)\n"
                    + "		    ?,    -- propQuantity - int\n"
                    + "		    1  -- propStatus - bit\n"
                    + "		    )";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, dto.getPropName());
            preStm.setString(2, dto.getPropImage());
            preStm.setString(3, dto.getPropDescription());
            preStm.setInt(4, dto.getPropQuanity());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteRecord(String id) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Delete dbo.tblProperties where propID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateRecord(PropertiesDTO dto, String id) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Update tblProperties set propName =? ,\n"
                    + "		    propImage=? ,\n"
                    + "		    propDescription= ?,\n"
                    + "		    propQuantity=? where propID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, dto.getPropName());
            preStm.setString(2, dto.getPropImage());
            preStm.setString(3, dto.getPropDescription());
            preStm.setInt(4, dto.getPropQuanity());
            preStm.setString(5, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public Map<String, String> loadListProp() throws Exception {
        Map<String, String> list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select propID,\n"
                    + "    propName \n"
                    + "    From dbo.tblProperties where propQuantity > 0 AND propStatus=1 ";
            preStm = cn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new HashMap<String, String>();
            while (rs.next()) {
                String propID = rs.getString("propID");
                String propName = rs.getString("propName");
                list.put(propID, propName);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
