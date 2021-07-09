<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- HEADER -->
<header>

    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-phone"></i> +84-388-684-854</a></li>
                <li><a href="#"><i class="fa fa-envelope-o"></i> nguyensaigonmit@gmail.com</a></li>
                <li><a href="#"><i class="fa fa-map-marker"></i> Viet Nam</a></li>
            </ul>
            <ul class="header-links pull-right">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <p><a href="<c:url value="/admin/home" />">${userName}</a></p>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                        <li><a style="color: #269abc" href="<c:url value="/user/mypage" />">${userName}</a></li>
                        <li><a style="color: #269abc" href="<c:url value="/logout" />">Logout</a></li>
                        </sec:authorize>

                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="<c:url value ="/login"/>"><i class="fa fa-user-o"></i> Login</a></li>
                    <li><a href="<c:url value ="/register"/>"><i class="fa fa-user-o"></i> Register</a></li>
                    </sec:authorize>


            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <a href="<c:url value="/"/>" class="logo">
                            <img src="<c:url value ="/resources/img/logo.png"/>" alt="mangyeu">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search" style="text-align: center">
                        <form action="search" method="post">                         
                            <input  class="input-select" placeholder="Search here" name="search">
                            <button  class="search-btn">Search</button>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">

                        <!-- Cart -->
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                <i class="fa fa-shopping-cart"></i>
                                <span>Your Cart</span>
                                <c:if test="${sessionScope.orders.countOrdersDetailInOrders > 0}">
                                    <div class="qty">${sessionScope.orders.countOrdersDetailInOrders}</div>
                                </c:if>
                            </a>
                            <div class="cart-dropdown">
                                <div class="cart-list">
                                    <c:forEach items="${sessionScope.orders.ordersDetails}" var="detail">
                                        <div class="product-widget">

                                            <div class="product-img">
                                                <img src="<c:url value ="resources/img/${detail.product.image}"/>" alt="">
                                            </div>

                                            <div class="product-body">
                                                <h3 class="product-name"><a href="#">${detail.product.name}</a></h3>
                                                    <fmt:setLocale value="vi_VN" scope="session"/>
                                                <h4 class="product-price"><span class="qty">x${detail.quantity}</span><fmt:formatNumber type="currency"  value="${detail.amount}"/></h4>
                                            </div>
                                            <button class="delete" onclick="location.href = '<c:url value="/removeOrdersDetail/${detail.product.id}"/>'"><i class="fa fa-close"></i></button>
                                        </div>
                                    </c:forEach>

                                </div>
                                <div class="cart-summary">
                                    <small>${sessionScope.orders.countOrdersDetailInOrders} Item(s) selected</small>
                                    <fmt:setLocale value="vi_VN" scope="session"/>
                                    <h5>SUBTOTAL: <fmt:formatNumber type="currency"  value="${sessionScope.orders.totalPrice}"/></h5>
                                </div>
                                <div class="cart-btns">
                                    <a href="<c:url value="/order"/>">View Cart</a>
                                    <a href="<c:url value="/customerInfo"/>">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <!-- /Cart -->

                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
        
</header>


<!-- /HEADER -->