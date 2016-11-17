<%-- 
    Document   : bookResponse
    Created on : Nov 2, 2016, 9:09:29 PM
    Author     : NStokesBeamon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp" />
    </head>
    <body>
    
        <div class="container">
        <div class="row">
        <div class="col-md-8">
        <h1>Book Inventory</h1>
        
        <form id="bookListForm" name="bookListForm" method="POST" action="BookController?action=update">
        <input type="submit" name="Delete" value="Delete"/>
        <input type="submit" name="Update" value="Update"/>
        <table class="table table-hover active">
            <thead>
                <tr>
                    
                    <th>ID</th>
                    <th>Title</th>
                    <th>ISBN</th>
                    <th>Author</th>
                </tr>
            </thead>
            
           
            <tbody>
            
                    <c:forEach var="book" items="${bookList}">
                                 <tr>
                                     <td>
                                        <input type="checkbox" name="bookId" value="${book.bookId}">
                                    </td>
                                    <td>
                                        <c:out value="${book.title}"/>
                                    </td>
                                    <td>
                                        <c:out value="${book.isbn}"/>
                                    </td>
                                     <td>
                                        <c:out value="${book.authorId.authorName}"/>
                                    </td>
                                </tr>
                    </c:forEach>  
           </tbody>
            
        </table>
        </form>
        
        <a href="bookAdd.jsp"><img src="images/addper.jpg" title="add" width="50" height="50"/></a>
        
        
        <h1>${errorMsg}</h1>
        </div>
        </div>
        </div>
       
       
    
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        
    </body>
</html>
