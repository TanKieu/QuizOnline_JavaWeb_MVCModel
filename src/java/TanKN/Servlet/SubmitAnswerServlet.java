/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Servlet;

import TanKN.Question.QuestionDTO;
import TanKN.Quiz_Record.Quiz_RecordDAO;
import TanKN.Quiz_Record.Quiz_RecordDTO;
import TanKN.Quiz_details.Quiz_detailsDAO;
import TanKN.Quiz_details.Quiz_detailsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author winnh
 */
@WebServlet(name = "SubmitAnswerServlet", urlPatterns = {"/SubmitAnswerServlet"})
public class SubmitAnswerServlet extends HttpServlet {
    private final String RESULT="resultPage";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session= request.getSession();
        ArrayList<QuestionDTO> quizList= (ArrayList<QuestionDTO>) session.getAttribute("QUIZLIST");
        HashMap<QuestionDTO, String> answerList= new HashMap<>();
        float socoreEachAnswer= 10/quizList.size();
        float scores=0;
        String url= RESULT;
        try {
            for (QuestionDTO questionDTO : quizList) {
                String answer= request.getParameter(questionDTO.getqID().trim());
                answerList.put(questionDTO, answer);
            }
            for (QuestionDTO questionDTO : answerList.keySet()) {
                if((questionDTO.getConrrectAnswer().trim()).equalsIgnoreCase(answerList.get(questionDTO))){
                    scores+=socoreEachAnswer;
                }
            }
            Date quizDate= new Date(System.currentTimeMillis());
            String email= (String) session.getAttribute("EMAIL");
            String SubID= (String) session.getAttribute("SUBJECTID");
            String QuizID= SubID+quizDate;
            Quiz_RecordDTO quizRecordDTO = new Quiz_RecordDTO(QuizID, email, SubID, scores);
            Quiz_RecordDAO quizRecordDAO = new Quiz_RecordDAO();
            quizRecordDAO.insertNewRecord(quizRecordDTO);
            for (QuestionDTO questionDTO : answerList.keySet()) {
                Quiz_detailsDTO detailDTO= new Quiz_detailsDTO(QuizID, questionDTO.getqID(), answerList.get(questionDTO), questionDTO.getConrrectAnswer());
                Quiz_detailsDAO dao = new Quiz_detailsDAO();
                dao.insertQuizDetails(detailDTO);
            }            
            session.setAttribute("SCORES", scores);
            session.setAttribute("ANSWERLIST", answerList);
        } catch (SQLException ex) {
            log("SubmitAnswerServlet_SQLException "+ex.getMessage());
        } catch (NamingException ex) {
            log("SubmitAnswerServlet_NamingException "+ex.getMessage());
        }finally{
            RequestDispatcher rd= request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
