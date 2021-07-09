<%-- 
    Document   : bill
    Created on : Apr 2, 2021, 11:03:40 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="include/css-page.jsp" />
        <title>bill</title>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <div class="container">           
            <div class="row">
                <div class="col-md-12 col-lg-12">                
                    <h1 class="colorTitle">Bill info</h1>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-lg-12">
                        <div class="table-responsive">
                            <table style="border: 2px solid #269abc" class="table table-bordered table-hover">
                                <tr>
                                    <th colspan="3">
                                        Code
                                    </th>

                                    <td colspan="6">
                                        ${order.code}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        Name
                                    </th>

                                    <td colspan="6">
                                        ${order.fullName}
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Phone number
                                    </th>

                                    <td colspan="6">
                                        ${order.numberPhone}
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Address
                                    </th>

                                    <td colspan="6">
                                        ${order.address}
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Gender
                                    </th>

                                    <td colspan="6">
                                        ${order.gender}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        birthDate
                                    </th>

                                    <td colspan="6">
                                        ${order.birthDate}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        Email
                                    </th>

                                    <td colspan="6">
                                        ${order.email}
                                    </td>
                                </tr>
                                <tr>
                                    <th style="color: #269abc" colspan="9" class="centerTh">
                                        Orders Info
                                    </th>

                                    
                                </tr>

                                <tr>
                                    <th colspan="3"> Product </th>
                                    <th> Image </th>
                                    <th> Memory </th>
                                    
                                    <th> Color </th>
                                    <th>Unit Price </th>
                                    <th> Quantity </th>
                                    <th> Total price </th>
                                </tr>
                                
                                <c:forEach items="${order.ordersDetails}" var="detail">
                                    <tr>
                                        <td colspan="3"> ${detail.product.name} </td>
                                        
                                            <td> 
                                                <img class="miniImage" src="<c:url value="/resources/img/${detail.product.image}"/>" alt="">
                                        </td>
                                      
                                        <td> 
                                            ${detail.product.memory} 
                                        </td>
                                        <td> 
                                            ${detail.product.color} 
                                        </td>
                                        <td> 
                                            <fmt:setLocale value="vi_VN" scope="session"/>
                                            <fmt:formatNumber type="currency" value="${detail.product.price}"/>   
                                        </td>

                                        <td> 
                                            ${detail.quantity} 
                                        </td>

                                        <td> 
                                            <fmt:setLocale value="vi_VN" scope="session"/>
                                            <fmt:formatNumber type="currency" value="${detail.amount}"/>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <tr>
                                    <th colspan="3">
                                        Bill price
                                    </th>

                                    <td colspan="6">
                                        <fmt:setLocale value="vi_VN" scope="session"/>
                                        <fmt:formatNumber type="currency" value="${order.totalPrice}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Time order
                                    </th>

                                    <td colspan="6
                                        ">
                                        ${order.createDate}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        Status
                                    </th>

                                    <td class="statusColor" colspan="6
                                        ">
                                        ${order.orderStatus}
                                    </td>
                                </tr>
                            </table>
                                    <button onclick="location.href = '<c:url value="/cancelOrder/${order.id}"/>'"
                            class="btn btn-info">Cancel</button>
                        </div>
                    </div>
                </div>                  
            </div>
        </div>
        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
</html>
