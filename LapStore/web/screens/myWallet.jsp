<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            .toast-container {
                position: fixed;
                bottom: 0;
                right: 0;
                margin: 1rem;
                z-index: 1050;
            }

            .toast {
                display: flex;
                flex-direction: column;
                width: 350px;
                background-color: #fff;
                border: 1px solid rgba(0, 0, 0, 0.1);
                border-radius: 0.25rem;
                box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
                animation: fadeIn 0.5s;
            }

            .toast-header {
                display: flex;
                align-items: center;
                padding: 0.5rem;
                border-bottom: 1px solid rgba(0, 0, 0, 0.1);
                background-color: #f8f9fa;
            }

            .toast-header img.web-logo {
                margin-right: 0.5rem;
                height: 20px;
                width: 20px;
            }

            .toast-title {
                flex-grow: 1;
                font-weight: bold;
            }

            .toast-time {
                margin-left: 0.5rem;
                font-size: 0.875rem;
            }

            .toast-close {
                background: none;
                border: none;
                font-size: 1.5rem;
                cursor: pointer;
            }

            .toast-body {
                padding: 0.75rem;
                color: #212529;
            }

            .toast-body.success {
                color: #28a745;
            }

            .toast-body.error {
                color: #dc3545;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(100%);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
            .right-container h2 {
                margin-bottom: 20px;
                font-size: 24px;
                color: #333;
            }

            .button-group {
                display: inline-flex;
                gap: 20px; /* Space between buttons */
            }

            .button-group button {
                display: inline-flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                padding: 20px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                width: 120px;
                height: 120px;
            }

            .button-group button i {
                font-size: 24px;
                margin-bottom: 8px;
            }

            .button-group button span {
                font-size: 14px;
                font-weight: bold;
            }

            .btn-deposit {
                background-color: #28a745;
                color: white;
            }

            .btn-deposit:hover {
                background-color: #218838;
            }

            .btn-shopping {
                background-color: #007bff;
                color: white;
            }

            .btn-shopping:hover {
                background-color: #0056b3;
            }

            .button-group a {
                display: inline-flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                padding: 30px;
                font-size: 18px;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                transition: background-color 0.3s ease;
                width: 150px;
                height: 150px;
                text-decoration: none; /* Remove underline from links */
                color: white; /* Ensure text color is white */
            }

            .button-group a i {
                font-size: 32px; /* Increased icon size */
                margin-bottom: 10px;
            }

            .button-group a span {
                font-size: 16px; /* Increased text size */
                font-weight: bold;
            }

            .btn-deposit {
                background-color: #28a745;
            }

            .btn-deposit:hover {
                background-color: #218838;
            }

            .btn-shopping {
                background-color: #007bff;
            }

            .btn-shopping:hover {
                background-color: #0056b3;
            }
        </style>
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

                        <h2>Current Balance: <fmt:formatNumber value="${balance}" type="balance" pattern="#,###" /> VNĐ</h2>
                    <div class="button-group">
                        <a href="/LapStore_main/ajaxServlet" class="btn-icon btn-deposit">
                            <i class="fa-solid fa-plus"></i>
                            <span>Deposit Balance</span>
                        </a>
                        <a href="/LapStore_main/store" class="btn-icon btn-shopping">
                            <i class="fas fa-shopping-cart"></i>
                            <span>Shopping Now</span>
                        </a>

                    </div>
                </div>

            </div>
        </div>
        <c:choose>
            <c:when test="${param.successed != null}">
                <div id="toast-container" class="toast-container">
                    <div id="customToast" class="toast">
                        <div class="toast-header">
                            <img src="../assets/images/favicon.ico.png" class="web-logo" alt="web-logo">
                            <strong class="toast-title">LapStore_main</strong>
                            <small class="toast-time">A few seconds ago</small>
                            <button type="button" class="toast-close" aria-label="Close">&times;</button>
                        </div>
                        <c:choose>
                            <c:when test="${param.successed == 'yes'}">
                                <div class="toast-body success">
                                    Checkout successfully!
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="toast-body error">
                                    Your current balance is not enough !
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:when>
        </c:choose>
        <jsp:include page="../screens/footer.jsp"></jsp:include>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var toastEl = document.getElementById('customToast');
                var closeBtn = document.querySelector('.toast-close');

                if (toastEl) {
                    // Automatically hide the toast after a delay
                    setTimeout(function () {
                        toastEl.style.display = 'none';
                    }, 5000);

                    // Close the toast when the close button is clicked
                    closeBtn.addEventListener('click', function () {
                        toastEl.style.display = 'none';
                    });
                }
            });
        </script>
    </body>
</html>