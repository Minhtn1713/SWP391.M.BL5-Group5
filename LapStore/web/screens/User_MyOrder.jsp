<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.OrderDetail"%>
<%@page import="dao.ProductVariantDAO"%>
<%@page import="dao.OrderDetailDAO"%>
<%@page import="model.ProductVariant"%>
<%@page import="model.Sale"%>
<%@page import="model.Ram"%>
<%@page import="model.Product"%>
<%@page import="model.Storage"%>
<%@page import="dao.SaleDAO"%>
<%@page import="dao.RamDAO"%>
<%@page import="dao.ProductDAO"%>
<%@page import="dao.StorageDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>LapStore</title>
        <!-- css -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/my-order.css" type="text/css"/>
        <!-- Font family -->
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
                        <a id="setting" class="left-line" href="/LapStore_main/account-profile?status=setting"><i class="fa fa-cog" aria-hidden="true"></i> Setting</a><br/><br/>
                        <a class="left-line" href="my-order"><i class='fa fa-shopping-bag'></i> My order</a><br/><br/>
                        <a id="wallet" class="left-line" href="/LapStore_main/wallet"><i class="fa fa-credit-card-alt" aria-hidden="true"></i> My Wallet</a><br/><br/>
                    </div>
                    <div class="right-container" id="right-content">
                        <div class="product-title">
                            <div class="filter" id="1" data-value="filter=1">All</div>
                            <div class="filter" id="2" data-value="filter=2">Waiting for payment</div>
                            <div class="filter" id="3" data-value="filter=3">Processing</div>
                            <div class="filter" id="4" data-value="filter=4">In transit</div>
                            <div class="filter" id="5" data-value="filter=5">Delivered</div>
                            <div class="filter" id="6" data-value="filter=6">Cancel</div>
                            <div class="filter" id="7" data-value="filter=7">Refund</div>
                        </div>
                        <div class="search-box">
                            <i class="fa fa-search" aria-hidden="true" id="search-icon"></i>
                            <input id="search-input" value="" type="text" placeholder="You can search by product name..." />
                        </div>
                    <c:forEach items="${orderList}" var="order">
                        <c:set var="orderId" value="${order.id}" />
                        <%int id = Integer.parseInt(String.valueOf(pageContext.getAttribute("orderId")));
                         List<Integer> listId = (List<Integer>) request.getAttribute("productId");
                         boolean check = false;
                         for (Integer list:listId){
                             if (list == id){
                               check = true;
                            }
                         }
                         if (check == true){
                        %>
                        <div class="card">
                            <div class="card-title">
                                <div style="width: 80%">
                                    <c:if test="${order.status == 3}">Your payment is being processed</c:if>
                                    <c:if test="${order.status == 2}">Your order is being processed</c:if>
                                    <c:if test="${order.status == 4}">Your order is in transit</c:if>
                                    <c:if test="${order.status == 5}">The order has been delivered successfully</c:if>
                                    <c:if test="${order.status == 6}">Your order has been canceled</c:if>
                                    <c:if test="${order.status == 7}">Your payment has been refunded</c:if>
                                    </div>
                                    <div style="width: 20%; cursor: pointer"><a href="order-history?orderId=${order.id}">View Order Detail</a></div>
                            </div>
                            <hr />
                            <div class="card-content">
                                <table>
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <% 
                                            OrderDetailDAO odd = new OrderDetailDAO();
                                            List<OrderDetail> od = odd.getAllOrderDetail(id);
                                            RamDAO ramDAO = new RamDAO();
                                            StorageDAO stoDao = new StorageDAO();
                                            ProductDAO proDao = new ProductDAO();
                                            SaleDAO saleDao = new SaleDAO();
                                            ProductVariantDAO pvd = new ProductVariantDAO();
                                            for (OrderDetail order : od){
                                                ProductVariant pv = pvd.getProductVariantByID(order.getProductId());
                                                Ram r = ramDAO.getRambyId(pv.getRamId());
                                                Storage s = stoDao.getStoragebyId(pv.getStorageId() + "");
                                                Product d = proDao.getProductByID(pv.getProductId() + "");
                                                Sale sale = saleDao.getSaleById(pv.getSaleId());
                                                float priceSale = pv.getVariantPrice() * (1-sale.getPercent()/100);
                                                String url = pvd.getOneProductVariantImage(d.getId(), "").getUrl();
                                        %> 
                                        <tr>
                                            <td><img class="card-img" src="img/<%=url%>" alt="alt"/></td>
                                            <td><span class="product-name"><%=d.getName()%> </span><span class="product-color"><%=r.getName()%></span>
                                                <span class="product-storage"><%=s.getStorageSize()%></span>
                                                <div class="product-quantity">x<%=order.getQuantity()%></div></td>
                                            <td><%if (pv.getSaleId() != 1){%>
                                                <span style="text-decoration: line-through;">$<%=pv.getVariantPrice()%></span>
                                                <span style="font-size: larger">$<%=priceSale%></span>
                                                <%}else{%>
                                                $<%=pv.getVariantPrice()%>
                                                <% }%>
                                            </td>
                                        </tr>
                                        <%}%>

                                    </tbody>
                                </table>
                            </div>
                            <div class="total-amount">
                                <p>Total amount: $${order.totalPrice}</p>
                                <c:if test="${order.status != 1 && order.status != 5 && order.status != 6 && order.status != 7 }"><a href="admin-change-order-status?orderId=<%=id%>&statusId=6&redirect=2"><button>Cancel order</button></a></c:if>
                                </div>
                            </div>
                        <%}%>

                    </c:forEach>

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

                const searchIcon = document.getElementById("search-icon");
                const searchInput = document.getElementById("search-input");

                searchIcon.addEventListener("click", function () {
                    submitSearchForm();
                });

                searchInput.addEventListener("keydown", function (event) {
                    if (event.key === "Enter") {
                        submitSearchForm();
                    }
                });

                function submitSearchForm() {
                    const searchQuery = searchInput.value;
                    const servletUrl = "my-order";
                    var queryString = window.location.search;
                    var urlParams = new URLSearchParams(queryString);
                    var filter = urlParams.get('filter');
                    if (filter) {
                        window.location.href = `${servletUrl}?filter=` + filter + `&sr=` + searchQuery;
                    } else {
                        window.location.href = `${servletUrl}?filter=1&sr=` + searchQuery;
                    }

                }
                const filter = document.querySelectorAll(".filter");
                filter.forEach(items => {
                    items.addEventListener("click", () => {
                        const value = items.getAttribute("data-value");
                        const servletUrl = "my-order";
                        window.location.href = `${servletUrl}?` + value;
                    });
                });

                const addActive = () => {
                    var queryString = window.location.search;
                    var urlParams = new URLSearchParams(queryString);
                    var filter = urlParams.get('filter');
                    if (filter) {
                        const border = document.getElementById(filter);
                        border.classList.add("active");
                    } else {
                        const border = document.getElementById("1");
                        border.classList.add("active");
                    }
                };
                addActive();
        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var toastEl = document.getElementById('customToast');
                var closeBtn = document.querySelector('.toast-close');

                if (toastEl) {
                    // Automatically hide the toast after a delay
                    setTimeout(function () {
                        toastEl.style.display = 'none';
                    }, 2000);

                    // Close the toast when the close button is clicked
                    closeBtn.addEventListener('click', function () {
                        toastEl.style.display = 'none';
                    });
                }
            });
        </script>
    </body>
</html>



















































