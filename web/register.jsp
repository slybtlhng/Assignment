<%-- 
    Document   : register
    Created on : Jul 9, 2020, 9:55:41 AM
    Author     : ASUS
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePage.css">
        <title>Register Page</title>
        <s:head/>
        <style type="text/css">
            #register label.error{
                color:red;
                width:250px;
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
                <h1>CREATE NEW ACCOUNT</h1>
                <s:form action="register" method="POST" id="register" enctype="multipart/form-data" >
                    <s:textfield name="username" label="Username*"/>
                    <s:password name="password" label="Password*"/>
                    <s:password name="confirm" label="Confirm Password*" id="pass"/>
                    <s:textfield name="fullname" label="Fullname*"/>
                    <s:select name="role" list="{'Cast','Director'}" label="Role"/>
                    <s:textfield name="email" label="Email*"/>
                    <s:textfield name="phone" label="Phone* (Ex:0919799125)"/>
                    <s:file name="fileImage" label="Image (*.jpg||*.png)" accept="image/*"/>
                    <s:textarea name="description" label="Description"/>
                    <s:submit value="Register" theme="xhtml"/>
                </s:form>
                <s:if test="%{exception.message.indexOf('duplicate') > -1}">
                    <s:if test="%{exception.message.indexOf(username) > -1}">
                        <font color="red" >
                        <s:property value="%{username}"/> is existed!!!
                        </font>
                    </s:if>
                    <s:if test="%{exception.message.indexOf(email) > -1}">
                        <font color="red" >
                        <s:property value="%{email}"/> is existed!!!
                        </font>
                    </s:if>
                    <s:if test="%{exception.message.indexOf(phone) > -1}">
                        <font color="red" >
                        <s:property value="%{phone}"/> is existed!!!
                        </font>
                    </s:if>
                </s:if>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#register").validate({
                rules: {
                    username: {
                        required: true,
                        rangelength: [4, 30]
                    },
                    password: {
                        required: true,
                        rangelength: [4, 30]
                    },
                    confirm: {
                        required: true,
                        equalTo: "#pass"
                    },
                    fullname: {
                        required: true,
                        minlength: 2
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    phone: {
                        required: true,
                        maxlength: 10,
                        phone: true
                    }
                },
                messages: {
                    confirm: {
                        equalTo: "Not match with above password!"
                    },
                    phone: {
                        maxlength: "Your number must contains 10 digit"
                    },
                    fileImage: {
                        accept: "Just accept file .png or .jpg"
                    }
                }
            });
        });
    </script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>
