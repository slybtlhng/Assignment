/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.daos;

import hangntk.db.MyConnection;
import hangntk.dtos.PartDTO;
import hangntk.dtos.PropertiesDTO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
public class PartDAO implements Serializable {

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

    public boolean loadFileToDB(File f, String tribulationID) throws Exception {
        boolean result = false;
        FileReader r = null;
        BufferedReader rr = null;
        try {
            r = new FileReader(f);
            rr = new BufferedReader(r);
            while (rr.ready()) {
                String s = rr.readLine();
                String[] arr = s.split(";");
                if (arr.length == 2) {
                    PartDTO dto = new PartDTO(arr[0], arr[1], tribulationID);
                    result = registerPart(dto);
                    if (result == false) {
                        return result;
                    }
                }
            }
        } finally {
            if (rr != null) {
                rr.close();
            }
            if (r != null) {
                r.close();
            }
        }
        return result;
    }

    public boolean registerPart(PartDTO dto) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "INSERT INTO dbo.tblPart\n"
                    + "(\n"
                    + "partName,\n"
                    + "partDescription,\n"
                    + " tribulationID\n"
                    + ")\n"
                    + "VALUES\n"
                    + "(   ?, -- partName - nvarchar(100)\n"
                    + "?, -- partDescription - nvarchar(100)\n"
                    + "?   -- tribulationID - varchar(10)\n"
                    + ")";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, dto.getPartName());
            preStm.setString(2, dto.getPartDescription());
            preStm.setString(3, dto.getTribulationID());
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
            String sql = "Delete dbo.tblPart where tribulationID like ? ";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public Map<String, String> loadListPart(String tribulationID) throws Exception {
        Map<String, String> list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "SELECT partID, partName\n"
                    + "		FROM dbo.tblPart p\n"
                    + "		WHERE tribulationID LIKE ? AND\n"
                    + "		p.partID not in (select partID from dbo.tblCastRegistration where p.partID = partID);";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, tribulationID);
            rs = preStm.executeQuery();
            list = new HashMap<String, String>();
            while (rs.next()) {
                String id = rs.getString("partID");
                String name = rs.getString("partName");
                list.put(id, name);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public PartDTO findByPrimaryKey(String id) throws Exception {
        PartDTO dto = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select partID,\n"
                    + "    partName,\n"
                    + "    partDescription,\n"
                    + "    tribulationID From dbo.tblPart Where partID like ? ";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String partID = rs.getString("partID");
                String partName = rs.getString("partName");
                String partDescription = rs.getString("partDescription");
                String tribulationID = rs.getString("tribulationID");
                dto = new PartDTO(partID, partName, partDescription, tribulationID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<String> findTribulationIDbyCastID(String castID) throws Exception {
        List<String> listID = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "SELECT tribulationID  FROM tblPart P,\n"
                    + "				tblCastRegistration C \n"
                    + "		WHERE P.partID=C.partID AND c.castID LIKE ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, castID);
            rs = preStm.executeQuery();
            listID = new ArrayList<>();
            while (rs.next()) {
                String tribulationID = rs.getString("tribulationID");
                if (!listID.contains(tribulationID)) {
                    listID.add(tribulationID);
                }
            }
        } finally {
            closeConnection();
        }
        return listID;
    }

    public List<String> findPartbyCastIDandTribulationID(String castID, String tribulationID) throws Exception {
        List<String> listID = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "SELECT P.partID FROM tblPart P,\n"
                    + "	tblCastRegistration C \n"
                    + "	WHERE P.partID=C.partID AND c.castID LIKE ? AND P.tribulationID LIKE ? ";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, castID);
            preStm.setString(2, tribulationID);
            rs = preStm.executeQuery();
            listID = new ArrayList<>();
            while (rs.next()) {
                String partID = rs.getString("partID");
                listID.add(partID);
            }
        } finally {
            closeConnection();
        }
        return listID;
    }

    public List<String> findPartTribulationID(String tribulationID) throws Exception {
        List<String> listID = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "SELECT partID FROM tblPart \n"
                    + "	WHERE tribulationID LIKE ? ";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, tribulationID);
            rs = preStm.executeQuery();
            listID = new ArrayList<>();
            while (rs.next()) {
                String partID = rs.getString("partID");
                listID.add(partID);
            }
        } finally {
            closeConnection();
        }
        return listID;
    }
}
