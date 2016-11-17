<%-- 
    Document   : bookAdd
    Created on : Nov 7, 2016, 7:49:45 PM
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
                    <div id="add" class="col-md-8">


                        <form method="POST" action="BookController?action=add">
                            <h2>Add a new Book</h2>
                            <label>Enter Title:</label>
                            <input type="text" name="title" value="" id="title"/><br>
                            <label>Enter ISBN:</label>
                            <input type="text" name="isbn" value="" id="isbn"/><br>
                            <label>Enter Author ID:</label>
                            <input type="text" name="authorId" value="" id="authorId"/><br>
                            <input type="hidden" name="action" value="add" />
                            <input class="btn" color="black" type="submit" name="submit" value="add" id="addButton" />
                        </form>

                    </div>
    </body>
</html>
