<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sub</title>

        <link href="css/editUser.css" rel="stylesheet" />

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">

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
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" src="image/${requestScope.user.imageURL}" width="200" height="200"><span class="font-weight-bold">${requestScope.user.userID}</span><span class="text-black-50">${requestScope.user.email}</span><span> </span></div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Sub's Profile</h4>
                        </div>

                        <form action="DisplayInformationController" method="post" enctype="multipart/form-data">

                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Name</label><p style="font-family:'Times New Roman'">${requestScope.user.userName}</p></div>
                                <div class="col-md-12"><label class="labels">Email</label><p style="font-family:'Times New Roman'">${requestScope.user.email}</p></div>                            
                                <div class="col-md-12"><label class="labels">Phone Number</label><p style="font-family:'Times New Roman'">${requestScope.user.phoneNumber}</p></div>
                                <div class="col-md-12"><label class="labels">Create Date</label><p style="font-family:'Times New Roman'">${requestScope.user.createDate}</p></div>
                                <div class="col-md-12"><label class="labels">Rank</label><p style="font-family:'Times New Roman'">${requestScope.user.rank}</p></div>

                                <c:if test="${requestScope.user.status eq 'true'}">
                                    <div class="col-md-12"><label class="labels">Status</label><p style="font-family:'Times New Roman'">Active</p></div>
                                </c:if>
                                <c:if test="${requestScope.user.status eq 'false'}">
                                    <div class="col-md-12"><label class="labels">Status</label><p style="font-family:'Times New Roman'">Inactive</p></div>
                                </c:if>

                                <div class="col-md-12"><label class="labels">Status Promotion</label><p style="font-family:'Times New Roman'">${requestScope.user.statusPromotion}</p></div>
                                <div class="col-md-12"><label class="labels">Promotion Date</label><p style="font-family:'Times New Roman'">${requestScope.user.promotionDate}</p></div>
                            </div>

                            <div class="mt-5 text-center"><button type="button" onclick="location.href = 'LogoutController'" class="btn">Logout</i></button>
                            </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
