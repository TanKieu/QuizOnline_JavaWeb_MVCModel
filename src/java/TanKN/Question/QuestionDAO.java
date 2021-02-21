/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Question;

import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class QuestionDAO implements Serializable{
    List<QuestionDTO> questionList ;

    public List<QuestionDTO> getQuestionList() {
        return questionList;
    }
    
    public void getAllQuestion() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Select questionID, question, A, B, C, D, answer, createDate, SubjectID, status "
                        + "From Question ";
                stm= con.prepareStatement(sql);
                rs= stm.executeQuery();
                questionList = new ArrayList<>();
                while(rs.next()){
                    String qID= rs.getString("questionID");
                    String question= rs.getString("question");
                    String A=rs.getString("A");
                    String B=rs.getString("B");
                    String C= rs.getString("C");
                    String D= rs.getString("D");
                    String correctAnswer= rs.getString("answer");
                    Date createDate = rs.getDate("createDate");
                    String subID= rs.getString("SubjectID");
                    boolean status= rs.getBoolean("status");
                    QuestionDTO dto = new QuestionDTO(qID, subID, question, correctAnswer, A, B, C, D, createDate, status);
                    questionList.add(dto);
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
    }
    public QuestionDTO getQuestionByID(String Id) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Select  question, A, B, C, D, answer, createDate, SubjectID, status "
                        + "From Question "
                        + "Where questionID=? ";
                stm= con.prepareStatement(sql);
                stm.setString(1, Id);
                rs= stm.executeQuery();
                if(rs.next()){
                    String qID= Id;
                    String question= rs.getString("question");
                    String A=rs.getString("A");
                    String B=rs.getString("B");
                    String C= rs.getString("C");
                    String D= rs.getString("D");
                    String correctAnswer= rs.getString("answer");
                    Date createDate = rs.getDate("createDate");
                    String subID= rs.getString("SubjectID");
                    boolean status= rs.getBoolean("status");
                    QuestionDTO dto = new QuestionDTO(qID, subID, question, correctAnswer, A, B, C, D, createDate, status);
                    return dto;
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
        return null;
    }
    
    public List<QuestionDTO> searchQuestion(String name, boolean status, String Subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs= null;
        List<QuestionDTO> searchResult= new ArrayList<>();
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql ="Select questionID, question, A, B, C, D, answer, createDate, SubjectID, status "
                        + "From Question "
                        + "Where question LIKE ? and status=? and SubjectID LIKE ? "
                        + "Order By question, SubjectID";
                stm= con .prepareStatement(sql);
                stm.setString(1, "%"+name+"%");
                stm.setBoolean(2, status);
                stm.setString(3, "%"+Subject+"%");
                rs= stm.executeQuery();
                while(rs.next()){
                    String qID= rs.getString("questionID");
                    String question= rs.getString("question");
                    String A=rs.getString("A");
                    String B=rs.getString("B");
                    String C= rs.getString("C");
                    String D= rs.getString("D");
                    String correctAnswer= rs.getString("answer");
                    Date createDate = rs.getDate("createDate");
                    String subID= rs.getString("SubjectID");
                    QuestionDTO dto = new QuestionDTO(qID, subID, question, correctAnswer, A, B, C, D, createDate, status);
                    searchResult.add(dto);
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
        return searchResult;
    }
    
    public boolean addQuestion(QuestionDTO question) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Insert into Question (questionID, question, A, B, C, D, answer, createDate, SubjectID, status) "
                        + "Values(?,?,?,?,?,?,?,?,?,?) ";
                stm=con.prepareStatement(sql);
                stm.setString(1, question.getqID());
                stm.setString(2, question.getQuestion());
                stm.setString(3, question.getA());
                stm.setString(4, question.getB());
                stm.setString(5, question.getC());
                stm.setString(6, question.getD());
                stm.setString(7, question.getConrrectAnswer());
                stm.setDate(8, question.getCreateDate());
                stm.setString(9, question.getSubId());
                stm.setBoolean(10, question.isStatus());
                int row = stm.executeUpdate();
                if(row > 0)
                    return true;
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
    public void updateQuestion(QuestionDTO question) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Update  Question "
                        + " Set question=? , A=?,  B=?, C=?, D=?, answer=?, SubjectID=?, status=? "
                        + "Where questionID=?  ";
                stm=con.prepareStatement(sql);
                stm.setString(9, question.getqID());
                stm.setString(1, question.getQuestion());
                stm.setString(2, question.getA());
                stm.setString(3, question.getB());
                stm.setString(4, question.getC());
                stm.setString(5, question.getD());
                stm.setString(6, question.getConrrectAnswer());
                stm.setString(7, question.getSubId());
                stm.setBoolean(8, question.isStatus());
                int row=stm.executeUpdate();     
                if(row>0){
                    System.out.println("finish");
                }else{
                    System.out.println("fail");
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
    }    
    
    public void deleteQuestion(String quesID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con= DBUtils.makeConnection();
            if(con != null){
                String sql="Update Question "
                        + "Set status= ? "
                        + "Where questionID= ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setString(2,quesID);
                int row =stm.executeUpdate();
                if(row>0){
                    System.out.println("true");
                }else{
                    System.out.println("false");
                }
                
            }
        }finally{
            if(stm != null ){
                stm.close();
            }
            if(con != null ){
                con.close();
            }
        }
    }
    public ArrayList<QuestionDTO> getQuestionForQuiz(String subjectID) throws SQLException, NamingException{
        ArrayList<QuestionDTO> quizList= new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql="Select TOP 20 questionID, question, A, B, C, D, answer, SubjectID "
                        + "From Question "
                        + "Where SubjectID= ? and status = 1 "
                        + "ORDER BY NEWID() ";
                stm= con.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs= stm.executeQuery();
                questionList = new ArrayList<>();
                while(rs.next()){
                    String qID= rs.getString("questionID");
                    String question= rs.getString("question");
                    String A=rs.getString("A");
                    String B=rs.getString("B");
                    String C= rs.getString("C");
                    String D= rs.getString("D");
                    String correctAnswer= rs.getString("answer");
                    String subID= rs.getString("SubjectID");
                    QuestionDTO dto = new QuestionDTO(qID, subID, question, correctAnswer, A, B, C, D, null, true);
                    quizList.add(dto);
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
        return quizList;
    }    
}
