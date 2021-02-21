/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Quiz_Record;

import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class Quiz_RecordDAO implements Serializable{
    public boolean insertNewRecord(Quiz_RecordDTO quizRecord) throws SQLException, NamingException{
        Connection con =  null;
        PreparedStatement stm = null;
        try{
            con=DBUtils.makeConnection();
            if(con != null){
                String sql="Insert into Quiz_Record(QuizID, email, SubjectID, result ) "
                        + "Values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, quizRecord.getQuizID());
                stm.setString(2, quizRecord.getEmail());
                stm.setString(3, quizRecord.getSubID());
                stm.setFloat(4, quizRecord.getScores());
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
    public List<Quiz_RecordDTO> getRecord(String email) throws SQLException, NamingException{
        List<Quiz_RecordDTO> recordList= new ArrayList<Quiz_RecordDTO>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs= null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql= "Select QuizID, SubjectID, result "
                        + "From Quiz_Record "
                        + "Where email=? ";
                stm= con.prepareStatement(sql);
                stm.setString(1, email);
                rs= stm.executeQuery();
                while(rs.next()){
                    String QuizID= rs.getString("QuizID");
                    String subject= rs.getString("SubjectID");
                    float scores= rs.getFloat("result");
                    Quiz_RecordDTO dto=new Quiz_RecordDTO(QuizID, email, subject, scores);
                    recordList.add(dto);
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
        return recordList;
    }
        public List<Quiz_RecordDTO> findRecord(String email, String subId) throws SQLException, NamingException{
        List<Quiz_RecordDTO> recordList= new ArrayList<Quiz_RecordDTO>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs= null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql= "Select QuizID, SubjectID, result "
                        + "From Quiz_Record "
                        + "Where email=? and SubjectID LIKE ? ";
                stm= con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, "%"+subId +"%");
                rs= stm.executeQuery();
                while(rs.next()){
                    String QuizID= rs.getString("QuizID");
                    String subject= rs.getString("SubjectID");
                    float scores= rs.getFloat("result");
                    Quiz_RecordDTO dto=new Quiz_RecordDTO(QuizID, email, subject, scores);
                    recordList.add(dto);
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
        return recordList;
    }
}
