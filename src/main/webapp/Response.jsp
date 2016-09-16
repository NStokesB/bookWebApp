<%-- 
    Document   : Response
    Created on : Sep 15, 2016, 12:51:59 PM
    Author     : NStokesBeamon
--%>

<%@page import="java.util.List"%>
<%@page import="model.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Inventory</title>
        <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">Author Inventory</a>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#collapse-menu">
                    <span class="sr-only">Toggle Navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="collapse-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="index.html">Home</a></li>
                </ul>
           </div>
        </div>
    </nav>
        <div class="container">
        <div class="row">
        <div class="col-md-8">
        <h1>Author Inventory</h1>
        
        <table class="table table-hover active">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Author Name</th>
                    <th>Date Added</th>
                </tr>
            </thead>
            
           
            <tbody>
            
                    <c:forEach var="author" items="${authorList}">
                                 <tr>
                                    <td>
                                        <c:out value="${author.authorId}" />
                                    </td>
                                    <td>
                                        <c:out value="${author.authorName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${author.dateAdded}"/>
                                    </td>
                                </tr>
                    </c:forEach>  
           </tbody>
            
        </table>
        <h1>${errorMsg}</h1>
        </div>
        </div>
        </div>
        
        <nav class="navbar	navbar-inverse	navbar-fixed-bottom">
            
            <div class="container">
                <p class="navbar-text"><i>&copy; 2016 Niesha Stokes Beamon</i></p>  
            </div>
                 
        </nav>
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
