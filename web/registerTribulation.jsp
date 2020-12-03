<%-- 
    Document   : registerTribulation
    Created on : Jul 12, 2020, 10:44:05 AM
    Author     : ASUS
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePage.css">
        <title>Register Tribulation</title>
        <s:head/>
        <style type="text/css">
            #register label.error{
                width:250px;
                color: red;
                font-style: italic;
            }
        </style>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/additional-methods.js" type="text/javascript"></script>
    </head>
    <body>
        <!--nav bar start-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand">
                    ${sessionScope.INFO.userFullname}
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="col-auto">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="admin.jsp">Home<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Create
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="register.jsp">Create new User</a>
                                    <s:url action="loadListDirector" id="loadListDir"/>
                                    <a class="dropdown-item" href="${loadListDir}" >Create New Tribulation</a>
                                    <a class="dropdown-item" href="registerProp.jsp">Create New Properties</a>
                                </div>
                            </li>
                        </ul>
                        <form action="logout" method="Post" class="form-inline my-2 my-lg-0">
                            <button type="submit" class="btn btn-outline-light text-dark">Log out</button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
        <!--nav bar end-->
        <div class="container ">
            <div class="jumbotron text-center">
                <h1>Create new Tribulation</h1>
                <s:if test="%{listDirector!=null and listDirector.size>0}">
                    <s:form action="registerTribulation" method="POST" id="register" enctype="multipart/form-data">
                        <s:textfield name="tribulationName" label="Name*"/>
                        <s:select name="director" label="Choose Director*" list="%{listDirector}"/>
                        <s:hidden name="adminID" value="%{#session.INFO.userID}"/>
                        <s:textarea name="description" label="Tribulation Description"/>
                        <s:textfield name="address" label="Address*"/>
                        <s:textfield type="date" name="startime" label="Star Time*"/>
                        <s:textfield type="date" name="endtime" label="End Time"/>
                        <s:textfield name="numberOfFilming" label="Number Of Filming"/>
                        <s:file name="fileTribulation" label="File Description" accept="text/plain"/>
                        <s:submit value="Register"/>
                    </s:form>
                </s:if>
                <s:else>
                    <font color="red">
                    There is no Director available!!!
                    </font>
                    <s:a href="register.jsp">Do you want to create new Director?</s:a>
                    <s:a href="admin.jsp">Click to back homepage!</s:a>
                </s:else>
                <s:if test="%{exception.message.indexOf('duplicate')}">
                    <font color="red">
                    Tribulation Name is existed!!!
                    </font>
                </s:if>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#register").validate({
                rules: {
                    tribulationName: {
                        required: true,
                        rangelength: [2, 30]
                    },
                    address: {
                        required: true,
                        rangelength: [4, 30]
                    },
                    numberOfFilming: {
                        required: true,
                        number: true,
                        min: 0,
                        max: 100000000
                    },
                    fileTribulation: {
                        required: true
                    },
                    startime: {
                        required: true
                    },
                    endtime: {
                        required: true
                    }
                }
            });
        });
    </script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>
