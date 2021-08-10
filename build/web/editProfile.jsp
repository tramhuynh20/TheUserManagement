<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>

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
            <button type="submit" onclick="location.href = 'DisplayAllController'" class="btn">Home</i></button>
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" src="image/${requestScope.user.imageURL}" width="200" height="200"><span class="font-weight-bold">${requestScope.user.userID}</span><span class="text-black-50">${requestScope.user.email}</span><span> </span></div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>

                        <form action="EditProfileController" method="post" enctype="multipart/form-data">

                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Name  <i><font color="red">${requestScope.error.userName}</font></i> </label><input type="text" class="form-control" placeholder="Enter name" name="userName" value="${requestScope.user.userName}" required></div>
                                <div class="col-md-12"><label class="labels">Email <i><font color="red">${requestScope.error.email}</font></i>  </label><input type="text" class="form-control" placeholder="Enter email" name="email" value="${requestScope.user.email}" required></div>
                                <div class="col-md-12"><label class="labels">Password <i><font color="red">${requestScope.error.password}</font></i>  </label><input type="password" class="form-control" placeholder="Enter password" name="password" value="${param.password}" required></div>
                                <div class="col-md-12"><label class="labels">Confirm Password <i><font color="red">${requestScope.error.passwordConfirm}</font></i>  </label><input type="password" class="form-control" placeholder="Enter password again" name="passwordConfirm" value="${param.passwordConfirm}" required></div>
                                <div class="col-md-12"><label class="labels">Phone Number <i><font color="red">${requestScope.error.phoneNumber}</font></i> </label><input type="text" class="form-control" placeholder="Enter phone number" name="phoneNumber" value="${requestScope.user.phoneNumber}" required></div>
                                <div class="col-md-12"><label class="labels">Image</label><input type="file" class="form-control" name="photo"/></div> 
                            </div>

                            <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>
