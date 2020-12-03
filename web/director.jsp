<%-- 
    Document   : director
    Created on : Jul 15, 2020, 12:14:47 AM
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
        <title>Director Page</title>
        <style type="text/css">
            #day label.error{
                color:red;
                width: 250px;
                font-style: italic;
            }
        </style>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/additional-methods.js" type="text/javascript"></script>
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
                <h1>Properties</h1>
                <s:form action="statisticalProp" method="POST" theme="simple" id="day">
                    From: <s:textfield type="date" name="startime" label="Star Time" />
                    To: <s:textfield type="date" name="endtime" label="End Time" />
                    <s:submit value="Properties depend on Date to Date used "/>
                </s:form>
                <br/>
                <s:form action="statisticalPropStatus" method="POST" theme="simple">
                    <s:select name="searchValue" label="Status" list="{'Out of stock','In stock'}"/>
                    <s:submit value="Properties depend on status "/>
                </s:form>
            </div>
            <s:if test="%{list!=null or listProperties!=null}">
                <s:if test="%{list.size > 0}">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Tribulation Name</th>
                                <th scope="col">Properties Name</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Date Start</th>
                                <th scope="col">Date End</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator var="dto" value="list" status="counter">
                                <tr>
                                    <th scope="row"><s:property value="%{#counter.count}"/></th>
                                    <td><s:property value="%{#dto.tribulationName}"/></td>
                                    <td><s:property value="%{#dto.propName}"/></td>
                                    <td><s:property value="%{#dto.propQuantity}"/></td>
                                    <td><s:property value="%{#dto.timeStart}"/></td>
                                    <td><s:property value="%{#dto.timeEnd}"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
                <s:elseif test="%{listProperties.size>0}">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Image</th>
                                <th scope="col">Name</th>
                                <th scope="col">Quantity</th>
                            </tr>
                        </thead>
                        <s:iterator value="listProperties" var="dto" status="counter">
                            <tbody>
                                <tr>
                                    <th scope="row"><s:property value="%{#counter.count}"/></th>
                                    <td><img width="200" height="200" src="image/${dto.propImage}"/></td>
                                    <td><s:property value="%{#dto.propName}"/></td>
                                    <td><s:property value="%{#dto.propQuanity}"/></td>
                                </tr>
                            </tbody>
                        </s:iterator>
                    </table>
                </s:elseif>
                <s:else>
                    <font color="red">
                    No record found!
                    </font>
                </s:else>
            </s:if>
        </div>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#day").validate({
                rules: {
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