/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ngocanh
 */
public class Connect {
    public Connection conn = null;
    //phuong thuc thuc hien knoi sql
    public Connection connectSQL() throws SQLException{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //b2.2 tỌ KẾT NỐI DTB
            String host = "localhost";
            String port="1433";
            String dbname="YODYJAVA";
            String username="sa";
            String password="123456";
            String dbUrl = "jdbc:sqlserver://"+host+":"+port+";databaseName="+dbname+";encrypt=false;";
            Connection conn = DriverManager.getConnection(dbUrl,username,password);
            System.out.println("ket noi thanh cong");
            return conn;
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Kết nối thất bại","Thông báo",1);
        }
        return null;
    }
}
