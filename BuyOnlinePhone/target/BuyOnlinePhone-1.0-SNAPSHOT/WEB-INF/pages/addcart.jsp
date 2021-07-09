<%-- 
    Document   : addcart
    Created on : Mar 18, 2021, 7:12:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">
            <!-- container -->
            <div  class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="breadcrumb-header">Checkout</h3>
                        <ul class="breadcrumb-tree">
                            <li><a href="#">Home</a></li>
                            <li class="active">Checkout</li>
                        </ul>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /BREADCRUMB -->
        <!-- SECTION -->
        <div  class="section">
            <!-- container -->
            <div id="updateQuantity" class="container">               
                    <!-- row -->
                    <div class="row">                        

                        <!-- Order Details -->

                        <div class="col-md-12 order-details section-title text-center">
                            <h3 class="title">Cart view </h3>
                        </div>                  
                    </div>
                    <div class="row">
                        <h3>There are ${sessionScope.orders.countOrdersDetailInOrders} products in the cart</h3>                            
                    </div> 
                    <c:if test="${sessionScope.orders == null || fn:length(sessionScope.orders.ordersDetails) <= 0}">
                        <h3 class="h3">
                            No products selected    
                        </h3>
                    </c:if>
                    <c:if test="${sessionScope.orders != null && fn:length(sessionScope.orders.ordersDetails) > 0}">
                        <c:forEach items="${sessionScope.orders.ordersDetails}" var="detail">
                            <input id="productId" value="${detail.product.id}" type="hidden">
                            <div class="row cart">
                                <div class="col-md-2">
                                    <div class="product-preview">
                                        <img src="<c:url value="/resources/img/${detail.product.image}"/>">
                                    </div>

                                </div>
                                <div class="col-md-2">
                                    ${detail.product.name} - ${detail.product.memory}

                                </div>
                                <div class="col-md-2">
                                    ${detail.product.color}
                                </div>

                                <div class="col-md-2">
                                    <div class="add-to-cart">
                                        <div class="qty-label">                                    
                                            <div class="input-number">
                                                <input id="${detail.product.id}" onchange="checkQuantity(${detail.product.id}, $('#' +${detail.product.id}).val())"  type="number" value="${detail.quantity}">
                                                <span  class="qty-up">+</span>
                                                <span class="qty-down">-</span>


                                            </div>

                                        </div> 
                                    </div>

                                </div>

                                <div class="col-md-2" id="amount-detail">
                                    <fmt:setLocale value="vi_VN" scope="session"/>
                                    <fmt:formatNumber type="currency"  value="${detail.amount}"/>
                                </div>
                                <div class="col-md-2">
                                    <a class="active hover" href="<c:url value="/removeOrdersDetail/${detail.product.id}"/>">
                                        <i class="fa fa-close"></i> 
                                    </a>
                                </div>
                            </div>
                        </c:forEach>

                        <div id="myModal" class="modal">

                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <p class="btnjs">quantity is not enough</p>
                                <button id="ok" class="btn btn-warning btnjs" type="button">Ok</button>
                            </div>

                        </div>
                        <div id="ha" class="row">
                            <div class="col-md-6">
                                <span>Discount code</span><br>
                                <input id="codeDiscount" type="text" placeholder="Enter discount code">
                                <button id="discount" class="btn-danger" type="button">Apply</button>
                            </div>


                            <div class="col-md-3">
                                <span>total amount:</span><br>
                                <span>reduction amount:</span><br>
                                <span>Need to pay:</span>                           
                            </div>
                            <div class="col-md-3">
                                <fmt:setLocale value="vi_VN" scope="session"/>                                
                                <span id="amount-total"><fmt:formatNumber type="currency"  value="${sessionScope.orders.totalPrice}"/></span><br>
                                <span id="giam"><fmt:formatNumber type="currency"  value="${sessionScope.orders.discount * sessionScope.orders.totalPrice / 100}"/></span><br>
                                <span id="total"><fmt:formatNumber type="currency"  value="${sessionScope.orders.totalPrice - sessionScope.orders.discount * sessionScope.orders.totalPrice / 100}"/></span>                           
                            </div>
                        </div> 
                        <div class="row">
                            <div class="col-md-12">
                                <a href="<c:url value="/customerInfo"/>">
                                    <button id="yes" >Continue Order</button>
                                </a>

                            </div>
                        </div>                            
                    </c:if>
                    <!-- /Order Details -->
                </div>

        </div>
        <!-- /row -->



        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
    <script>



        $('#discount').on('click', function () {
            var discount = $('#codeDiscount').val();
            debugger;
            $.ajax({
                type: "get",
                url: '${pageContext.request.contextPath}/checkDiscount/'
                        + discount,
                success: function (result) {
                    if (JSON.parse(result) === "not true") {
                        alert("code voucher not true");
                    } else if (JSON.parse(result) === "code used") {
                        alert("code used");
                    } else if (JSON.parse(result) === "expired") {
                        alert("code voucher expired");
                    } else {

                        $('#ha').load('/BuyOnlinePhone/discount');

                    }

                }
            })
        });



        function checkQuantity(a, b) {
            $.ajax({
                type: "get",
                url: '${pageContext.request.contextPath}/checkQuantityCardByAjax/'
                        + a + '/' + b,
                success: function (result) {
                    if (JSON.parse(result) === "error") {
                        modal.style.display = "block";
                    } else {
                        var price = document.getElementById('amount-detail').innerHTML;
                        document.getElementById('amount-detail').innerHTML = parseFloat(price)
                        $('#updateQuantity').load('/BuyOnlinePhone/updateQuantityByAjax');
                    }
                }
            });
        }
        ;
        var modal = document.getElementById("myModal");
        var span = document.getElementsByClassName("close")[0];
        var ok = document.getElementById("ok");
        span.onclick = function () {

            modal.style.display = "none";
        };
        ok.onclick = function () {

            modal.style.display = "none";
        }


//        
//        function test (id) {
//          alert(id);
//        }
//        var oldQuantity = $('#quantity').val();

//        function update(productId) {



//
//        $('#quantity').on('change', function () {
//        var quantity = $(this).val();
//                var productId = $('#productId').val();
//                var modal = document.getElementById("myModal");
//                var span = document.getElementsByClassName("close")[0];
//                var ok = document.getElementById("ok");
//                span.onclick = function () {
//                document.getElementById('quantity').value = oldQuantity;
//                        modal.style.display = "none";
//                };
//                ok.onclick = function () {
//                document.getElementById('quantity').value = oldQuantity;
//                        modal.style.display = "none";
//                }

//        var customer = document.getElementById("customer");
//        var close = document.getElementsByClassName("close")[0];
//        var yes = document.getElementById("yes");
//        yes.onclick = function () {
//            customer.style.display = "block";
//        };
//        close.onclick = function () {
//            customer.style.display = "none";
//        }

//        $('#checkDiscount').on('click', function () {
//        var codeDiscount = $('#codeDiscount').val();
//                var productId = $('#productId').val();
//                var price = parseFloat($('#price').val());
//                $.ajax({
//                type: "get",
//                        url: '${pageContext.request.contextPath}/checkDiscount/'
//                        + codeDiscount + '/' + productId,
//                        success: function (result) {
//                        if (parseInt(JSON.parse(result)) > 0) {
//                        document.getElementById('discount').value = parseInt(JSON.parse(result));
//                                $('#setPrice').attr('value',
//                                parseFloat(price * (100 - parseInt(JSON.parse(result))) / 100).toLocaleString('vi-VN', {
//                        style: 'currency',
//                                currency: 'VND'
//                        }));
//                        } else if (result.length - 2 == 0) {
//                        alert("code voucher not true");
//                        } else {
//                        alert("code voucher expired");
//                        }
//                        }
//                }
//                )
//        }
//
//        );



    </script>
</html>
