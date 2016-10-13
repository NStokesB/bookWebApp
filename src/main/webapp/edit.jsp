<%-- 
    Document   : edit
    Created on : Oct 5, 2016, 9:33:15 PM
    Author     : NStokesBeamon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Author</title>
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
                    <div id="editDelete" class="col-md-8">

                        <form method="POST" action="AuthorController?action=edit" >
                            <h2>Edit</h2>



                            <input id="request" color="black" type="hidden" name="request" value=""/>
                            <label>Author Id:</label>
                            <input id="id" color="black" type="text" name="authorId" value="${author.authorId}" readonly/>
                            <br>
                            <label>Name:</label>

                            <input id="name" color="black" type="text" name="authorName" value="${author.authorName}"/>
                            <br>
                            <label>Date Added:</label>
                            <input id="date" color="black" type="text" name="date" value="${author.dateAdded}" readonly/>
                            <br>
                            
                            <input type="submit" class="btn" color="black" value="edit" name="submit" />
                            <input class="btn" color="black" type="submit" id="cancel" value="cancel" name="submit" />
                        </form>
                    </div> 
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
