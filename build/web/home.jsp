<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Manage User</title>

        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

        <link href="css/styles.css" rel="stylesheet" />

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
            .btn {
                background-color: DodgerBlue;
                border: none;
                color: white;
                padding: 12px 16px;
                font-size: 16px;
                cursor: pointer;
            }

            /* Darker background on mouse-over */
            .btn:hover {
                background-color: RoyalBlue;
            }
        </style>
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">Manage User</div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="DisplayAllController">All</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="DisplayAdminController">Admin</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="DisplaySubController">Sub</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="ListPromotionController">List Promotion</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="insertUser.jsp">Insert User</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="HistoryController">History</a>
                </div>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">

                        <form action="SearchNameController" method="get">
                            <input type="hidden">
                            <input type="text" placeholder="Search.." name="word" value="${param.word}">
                            <button type="submit"><i class="fa fa-search"></i></button>
                        </form>

                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="DisplayAllController">Home</a></li>
                                <li class="nav-item"><a class="nav-link" href="LogoutController">Logout</a></li>
                                <li class="nav-item active"><a class="nav-link" href="EditProfileController?action=display">Edit</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- Page content-->
                <div class="container-fluid">
                    <c:if test="${not empty requestScope.listUser}">
                        <table class="table table-data2">
                            <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>User ID</th>
                                    <th>User Name</th>
                                    <th>Email</th>
                                    <th>Phone Number</th>
                                    <th>Create Date</th>
                                    <th>Rank</th>
                                    <th>Status</th>
                                    <th>Status Promotion</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                    <th>Add Promotion</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listUser}" var="user">
                                    <tr class="tr-shadow">
                                        <td>
                                            <img src="image/${user.imageURL}" width="200" height="200"/>
                                        </td>
                                        <td>
                                            ${user.userID}
                                        </td>
                                        <td>
                                            ${user.userName}
                                        </td>
                                        <td>
                                            ${user.email}
                                        </td>
                                        <td>
                                            ${user.phoneNumber}
                                        </td>
                                        <td>
                                            ${user.createDate}
                                        </td>
                                        <td>                                       
                                            ${user.rank}
                                        </td>
                                        <td>
                                            <c:if test="${user.status eq 'true'}">
                                                Active
                                            </c:if>
                                            <c:if test="${user.status eq 'false'}">
                                                Inactive
                                            </c:if>
                                        </td>
                                        <td>
                                            ${user.statusPromotion}
                                        </td>

                                        <td>
                                            <c:if test="${(user.status eq 'True') and (user.roleID eq '2')}">
                                                <button type="button" onclick="location.href = 'UpdateStatusDeleteController?userID=${user.userID}'" class="btn"><i class="fa fa-trash"></i></button>
                                                </c:if>
                                        </td>

                                        <td>
                                            <c:if test="${user.roleID eq '2'}">
                                                <button type="button" onclick="location.href = 'UpdateUserController?userID=${user.userID}'" class="btn">Update</i></button>
                                            </c:if>
                                        </td>

                                        <td>
                                            <c:if test="${(user.roleID eq '2') and (user.statusPromotion eq 'False')}">
                                                <button type="button" onclick="location.href = 'AddPromotionController?userID=${user.userID}'" class="btn">Add Promotion</i></button>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr class="spacer"></tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.listUser}">
                        <h1>Empty</h1>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
