<%-- 
    Document   : search
    Created on : Jul 2, 2020, 11:28:26 PM
    Author     : ASUS
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePage.css">
        <title>Admin</title>
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
                <h1>Search Page</h1>
                <s:form action="search" method="POST" theme="simple">
                    <s:select name="kindOfSearch" label="What do you want to search" list="{'User','Propertie','Tribulation'}"/>
                    <s:textfield name="searchValue" label="Fullname"/>
                    <s:submit value="Search"/>
                </s:form>
            </div>
            <s:if test="%{searchValue !=null and searchValue!=''}">
                <s:if test="%{listAccount !=null and listAccount.size>0 }">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Image</th>
                                <th scope="col">FullName</th>
                                <th scope="col">Role</th>
                                <th scope="col">UserName</th>
                                <th scope="col">Email</th>
                                <th scope="col">Phone</th>
                                <th scope="col">Delete</th>
                                <th scope="col">Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator var="dto" value="listAccount" status="counter">
                                <tr>
                                    <th scope="row"><s:property value="%{#counter.count}"/></th>
                                    <td>
                                        <img width="200" height="200" src="image/${dto.userImage}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.userFullname}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.roleName}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.userName}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.userEmail}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#dto.userPhone}"/>
                                    </td>
                                    <s:if test="%{!#dto.roleName.equalsIgnoreCase('Admin') }">
                                        <td>
                                            <s:url var="deleteLink" value="deleteRecord">
                                                <s:param name="id" value="%{#dto.userID}"/>
                                                <s:param name="lastSearchValue" value="searchValue"/>
                                                <s:param name="kindOfSearch" value="kindOfSearch"/>
                                                <s:param name="role" value="%{#dto.roleName}"/>
                                            </s:url>
                                            <s:a href="%{deleteLink}">Delete</s:a>
                                            </td>
                                            <td>
                                            <s:form action="editRecord" method="POST" theme="simple">
                                                <s:hidden name="id" value="%{#dto.userID}"/>
                                                <s:hidden name="lastSearchValue" value="%{searchValue}"/>
                                                <s:hidden name="kindOfSearch" value="%{kindOfSearch}"/>
                                                <s:submit value="Edit" />
                                            </s:form>
                                        </td>
                                    </s:if>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:if>
                <s:elseif test="%{listTribulation!=null and listTribulation.size>0}">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Tribulation Name</th>
                                <th scope="col">Director Name</th>
                                <th scope="col">Address</th>
                                <th scope="col">Start time</th>
                                <th scope="col">End time</th>
                                <th scope="col">Number of filming</th>
                                <th scope="col">File Tribulation</th>
                                <th scope="col">Delete</th>
                                <th scope="col">Edit</th>
                            </tr>
                        </thead>
                        <s:iterator value="listTribulation" var="dto" status="counter">
                            <tbody>
                                <tr>
                                    <th scope="row"><s:property value="%{#counter.count}"/></th>
                                    <td><s:property value="%{#dto.tribulationName}"/></td>
                                    <td><s:property value="%{#dto.directorName}"/></td>
                                    <td><s:property value="%{#dto.tribulationAddress}"/></td>
                                    <td><s:property value="%{#dto.tribulationStartTime}"/></td>
                                    <td><s:property value="%{#dto.tribulationEndTime}"/></td>
                                    <td><s:property value="%{#dto.tribulationCount}"/></td>
                                    <td>
                                        <s:url var="downFile" value="downFile">
                                            <s:param name="fileName" value="%{#dto.tribulationFile}"/>
                                        </s:url>
                                        <s:a href="%{downFile}">Download</s:a>
                                        </td>
                                        <td><s:url var="deleteLinkTribulation" value="deleteRecordTribulation">
                                            <s:param name="id" value="%{#dto.tribulationID}"/>
                                            <s:param name="lastSearchValue" value="searchValue"/>
                                            <s:param name="kindOfSearch" value="kindOfSearch"/>
                                        </s:url>
                                        <s:a href="%{deleteLinkTribulation}">Delete</s:a></td>
                                    <td><s:form action="editTribulation" method="POST" theme="simple">
                                            <s:hidden name="id" value="%{#dto.tribulationID}"/>
                                            <s:hidden name="lastSearchValue" value="%{searchValue}"/>
                                            <s:hidden name="kindOfSearch" value="%{kindOfSearch}"/>
                                            <s:submit value="Edit" />
                                        </s:form>
                                    </td>
                                </tr>
                            </tbody>
                        </s:iterator>
                    </table>
                </s:elseif>
                <s:elseif test="%{listProperties!=null and listProperties.size>0}">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">No.</th>
                                <th scope="col">Image</th>
                                <th scope="col">Name</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Delete</th>
                                <th scope="col">Edit</th>
                            </tr>
                        </thead>
                        <s:iterator value="listProperties" var="dto" status="counter">
                            <tbody>
                                <tr>
                                    <th scope="row"><s:property value="%{#counter.count}"/></th>
                                    <td><img width="200" height="200" src="image/${dto.propImage}"/></td>
                                    <td><s:property value="%{#dto.propName}"/></td>
                                    <td><s:property value="%{#dto.propQuanity}"/></td>
                                    <td><s:url var="deleteLinkProp" value="deleteRecordProp">
                                            <s:param name="id" value="%{#dto.propID}"/>
                                            <s:param name="lastSearchValue" value="searchValue"/>
                                            <s:param name="kindOfSearch" value="kindOfSearch"/>
                                        </s:url>
                                        <s:a href="%{deleteLinkProp}">Delete</s:a></td>
                                    <td><s:form action="editProp" method="POST" theme="simple">
                                            <s:hidden name="id" value="%{#dto.propID}"/>
                                            <s:hidden name="lastSearchValue" value="%{searchValue}"/>
                                            <s:hidden name="kindOfSearch" value="%{kindOfSearch}"/>
                                            <s:submit value="Edit" />
                                        </s:form></td>
                                </tr>
                            </tbody>
                        </s:iterator>
                    </table>
                </s:elseif>
                <s:else >
                    <font color="red">    
                    No record is matched!!!
                    </font>
                </s:else>
            </s:if>
        </div>
        <script src="js/jquery-3.5.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
