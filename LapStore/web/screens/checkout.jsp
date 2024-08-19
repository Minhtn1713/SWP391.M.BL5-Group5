<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <link rel="stylesheet" href="styles/home.css" type="text/css"/>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/checkout.css" type="text/css"/>

        <!-- Font family -->

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet" />
        <!-- Favicon -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" /> 
    </head>
    <body>
        <jsp:include page="../screens/navbar.jsp"></jsp:include>

            <!-- Cart details section -->
            <div class="container">
                <form action="finish" method="post" >
                <h2>Shipping information</h2>
                <div class="shipping-form">
                    <input name="id" id="id" type="hidden" class="form-control" value="${id}">
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label class="form-label">Full Name</label>
                            <input name="name" id="name" type="text" class="form-control" value="${name}">
                        <p class="text-danger">${errorName}</p>
                    </div>

                    <div class="form-group">
                        <label class="form-label">Phone</label>
                        <input name="phone" id="phone" type="text" class="form-control" value="${phone}">
                        <p class="text-danger">${errorPhone}</p>
                    </div>
                </div>

                <div class="form-group full-width">
                    <label class="form-label">Address</label>
                    <input name="address" id="address" type="text" class="form-control" value="${address}">
                    <p class="text-danger">${errorEmail}</p>
                </div>
            </div>
            <table class="cart-table">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>RAM</th>
                        <th>Storage</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total money</th>
                    </tr>
                </thead>
                <tbody>
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
                                ${i.quantity}
                            </td>
                            <td class="tr">
                                $<fmt:formatNumber pattern="##.#" value="${i.price}" />
                            </td>
                            <td class="tr">
                                $<fmt:formatNumber pattern="##.#" value="${i.price*i.quantity}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="payment-section">
                <h3>Payment</h3>
                <input type="radio" name="payment" id="payOnReceipt" value="Pay on Receipt">
                <label for="payOnReceipt">Pay after the receipt of the products</label><br>
                <input type="radio" name="payment" id="banking" value="Banking" checked>
                <label for="banking">Banking</label>
            </div>

            <div class="total-section">
                <p>Total Price: <span>$<fmt:formatNumber pattern="##.#" value="${o.totalMoney}" /></span></p>
                <p>Ship Price: <span>$0</span></p>
                <p>Total Pay: <span>$<fmt:formatNumber pattern="##.#" value="${o.totalMoney}" /></span></p>
                <p><span>
                <button type="submit" class="finish-button">Finish</button></span></p>
            </div>          
                </form>
        </div>

        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
</html>
