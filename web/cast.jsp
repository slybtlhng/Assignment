<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePage.css">
        <title>Cast Page</title>
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
                                <a class="nav-link" href="loadCurrentTribu">Home<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="loadNotiCast">Notification<span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="showOldTribu">Old shooting schedule<span class="sr-only">(current)</span></a>
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
                <h2>Upcoming shooting schedule</h2>
            </div>
            <s:if test="%{listTribu!=null and listTribu.size>0}">
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Tribulation Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Start Time</th>
                            <th scope="col">End Time</th>
                            <th scope="col">Script</th>
                            <th scope="col">Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator var="dto" value="%{listTribu}" status="counter">
                            <tr>
                                <th scope="row"><s:property value="%{#counter.count}"/></th>
                                <td><s:property value="%{#dto.tribulationName}"/></td>
                                <td><s:property value="%{#dto.tribulationDescription}"/></td>
                                <td><s:property value="%{#dto.tribulationStartTime}"/></td>
                                <td><s:property value="%{#dto.tribulationEndTime}"/></td>
                                <td>
                                    <s:url var="downFile" value="downFile">
                                        <s:param name="fileName" value="%{#dto.tribulationFile}"/>
                                    </s:url>
                                    <s:a href="%{downFile}">Download</s:a>
                                    </td>
                                    <td>
                                    <s:url var="viewPart" value="viewPart">
                                        <s:param name="idTribulation" value="%{#dto.tribulationID}"/>
                                        <s:param name="nameTribulation" value="%{#dto.tribulationName}"/>
                                    </s:url>
                                    <s:a href="%{viewPart}">View Role</s:a>
                                    </td>
                                </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                No schedule!!!
            </s:else>
        </div>
        <script src="js/jquery-3.5.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>