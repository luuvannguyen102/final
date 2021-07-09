<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="include/css-page.jsp" />
<div class="row">
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
<jsp:include page="include/js-page.jsp"/>