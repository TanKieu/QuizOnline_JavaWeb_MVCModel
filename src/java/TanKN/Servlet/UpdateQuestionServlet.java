/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Servlet;

import TanKN.Question.CreateNewQuestionErrors;
import TanKN.Question.QuestionDAO;
import TanKN.Question.QuestionDTO;
import TanKN.Subject.SubjectDAO;
import TanKN.Subject.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {
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
        String quesID=request.getParameter("txtQuestionID");
        String ques=request.getParameter("txtQuestion");
        String a=request.getParameter("txtA");
        String b=request.getParameter("txtB");
        String c=request.getParameter("txtC");
        String d=request.getParameter("txtD");
        String answer = request.getParameter("txtAnswer");
        String subID=request.getParameter("txtSubId");
        boolean status = true;
        boolean foundErrors= false;
        CreateNewQuestionErrors errors = new CreateNewQuestionErrors();
        HttpSession session = request.getSession();    
        String url="adminPage";
        try {
            if(quesID.equals("")){
                foundErrors = true;
                errors.setQuestionIDError("Question ID is not null");
            }
            if(ques.equals("")){
                foundErrors= true;
                errors.setQuestionError("Question is not null");
            }
            if(a.equals("")){
                foundErrors = true;
                errors.setaError("Answer A is not null");
            }
            if(b.equals("")){
                foundErrors = true;
                errors.setbError("Answer B is not null");
            }
            if(answer.equals("")){
                foundErrors = true;
                errors.setAnswerError("Have to fill correct answer");
            }else{
                if(!answer.trim().equalsIgnoreCase("a") && !answer.trim().equalsIgnoreCase("b") && !answer.trim().equalsIgnoreCase("c") && !answer.trim().equalsIgnoreCase("d")){
                    foundErrors = true;
                    errors.setAnswerError("Correct answer is not valid");
                }
            }
            if(subID.equals("")){
                foundErrors = true;
                errors.setSubIdError("Subject Id is not null");
            }
            if(foundErrors== false){
                subID = request.getParameter("txtSubId");
                if((request.getParameter("cbStatus"))==null){
                    status= false;
                }
                SubjectDAO subDAO= new SubjectDAO();
                if(subDAO.checkExistbyID(subID)==false){
                    SubjectDTO subDTO = new SubjectDTO();
                    subDTO.setSubjectID(subID);
                    subDTO.setSubjectName("");
                    subDAO.addNewSubject(subDTO);
                }
                Date createDate = new Date(System.currentTimeMillis());
                QuestionDTO dto = new QuestionDTO(quesID, subID, ques, answer, a, b, c, d, createDate, status);
                QuestionDAO dao = new QuestionDAO();
                dao.updateQuestion(dto);
                url="GetQuestion";
                
            }
            session.setAttribute("UPDATEERROR", errors);
        } catch (SQLException ex) {
            log("UpdateQuestionServlet_SQLException "+ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateQuestionServlet_NamingException "+ex.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
