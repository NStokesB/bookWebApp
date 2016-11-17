<%-- 
    Document   : bookEdit
    Created on : Nov 7, 2016, 8:03:02 PM
    Author     : NStokesBeamon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="container">
                <div class="row">
                    <div id="editDelete" class="col-md-8">

                        <form method="POST" action="BookController?action=edit" >
                            <h2>Edit a Book</h2>



                            <input id="request" color="black" type="hidden" name="request" value=""/>
                            <label>Book Id:</label>
                            <c:choose>
                                <c:when test="${not empty book}">
                                    <tr>
                                        <td style="background-color: black;color:white;" align="left"></td>
                                        <td align="left"><input type="text" value="${book.bookId}" name="bookId" readonly/></td>
                                    </tr>         
                                </c:when>
                            </c:choose>
                            <br>
                            <label>Title:</label>
                            <input id="title" color="black" type="text" name="title" value="${book.title}"/>
                            <br>
                            <label>ISBN:</label>
                            <input id="isbn" color="black" type="text" name="isbn" value="${book.isbn}" />
                            <br>
                            <label>Author:</label>
                            <select>
                                <c:forEach var="author" items="${authors}">
                                    <option value="${author.authorId}" name="bookAuthor" id="author">${author.authorName} </option>
                                </c:forEach>
                            </select>
                            <input id="authorId" color="black" type="text" name="authorId" value="${book.authorId.authorName}" readonly/>
                            <br>
                            <input type="submit" class="btn" color="black" value="edit" name="submit" />
                            <input class="btn" color="black" type="submit" id="cancel" value="cancel" name="submit" />
                        </form>
                    </div> 
                </div>
            </div>
        </div>
        
        
    </body>
</html>
