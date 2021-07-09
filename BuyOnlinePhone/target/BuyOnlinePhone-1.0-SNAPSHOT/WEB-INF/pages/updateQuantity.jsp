<%-- 
    Document   : updateQuantity
    Created on : Jul 3, 2021, 1:36:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<jsp:include page="include/css-page.jsp" />
<div class="container">               
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
<jsp:include page="include/js-page.jsp"/>