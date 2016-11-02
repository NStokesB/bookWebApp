/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.controller;

import edu.wctc.nsb.ejb.AuthorFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.wctc.nsb.model.Author;
import java.sql.SQLException;
import java.util.Date;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author NStokesBeamon
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String ACTION_PARAMETER = "action";
    private static final String GET_AUTHOR_LIST_ACTION = "getList";
    private static final String NO_PARAMETER_MSG = "No matching parameter found";
    private static final String EDIT = "edit";
    private static final String EDIT_SELECT = "update";
    private static final String ADD = "add";
    private final String RESPONSE_PAGE = "/Response.jsp";
    private final String EDIT_PAGE = "/edit.jsp";
   

    @Inject
    private AuthorFacade authService;

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

        String destination = RESPONSE_PAGE;
        String action = request.getParameter(ACTION_PARAMETER);
        Author author = null;

        List<Author> authors;
       
        try {

            
            authors = authService.findAll();
            request.setAttribute("authorList", authors);
            
            String delete = request.getParameter("Delete");
            if(delete != null && delete.equals("Delete")) {
                String[] itemsChecked = request.getParameterValues("authorId");
                if(itemsChecked != null && itemsChecked.length > 0) {
                    for(String id : itemsChecked) {
                        author = authService.find(new Integer(id));
                        authService.remove(author);
                    }
                }
                this.refreshList(request, authService);
            }
            
            

            switch (action) {
                case GET_AUTHOR_LIST_ACTION:

                    this.refreshList(request, authService);

                    destination = RESPONSE_PAGE;
                    //response.sendRedirect(RESPONSE_PAGE);
                    break;

                case ADD:
                    String authorName = request.getParameter("name");
                    author = new Author();
                    author.setAuthorName(authorName);
                    author.setDateAdded(new Date());

                    authService.create(author);
                    this.refreshList(request, authService);
                    destination = RESPONSE_PAGE;
                    //response.sendRedirect(RESPONSE_PAGE);
                    break;
                    
                case EDIT_SELECT:
                    String[] itemsChecked = request.getParameterValues("authorId");
                    Integer a = Integer.parseInt(itemsChecked[0]);
                    author = authService.find(new Integer(a));
                    request.setAttribute("author", author);
                    destination = EDIT_PAGE;
                    //response.sendRedirect(EDIT_PAGE);
                    break;
                    
                case EDIT:
                    String name = request.getParameter("authorName");
                    String authorId = request.getParameter("authorId");
                    author.setAuthorName(name);
                    author = authService.find(new Integer(authorId));
                    authService.edit(author);
                    this.refreshList(request, authService);
                    destination = RESPONSE_PAGE;
                    //response.sendRedirect(RESPONSE_PAGE);
                    
                    
                default:

                    request.setAttribute("errMsg", NO_PARAMETER_MSG);
                    destination = RESPONSE_PAGE;
                    //response.sendRedirect(RESPONSE_PAGE);
                    break;

            }

        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);
        //response.sendRedirect(RESPONSE_PAGE);

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

    @Override
    public void init() throws ServletException {

    }


    private void refreshList(HttpServletRequest request, AuthorFacade authorService) throws Exception {
        List<Author> authors = authorService.findAll();
        request.setAttribute("authorList", authors);
    }
}
