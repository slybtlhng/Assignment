<%-- 
    Document   : viewCart1
    Created on : Jul 15, 2020, 4:31:23 PM
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
        <title>View Page</title>
    </head>
    <body>
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
                                <a class="nav-link" href="director.jsp">Home<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="loadNotiDirector">Notification<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Shopping
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <s:url var="loadListTribulaionToAddCast" value="loadListTribulationToAddCast">
                                        <s:param value="%{#session.INFO.userID}" name="id"/>
                                    </s:url>
                                    <a class="dropdown-item" href="${loadListTribulaionToAddCast}" >Shopping Cart 1(Cast-Part-Tribulation)</a>
                                    <s:url var="loadListTribulaionToAddProp" value="loadListTribulationToAddProp">
                                        <s:param value="%{#session.INFO.userID}" name="id"/>
                                    </s:url>
                                    <a class="dropdown-item" href="${loadListTribulaionToAddProp}">Shopping Cart 2(Properties-Tribulation)</a>
                                </div>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="viewCart1.jsp">Cart 1<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="viewCart2.jsp">Cart 2<span class="sr-only">(current)</span></a>
                            </li>
                        </ul>
                        <form action="logout" method="Post" class="form-inline my-2 my-lg-0">
                            <button type="submit" class="btn btn-outline-light text-dark">Log out</button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron text-center">
                <h1>Your Cart Cast-Part-Tribulation</h1>
            </div>
            <s:if test="%{#session.CART1!=null}">
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">ID Part</th>
                            <th scope="col">Name Part</th>
                            <th scope="col">Cast Name</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{#session.CART1.cart}" status="counter">
                            <tr>
                                <th scope="row"><s:property value="%{#counter.count}"/></th>
                                <td><s:property value="%{key.partID}"/></td>
                                <td><s:property value="%{key.partName}"/></td>
                                <td><s:property value="%{value.userFullname}"/></td>
                                <td><s:url var="deleteLink" value="deleteCastToPart">
                                        <s:param value="%{key.partID}" name="id"/>
                                    </s:url>
                                    <s:a href="%{deleteLink}">Delete</s:a>
                                    </td>
                                </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="4"><s:url var="addMoreLink" value="addMore">
                                    <s:param value="%{#session.INFO.userID}" name="id"/>
                                </s:url>
                                <s:a href="%{addMoreLink}">Add More Items to Your Cart</s:a></td>
                            <td><s:form action="saveCart1" method="POST" theme="simple">
                                    <s:submit value="Save"/>
                                </s:form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                Not yet Add Cast to Part!!!
            </s:else>
        </div>
    </body>
    <script src="js/jquery-3.5.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>
