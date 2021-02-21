/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Subject;

import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class SubjectDAO implements Serializable{
    public boolean checkExistbyID(String subId) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql= " Select SubjectID "
                        + "From Subject "
                        + "Where SubjectID = ? ";
                stm = con.prepareStatement(sql);
                stm .setString(1, subId);
                rs= stm.executeQuery();
                if(rs.next()){
                    return true;
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
    public boolean addNewSubject(SubjectDTO subject) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "Insert into Subject(SubjectID, Subject_name) "
                        + "Values(?,?) ";
                stm = con .prepareStatement(sql);
                stm.setString(1, subject.getSubjectID());
                stm.setString(2, subject.getSubjectName());
                int row= stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return false;
    }
    public ArrayList<SubjectDTO> getAllSubject() throws SQLException, NamingException{
        ArrayList subjectList= new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs= null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Select SubjectID, Subject_name "
                        + "From Subject ";
                stm= con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String subID= rs.getString("SubjectID");
                    String subName= rs.getString("Subject_name");
                    SubjectDTO subject = new SubjectDTO(subID, subName);
                    subjectList.add(subject);
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
        return subjectList;
    }
}
