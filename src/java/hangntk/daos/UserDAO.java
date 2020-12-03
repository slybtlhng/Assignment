/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.daos;

import hangntk.db.MyConnection;
import hangntk.dtos.UserDTO;
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
public class UserDAO implements Serializable {

    Connection cn;
    PreparedStatement preStm;
    ResultSet rs;
    List<UserDTO> list;

    public UserDAO() {

    }

    public List<UserDTO> getList() {
        return list;
    }

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

    public UserDTO checkLogin(String username, String password) throws Exception {
        UserDTO dto = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select userID,\n"
                    + "    roleName,\n"
                    + "    userFullName,\n"
                    + "    userImage,\n"
                    + "    userDescription,\n"
                    + "    userEmail,\n"
                    + "    userPhone From dbo.tblUser Where userName like ? and userPass like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String role = rs.getString("roleName");
                String userFullName = rs.getString("userFullName");
                String userImage = rs.getString("userImage");
                String userDescription = rs.getString("userDescription");
                String userEmail = rs.getString("userEmail");
                String userPhone = rs.getString("userPhone");
                dto = new UserDTO(userID, role, username, userFullName, userImage, userDescription, userEmail, userPhone);
                dto.setUserPass(password);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<UserDTO> findByLikeName(String id) throws Exception {
        list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select userID,\n"
                    + "    roleName,\n"
                    + "    userName,\n"
                    + "    userPass,\n"
                    + "    userFullName,\n"
                    + "    userImage,\n"
                    + "    userDescription,\n"
                    + "    userEmail,\n"
                    + "    userPhone From dbo.tblUser Where userFullName like ? and userStatus=1";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, "%" + id + "%");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String roleName = rs.getString("roleName");
                String userName = rs.getString("userName");
                String pass = rs.getString("userPass");
                String fullname = rs.getString("userFullname");
                String image = rs.getString("userImage");
                String Description = rs.getString("userDescription");
                String email = rs.getString("userEmail");
                String phone = rs.getString("userPhone");
                UserDTO dto = new UserDTO(userID, roleName, userName, fullname, image, Description, email, phone);
                dto.setUserPass(pass);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean deleteRecord(String id) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Delete dbo.tblUser where userID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateRecord(UserDTO dto, String id) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "Update tblUser set roleName=?,\n"
                    + "    userPass=?,\n"
                    + "    userFullName=?,\n"
                    + "    userImage=?,\n"
                    + "    userDescription=?,\n"
                    + "    userEmail=?,\n"
                    + "    userPhone =? where userID like ?";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, dto.getRoleName());
            preStm.setString(2, dto.getUserPass());
            preStm.setString(3, dto.getUserFullname());
            preStm.setString(4, dto.getUserImage());
            preStm.setString(5, dto.getUserDescription());
            preStm.setString(6, dto.getUserEmail());
            preStm.setString(7, dto.getUserPhone());
            preStm.setString(8, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public UserDTO findByPrimaryKey(String id) throws Exception {
        UserDTO dto = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select userID,\n"
                    + "    roleName,\n"
                    + "    userName,\n"
                    + "    userPass,\n"
                    + "    userFullName,\n"
                    + "    userImage,\n"
                    + "    userDescription,\n"
                    + "    userEmail,\n"
                    + "    userPhone From dbo.tblUser Where userID like ? and userStatus=1";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String userID = rs.getString("userID");
                String roleName = rs.getString("roleName");
                String userName = rs.getString("userName");
                String pass = rs.getString("userPass");
                String fullname = rs.getString("userFullname");
                String image = rs.getString("userImage");
                String Description = rs.getString("userDescription");
                String email = rs.getString("userEmail");
                String phone = rs.getString("userPhone");
                dto = new UserDTO(userID, roleName, userName, fullname, image, Description, email, phone);
                dto.setUserPass(pass);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean registUser(UserDTO dto) throws Exception {
        boolean result = false;
        try {
            cn = MyConnection.getConnection();
            String sql = "INSERT INTO dbo.tblUser\n"
                    + "(\n"
                    + "    roleName,\n"
                    + "    userName,\n"
                    + "    userPass,\n"
                    + "    userFullName,\n"
                    + "    userImage,\n"
                    + "    userDescription,\n"
                    + "    userEmail,\n"
                    + "    userPhone,\n"
                    + "    userStatus\n"
                    + ")\n"
                    + "VALUES(\n"
                    + "    ?, \n"
                    + "    ?,\n"
                    + "    ?, \n"
                    + "    ?, \n"
                    + "    ?, \n"
                    + "    ?, \n"
                    + "    ?, \n"
                    + "    ?,  \n"
                    + "    1\n"
                    + "    )";
            preStm = cn.prepareStatement(sql);
            preStm.setString(1, dto.getRoleName());
            preStm.setString(2, dto.getUserName());
            preStm.setString(3, dto.getUserPass());
            preStm.setString(4, dto.getUserFullname());
            preStm.setString(5, dto.getUserImage());
            preStm.setString(6, dto.getUserDescription());
            preStm.setString(7, dto.getUserEmail());
            preStm.setString(8, dto.getUserPhone());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public Map<String, String> loadListDirector() throws Exception {
        Map<String, String> listDirector = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select userID,\n"
                    + "    userFullName \n"
                    + "    From dbo.tblUser Where roleName like 'Director' and userStatus=1";
            preStm = cn.prepareStatement(sql);
            rs = preStm.executeQuery();
            listDirector = new HashMap<String, String>();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullname = rs.getString("userFullname");
                listDirector.put(userID, fullname);
            }
        } finally {
            closeConnection();
        }
        return listDirector;
    }

    public List<UserDTO> loadListCast() throws Exception {
        List<UserDTO> list = null;
        try {
            cn = MyConnection.getConnection();
            String sql = "Select userID,\n"
                    + "    userName,\n"
                    + "    roleName,\n"
                    + "    userFullName,\n"
                    + "    userImage,\n"
                    + "    userDescription,\n"
                    + "    userEmail,\n"
                    + "    userPhone From dbo.tblUser Where roleName like 'Cast' and userStatus=1";
            preStm = cn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String roleName = rs.getString("roleName");
                String userName = rs.getString("userName");
                String fullname = rs.getString("userFullname");
                String image = rs.getString("userImage");
                String Description = rs.getString("userDescription");
                String email = rs.getString("userEmail");
                String phone = rs.getString("userPhone");
                UserDTO dto = new UserDTO(userID, roleName, userName, fullname, image, Description, email, phone);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
