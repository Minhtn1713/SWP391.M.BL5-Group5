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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
        <style>
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
                                $<fmt:formatNumber pattern="##.#" value="${i.price}" />
                            </td>
                            <td class="tr">
                                $<fmt:formatNumber pattern="##.#" value="${i.price*i.quantity}" />
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
        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
</html>