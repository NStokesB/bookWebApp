/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.nsb.controller;


import edu.wctc.nsb.ejb.AuthorFacade;
import edu.wctc.nsb.ejb.BookFacade;
import edu.wctc.nsb.model.Author;
import edu.wctc.nsb.model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NStokesBeamon
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {
     private static final String ACTION_PARAMETER = "action";
    private static final String GET_BOOK_LIST_ACTION = "getList";
    private static final String NO_PARAMETER_MSG = "No matching parameter found";
    private static final String EDIT = "edit";
    private static final String EDIT_SELECT = "update";
    private static final String ADD = "add";
    private final String RESPONSE_PAGE = "/bookResponse.jsp";
    private final String EDIT_PAGE = "/bookEdit.jsp";
   
    
    @Inject
    private BookFacade bookService;
    @Inject
    private AuthorFacade authorService;

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
        try (PrintWriter out = response.getWriter()) {
            
      
            String destination = RESPONSE_PAGE;
            String action = request.getParameter(ACTION_PARAMETER);
            Book book = null;

            List<Book> books;

            try {

                books = bookService.findAll();
                request.setAttribute("bookList", books);

                switch (action) {

                    case GET_BOOK_LIST_ACTION:

                        this.refreshList(request, bookService);

                        destination = RESPONSE_PAGE;
                        //response.sendRedirect(RESPONSE_PAGE);
                        break;

                    case ADD:
                        String title = request.getParameter("title");
                        String isbn = request.getParameter("isbn");
                        String authorId = request.getParameter("authorId");                             
                        book = new Book();
                        book.setTitle(title);
                        book.setIsbn(isbn);
                        Author author = null;
                        if(authorId != null){
                            author = authorService.find(new Integer(authorId));
                            book.setAuthorId(author);
                        }
                        bookService.create(book);
                        this.refreshList(request, bookService);
                        destination = RESPONSE_PAGE;
                        //response.sendRedirect(RESPONSE_PAGE);
                        break;

                    case EDIT_SELECT:
                        String delete = request.getParameter("Delete");
                        String update = request.getParameter("Update");
                        if (delete != null && delete.equals("Delete")) {
                            String[] itemsChecked = request.getParameterValues("bookId");
                            if (itemsChecked != null && itemsChecked.length > 0) {
                                for (String id : itemsChecked) {
                                    book = bookService.find(new Integer(id));
                                    bookService.remove(book);
                                }
                            }
                            this.refreshList(request, bookService);
                        }
                        if (update != null && update.equals("Update")) {

                            String[] itemsChecked = request.getParameterValues("bookId");
                            Integer a = Integer.parseInt(itemsChecked[0]);
                            book = bookService.find(new Integer(a));
                            List<Author> auth = authorService.findAll();
                            request.setAttribute("book", book);
                            request.setAttribute("authorList", auth);
                            destination = EDIT_PAGE;
                        }
                        //response.sendRedirect(EDIT_PAGE);
                        break;

                    case EDIT:
                        String title2 = request.getParameter("title");
                        String isbn2 = request.getParameter("isbn");
                        String bookId = request.getParameter("bookId");
                        String authorId2 = request.getParameter("authorId");
                        book = bookService.find(new Integer(bookId));
                        book.setTitle(title2);
                        book.setIsbn(isbn2);
                        Author author1 = null;
                          if(authorId2 != null) {
                            author = authorService.find(new Integer(authorId2));
                            book.setAuthorId(author1);
                          }
                        bookService.edit(book);
                        this.refreshList(request, bookService);
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
    
  private void refreshList(HttpServletRequest request, BookFacade bookServ) throws Exception {
        List<Book> books = bookServ.findAll();
        request.setAttribute("bookList", books);
    }
  
}
