/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.Servlet;

import TanKN.Account.AccountDAO;
import TanKN.Account.RegisterError;
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

/**
 *
 * @author winnh
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    private final String ERROR_PAGE="registerPage";
    private final String REGISTER_SUCCESS="registerSuccess";

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
        String email = request.getParameter("txtEmail");
        String password= request.getParameter("txtPassword");
        String confirm=request.getParameter("txtConfirm");
        String name = request.getParameter("txtName");
        String role= request.getParameter("dlRole");
        RegisterError errors = new RegisterError();
        boolean foundError= false;
        String url = ERROR_PAGE;
        try {
            /* TODO output your page here. You may use following sample code. */
            if(email.trim().isEmpty()){
                foundError= true;
                errors.setEmailError("Email is required");
            }else if(!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?")){
                foundError= true;
                errors.setEmailError("Email is wrong format!!!");
            }
            if(password.trim().length()<6){
                foundError = true;
                errors.setPasswordError("Password is requird 6 characters");
            }else if(!confirm.trim().equals(password.trim())){
                foundError= true;
                errors.setConfirmNotMatch("Confirm must be matched password!!!");
            }
            if(foundError){
                request.setAttribute("REGISTER_ERRORS", errors);
            }else{
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] passwordEncrypt= digest.digest(password.getBytes(StandardCharsets.UTF_8));
                AccountDAO dao= new AccountDAO();
                boolean result= dao.insertAccount(email, passwordEncrypt, name, role, "New");
                if(result){
                    url=REGISTER_SUCCESS;
                }
                
            }
            
        
        } catch (SQLException ex) {
            log("Register_SQLException "+ex.getMessage());
            if((ex.getMessage()).contains("duplivate")){
                errors.setEmailExistError(email+" is already existed");
                request.setAttribute("REGISTER_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("Register_NamingException "+ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("Register_NoSuchAlgorithmException "+ex.getMessage());
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
