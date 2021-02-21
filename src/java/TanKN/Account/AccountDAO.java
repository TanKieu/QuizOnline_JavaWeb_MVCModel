/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Account;

import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class AccountDAO implements Serializable{
    public String username;
    public String role;

    public String getRole() {
        return role;
    }
    
    
    public String getUsername(){
        return username;
    }
    public boolean insertAccount(String email, byte[] password,String name ,String role, String status) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con= DBUtils.makeConnection();
            if(con!=null){
                String sql="INSERT into ACcount (email, password, name, role, status) "
                        + "Values(?, ?, ?, ?, ?)";
                stm=con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setBytes(2, password);
                stm.setString(3, name);
                stm.setString(4, role);
                stm.setString(5, status);
                int row= stm.executeUpdate();
                if(row>0)
                    return true;
            }
        }finally{
            if(stm!=null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    public boolean checkLogin(String email, byte[] password) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement stm= null;
        ResultSet rs =null;
        try{
            con= DBUtils.makeConnection();
           if(con!=null){
               String sql="Select email, password, name, role "
                       + "From Account "
                       + "Where email= ?";
           stm= con.prepareStatement(sql);
           stm.setString(1, email);
           rs= stm.executeQuery();
                if(rs.next()){
                    String name =rs.getString("name");
                    String role = rs. getString("role");
                    byte[] passwordEcrypt= rs.getBytes("password");
                    if(Arrays.equals(password, passwordEcrypt)){
                        username = name;
                        this.role = role;
                        return true;
                        
                    }
                }    
           }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
}
