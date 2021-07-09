<%-- 
    Document   : mypage
    Created on : Apr 5, 2021, 3:31:40 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Page</title>
        <jsp:include page="../include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="../include/header.jsp" />
        <jsp:include page="../include/menu1.jsp" />
        <div class="section">
            <div class="container">
                
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <div class="table-responsive">
                            <h1 class="centerTh">History Order</h1>
                            <div class="col-md-6">
                                <mvc:form action="${pageContext.request.contextPath}/user/searchOrderCode" method="post">                       
                                    <input name="search" style="padding: 5px; margin-left: -12px;" type="text" placeholder="Enter Order Code">
                                    <button class="btn btn-danger">Search</button>
                                </mvc:form>
                            </div>

                            <c:if test="${notFound == null}">
                                <table class="table table-bordered table-hover">

                                    <tr style="background-color:  #398439">
                                        <th> Create Date </th>
                                        <th> Code </th>
                                        <th> Quantity Product </th>
                                        <th> Total price </th>
                                        <th> Discount </th>
                                        <th> payment amount </th>                                       
                                        <th> Note</th>
                                        <th> Status </th>
                                        <th> Action </th>
                                    </tr>
                                    <c:forEach items="${account.orders}" var="order">   

                                        <tr style="background-color:  yellow">
                                            <td> ${order.createDate} </td>
                                            <td>
                                                ${order.code}
                                            </td> 
                                            <td> 
                                                ${order.countOrdersDetailInOrders}
                                            </td>

                                            <td> 
                                                <fmt:setLocale value="vi_VN" scope="session"/>
                                                <fmt:formatNumber type="currency" value="
                                                                  ${order.totalPrice}"/>   

                                            </td>
                                            <td> 
                                                ${order.discount} %   

                                            </td>
                                            <td> 
                                                <fmt:setLocale value="vi_VN" scope="session"/>
                                                <fmt:formatNumber type="currency" value="
                                                                  ${order.totalPrice - order.totalPrice * order.discount / 100}"/>   

                                            </td>

                                            <td> 
                                                ${order.note} 
                                            </td>
                                            <td style="color: #66afe9"> 
                                                ${order.orderStatus}

                                            </td>

                                            <td> 
                                                <c:if test="${order.orderStatus == status}">
                                                    <button onclick="location.href = '<c:url value="/user/cancelOrder/${order.id}"/>'" class="btn btn-danger">Cancel</button>
                                                </c:if>
                                                <button onclick="detail(${order.id})" class="btn btn-warning">Detail</button>
                                            </td>


                                        </tr>

                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                        <c:if test="${notFound != null}">
                            <h2 style="color: red;font-family: sans-serif;font-size: 200%;text-align: center">${notFound}</h2></c:if>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <div class="table-responsive">
                                <table id="loadDetail">

                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        <jsp:include page="../include/footer.jsp"/>
        <jsp:include page="../include/js-page.jsp"/>
    </body>
    <script>
        function  detail(id) {
            $('#loadDetail').load('/BuyOnlinePhone/user/detail/' + id);
        }
    </script>
</html>
