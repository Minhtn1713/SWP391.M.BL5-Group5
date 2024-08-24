<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/home.css" type="text/css"/>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/mycart.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet" />
        <!-- Favicon -->

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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


            table{
                border-collapse:  collapse;
            }
            .tr{
                text-align: right;
            }
            a{
                text-decoration: none;
                font-size: 22px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>

        <jsp:include page="../screens/navbar.jsp"></jsp:include>

            <div class="container">
            <c:if test="${size != 0}">
                <table border="1px" width="40%">
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>RAM</th>
                        <th>Storage</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total money</th>
                        <th>Action</th>
                    </tr>
                    <c:set var="o" value="${requestScope.cart}"/>
                    <c:set var="tt" value="0" />
                    <c:forEach items="${o.items}" var="i">
                        <c:set var="tt" value="${tt+1}"/>
                        <tr>
                            <td>${tt}</td>
                            <td>${i.productVariant.name}</td>
                            <td>${i.productVariant.ram}</td>
                            <td>${i.productVariant.storage}</td>
                            <td style="text-align: center">
                                <button><a href="process?num=-1&id=${i.productVariant.id}">-</a></button>
                                ${i.quantity}
                                <button><a href="process?num=1&id=${i.productVariant.id}">+</a></button>
                            </td>
                            <td class="tr">
                                <fmt:formatNumber pattern="#,###" value="${i.price}" /> VNĐ
                            </td>
                            <td class="tr">
                                <fmt:formatNumber pattern="#,###" value="${i.price*i.quantity}" /> VNĐ
                            </td>
                            <td style="text-align: center"> 
                                <form action="process" method="post">
                                    <input type="hidden" name="id" value="${i.productVariant.id}" />
                                    <input type="submit" value="remove"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="total-money">
                    <h3>Total money: $<fmt:formatNumber pattern="##.#" value="${o.totalMoney}" /></h3>
                </div>

                <div class="checkout-container">
                    <form action="checkout" method="post">
                        <input type="submit" value="Check out"/>
                    </form>
                </div>
            </c:if>

            <c:if test="${size == 0}">
                <h3 style="text-align:center; margin: 50px 0;">There is no product in your cart</h3>
            </c:if>

            <a href="shop">Click Me To Continue Shopping</a>
        </div>

        <hr />
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
                                    Failed to Checkout!
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