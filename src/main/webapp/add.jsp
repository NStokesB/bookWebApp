<%-- 
    Document   : add
    Created on : Oct 5, 2016, 9:33:58 PM
    Author     : NStokesBeamon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Author</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <a href="index.html" class="navbar-brand">Author Inventory</a>
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
            <div class="container">
                <div class="row">
                    <div id="add" class="col-md-8">


                        <form method="POST" action="AuthorController?action=add">
                            <h2>Add a new Author</h2>
                            <label>Enter Name</label>
                            <input type="text" name="name" value="" id="name"/>
                            <input type="hidden" name="action" value="add" />
                            <input class="btn" color="black" type="submit" name="submit" value="add" id="addButton" />
                        </form>

                    </div>

                    <nav class="navbar	navbar-inverse	navbar-fixed-bottom">

                        <div class="container">
                            <p class="navbar-text"><i>&copy; 2016 Niesha Stokes Beamon</i></p>  
                        </div>

                    </nav>

                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
                    </body>
                    </html>
