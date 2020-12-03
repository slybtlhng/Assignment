<%-- 
    Document   : shoppingCart2
    Created on : Jul 15, 2020, 12:21:40 AM
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
        <title>Shopping Cart 2</title>
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
        <s:if test="%{done!=null}">
            <div class="alert alert-success">
                <strong>Success!</strong> ${done}
            </div>
        </s:if>
        <s:if test="%{notdone!=null}">
            <div class="alert alert-danger">
                <strong>Error!</strong> ${notdone}
            </div>
        </s:if>
        <div class="container">
            <div class="jumbotron text-center">
                <h1>Add Properties to Tribulation</h1>
            </div>
            <s:if test="%{listTribulation.size>0 and listTribulation!=null}">
                <s:if test="%{listProp.size>0 and listProp!=null}">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Image</th>
                                <th scope="col">Name</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Tribulation Name</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Add</th>
                            </tr>
                        </thead>
                        <s:iterator value="listProp" var="dto" status="counter">
                            <tbody>
                                <tr>
                                    <th scope="row"><s:property value="%{#counter.count}"/></th>
                                    <td><img width="200" height="200" src="image/${dto.propImage}"/></td>
                                    <td><s:property value="%{#dto.propName}"/></td>
                                    <td><s:property value="%{#dto.propQuanity}"/></td>
                                    <s:form action="addPropToTribu" method="POST" theme="simple" id="add">
                                        <td><s:if test="%{#session.CART2==null}">
                                                <s:select name="tribulation" list="%{listTribulation}"/>
                                            </s:if>
                                            <s:else>
                                                <s:hidden name="tribulation" value="%{#session.TRIBULATION}"/>
                                                <s:textfield readonly="true" name="tribulationName" value="%{tribulationName}" label="Tribulation"/>
                                            </s:else></td>
                                        <td><s:select name="quantity" list="{'1','5','10','15','20','25','50','100'}"/></td>
                                        <td>
                                            <s:hidden name="prop" value="%{#dto.propID}"/>
                                            <s:submit value="Add Prop"/>
                                        </td></s:form>
                                    </tr>
                                </tbody>
                        </s:iterator>
                    </table>
                </s:if>
                <s:else>
                    You have no Prop to add!!
                </s:else>
            </s:if>
            <s:else>
                You have no Scene to add your Prop!!
            </s:else>
        </div>
    </body>
    <script src="js/jquery-3.5.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>
