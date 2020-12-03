/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.daos;

import hangntk.db.MyConnection;
import hangntk.dtos.TribulationDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
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
public class TribulationDAO implements Serializable {

    Connection cn;
    PreparedStatement preStm;
    ResultSet rs;
    List<TribulationDTO> list;

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

    public TribulationDAO() {

    }

    public List<TribulationDTO> findByLikeName(String id) throws Exception {
        list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select tribulationID, \n"
                    + " directorID,\n"
                    + "	    tribulationName,\n"
                    + "	    tribulationDescription,\n"
                    + "	    tribulationAddress,\n"
                    + "	    tribulationStartTime,\n"
                    + "	    tribulationEndTime,\n"
                    + "	    tribulationCount,\n"
                    + "	    toolCount,\n"
                    + "	    tribulationFile \n"
                    + "	    From dbo.tblTribulation Where tribulationName like ? and tribulationStatus=1";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, "%" + id + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String directorID = rs.getString("directorID");
                String tribulationID = rs.getString("tribulationID");
                String tribulationName = rs.getString("tribulationName");
                String tribulationDescription = rs.getString("tribulationDescription");
                String tribulationAddress = rs.getString("tribulationAddress");
                Date tribulationStartTime = rs.getDate("tribulationStartTime");
                Date tribulationEndTime = rs.getDate("tribulationEndTime");
                int tribulationCount = rs.getInt("tribulationCount");
                int toolCount = rs.getInt("toolCount");
                String tribulationFile = rs.getString("tribulationFile");
                TribulationDTO dto = new TribulationDTO(tribulationID, tribulationName, tribulationDescription, tribulationAddress, tribulationStartTime, tribulationEndTime, tribulationCount, toolCount, tribulationFile);
                dto.setDirectorID(directorID);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public TribulationDTO findByPrimaryKey(String id) throws Exception {
        TribulationDTO dto = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select tribulationID,\n"
                    + "	    tribulationName,\n"
                    + "	    tribulationDescription,\n"
                    + "	    tribulationAddress,\n"
                    + "	    tribulationStartTime,\n"
                    + "	    tribulationEndTime,\n"
                    + "	    tribulationCount,\n"
                    + "	    toolCount,\n"
                    + "	    tribulationFile, \n"
                    + "	    directorID From dbo.tblTribulation Where tribulationID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String tribulationID = rs.getString("tribulationID");
                String tribulationName = rs.getString("tribulationName");
                String tribulationDescription = rs.getString("tribulationDescription");
                String tribulationAddress = rs.getString("tribulationAddress");
                Date tribulationStartTime = rs.getDate("tribulationStartTime");
                Date tribulationEndTime = rs.getDate("tribulationEndTime");
                int tribulationCount = rs.getInt("tribulationCount");
                int toolCount = rs.getInt("toolCount");
                String tribulationFile = rs.getString("tribulationFile");
                dto = new TribulationDTO(tribulationID, tribulationName, tribulationDescription, tribulationAddress, tribulationStartTime, tribulationEndTime, tribulationCount, toolCount, tribulationFile);
                String directorID = rs.getString("directorID");
                dto.setDirectorID(directorID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public String getID(String name) throws Exception {
        String id = "failed";
        try {
            cn = MyConnection.getConnection();
            String sql = "Select tribulationID \n"
                    + "	    From dbo.tblTribulation Where tribulationName like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, name);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getString("tribulationID");
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public boolean registTribulation(TribulationDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO dbo.tblTribulation\n"
                    + "	(\n"
                    + "	    adminID,\n"
                    + "	    directorID,\n"
                    + "	    tribulationName,\n"
                    + "	    tribulationDescription,\n"
                    + "	    tribulationAddress,\n"
                    + "	    tribulationStartTime,\n"
                    + "	    tribulationEndTime,\n"
                    + "	    tribulationCount,\n"
                    + "	    toolCount,\n"
                    + "	    tribulationFile,\n"
                    + "	    tribulationStatus\n"
                    + "	)\n"
                    + "	VALUES\n"
                    + "	  ( ?,        -- adminID - varchar(10)\n"
                    + "	    ?,        -- directorID - varchar(10)\n"
                    + "	    ?,       -- tribulationName - nvarchar(100)\n"
                    + "	    ?,       -- tribulationDescription - nvarchar(100)\n"
                    + "	    ?,       -- tribulationAddress - nvarchar(100)\n"
                    + "	    ?, -- tribulationStartTime - date\n"
                    + "	    ?, -- tribulationEndTime - date\n"
                    + "	    ?,         -- tribulationCount - tinyint\n"
                    + "	    ?,         -- toolCount - int\n"
                    + "	    ?,       -- tribulationFile - nvarchar(max)\n"
                    + "	    1       -- tribulationStatus - bit\n"
                    + "	    )";
            cn = MyConnection.getConnection();
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, dto.getAdminID());
            preStm.setString(2, dto.getDirectorID());
            preStm.setString(3, dto.getTribulationName());
            preStm.setString(4, dto.getTribulationDescription());
            preStm.setString(5, dto.getTribulationAddress());
            preStm.setDate(6, new java.sql.Date(dto.getTribulationStartTime().getTime()));
            preStm.setDate(7, new java.sql.Date(dto.getTribulationEndTime().getTime()));
            preStm.setInt(8, dto.getTribulationCount());
            preStm.setInt(9, dto.getToolCount());
            preStm.setString(10, dto.getTribulationFile());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteRecord(String id) throws Exception {
        boolean result = false;
        try {
            PartDAO dao = new PartDAO();
            if (dao.deleteRecord(id)) {
                cn = MyConnection.getConnection();
                String sql = "Delete dbo.tblTribulation where tribulationID like ? ";
                preStm = cn.prepareStatement(sql);
                preStm.setString(1, id);
                result = preStm.executeUpdate() > 0;
            } else {
                return result;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateRecord(TribulationDTO dto, String id) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Update tblTribulation set"
                    + " directorID=?, \n"
                    + "	    tribulationDescription=?,\n"
                    + "	    tribulationAddress=?,\n"
                    + "	    tribulationStartTime=?,\n"
                    + "	    tribulationEndTime=?,\n"
                    + "	    tribulationCount=?,\n"
                    + "	    tribulationFile=? \n"
                    + "	    where tribulationID like ?";
            cn = MyConnection.getConnection();
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, dto.getDirectorID());
            preStm.setString(2, dto.getTribulationDescription());
            preStm.setString(3, dto.getTribulationAddress());
            preStm.setDate(4, new java.sql.Date(dto.getTribulationStartTime().getTime()));
            preStm.setDate(5, new java.sql.Date(dto.getTribulationEndTime().getTime()));
            preStm.setInt(6, dto.getTribulationCount());
            preStm.setString(7, dto.getTribulationFile());
            preStm.setString(8, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public Map<String, String> loadListTribulation(String directorID) throws Exception {
        Map<String, String> list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select tribulationID,\n"
                    + "	    tribulationName \n"
                    + "	     From dbo.tblTribulation Where directorID like ? ";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, directorID);
            rs = preStm.executeQuery();
            list = new HashMap<String, String>();
            while (rs.next()) {
                String id = rs.getString("tribulationID");
                String name = rs.getString("tribulationName");
                list.put(id, name);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
