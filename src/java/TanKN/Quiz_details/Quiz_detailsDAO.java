/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Quiz_details;

import TanKN.Question.QuestionDAO;
import TanKN.Question.QuestionDTO;
import TanKN.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author winnh
 */
public class Quiz_detailsDAO implements Serializable{
    public void insertQuizDetails(Quiz_detailsDTO dto) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con= DBUtils.makeConnection();
            if(con != null){
                String sql="Insert into Quiz_details(QuizID, questionID, answer, correctAnswer) "
                        + "Values(?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getQuizID());
                stm.setString(2, dto.getQuestionID());
                stm.setString(3, dto.getAnswer());
                stm.setString(4, dto.getCorrectAnswer());
                stm.executeUpdate();
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
    public Map<QuestionDTO, String> getDetail(String QuizID) throws SQLException, NamingException{
        Map<QuestionDTO, String> detailList= new HashMap<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con=DBUtils.makeConnection();
            if(con!=null){
                String sql="Select questionID, answer "
                        + "From Quiz_details "
                        + "Where QuizID= ? ";
                stm=con.prepareStatement(sql);
                stm.setString(1, QuizID);
                rs=stm.executeQuery();
                while(rs.next()){
                    String questionID= rs.getString("questionID");
                    QuestionDAO dao = new QuestionDAO();
                    QuestionDTO dto = dao.getQuestionByID(questionID);
                    String answer= rs.getString("answer");
                    detailList.put(dto, answer);
                    
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
        return detailList;
    }
}
