<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>LapShop</title>
        <!-- css -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/account-profile.css" type="text/css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap"
            rel="stylesheet"
            />
        <!-- Favicon -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <div class="profile-container">
                <div class="profile">
                    <div class="left-container">
                        <a id="profile" class="left-line" href="/LapStore_main/account-profile?status=profile"><i class='fa fa-user'></i> Profile</a><br/><br/>
                        <a id="setting" class="left-line" href="/LapStore_main/account-profile?status=setting"><i class="fa fa-cog" aria-hidden="true"></i> Change Password</a><br/><br/>
                        <a class="left-line" href="my-order"><i class='fa fa-shopping-bag'></i> My order</a><br/><br/>
                        <a id="wallet" class="left-line" href="/LapStore_main/wallet"><i class="fa fa-credit-card-alt" aria-hidden="true"></i> My Wallet</a><br/><br/>
                    </div>
                    <div class="right-container" id="right-content">
                    <c:choose>
                        <c:when test="${param.status == 'profile'}">
                            <form method="POST" action="account-profile?status=profile">
                                <p>Full name</p>
                                <input name="fullName" class="user-inf" value="${param.fullName != null ? param.fullName : user.fullName}" required/><br/>
                                <c:if test="${not empty error6}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error6}</h3><br/></c:if>
                                    <p>Gender</p>
                                    <select name="gender" class="user-inf">
                                        <option value="0" ${param.gender == '0' ? 'selected' : (user.gender == '0' ? 'selected' : '')}>Male</option>
                                    <option value="1" ${param.gender == '1' ? 'selected' : (user.gender == '1' ? 'selected' : '')}>Female</option>
                                </select><br/>
                                <p>Phone</p>
                                <input name="phone" type="number" class="user-inf" value="${param.phone != null ? param.phone : user.email}" required/><br/>
                                <c:if test="${not empty error3}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error3}</h3><br/></c:if>
                                    <p>Email</p>
                                    <input name="email"type="email" class="user-inf" value="${param.email != null ? param.email : user.phone}" required/><br/>  
                                <c:if test="${not empty error4}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error4}</h3><br/></c:if>
                                    <p>Address</p>
                                    <input name="address" class="user-inf" value="${param.address != null ? param.address : user.address}" required/>
                                <c:if test="${not empty error7}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error7}</h3><br/></c:if>
                                    <input class="update" type="submit" value="Update">
                                </form>
                        </c:when>
                        <c:when test="${param.status == 'setting'}">
                            <form method="POST" action="account-profile?status=setting">
                                <p>Current password</p>
                                <input type="password" name="current-password" class="user-inf" required/><br/>
                                <c:if test="${not empty error}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error}</h3><br/></c:if>
                                    <p>New password</p>
                                    <input type="password" name="newPassword" class="user-inf" required/><br/>
                                <c:if test="${not empty error5}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error5}</h3><br/></c:if>
                                    <p>Verify new password</p>
                                    <input type="password" name="rePassword" class="user-inf"  required/>
                                <c:if test="${not empty error2}"><h3 style="color: red; text-align: center; width: 100%; margin: auto">${error2}</h3><br/></c:if>
                                    <input class="update" type="submit" value="Update">
                                </form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
        <jsp:include page="../screens/footer.jsp"></jsp:include>

            <script>
            <% String updateSuccess = (String) session.getAttribute("updateSuccess"); %>
            <% if (updateSuccess != null) { %>
                alert("<%= updateSuccess %>");
            <% session.removeAttribute("updateSuccess"); %>
            <% } %>
        </script>
    </body>
</html>