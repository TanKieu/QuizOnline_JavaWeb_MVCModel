/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Servlet;

import TanKN.Account.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_FAIL = "loginFailPage";
    private final String ADMIN_LOGIN = "GetQuestion";
    private final String STUDENT_LOGIN="getSubject";

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
        HttpSession session = request.getSession();
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = LOGIN_FAIL;
        try {
            if (request.getParameter("txtEmail") != null || request.getParameter("txtPassword") != null) {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] passwordEncrypt = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                AccountDAO dao = new AccountDAO();
                boolean check = dao.checkLogin(email, passwordEncrypt);
                if (check) {
                    String name = dao.getUsername();
                    String role =dao.getRole();
                    session.setAttribute("NAME", name);
                    session.setAttribute("EMAIL", email);
                    if(role.trim().equals("Student")){
                        url= STUDENT_LOGIN;
                    }else{
                        url = ADMIN_LOGIN;
                    }
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            log("LoginServlet_NoSuchAlgorithmExcept " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet_SQLException " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet_NamingException " + ex.getMessage());
        } finally {
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
