/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ASUS
 */
public class MyConnection implements Serializable{
    public static Connection getConnection() throws Exception{
        Connection cn;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        cn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JOURNEYTOTHEWEST","sa","123456");
        return cn;
    }
}
