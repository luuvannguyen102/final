<%-- 
    Document   : resultOrder
    Created on : Jul 2, 2021, 3:33:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<jsp:include page="include/css-page.jsp" />
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
                                        ${result.code}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        Name
                                    </th>

                                    <td colspan="6">
                                        ${result.fullName}
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Phone number
                                    </th>

                                    <td colspan="6">
                                        ${result.numberPhone}
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Address
                                    </th>

                                    <td colspan="6">
                                        ${result.address}
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Gender
                                    </th>

                                    <td colspan="6">
                                        ${result.gender}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        birthDate
                                    </th>

                                    <td colspan="6">
                                        ${result.birthDate}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        Email
                                    </th>

                                    <td colspan="6">
                                        ${result.email}
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
                                
                                <c:forEach items="${result.ordersDetails}" var="detail">
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
                                       Discount
                                    </th>

                                    <td colspan="6">
                                        ${result.discount} %
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Bill price
                                    </th>

                                    <td colspan="6">
                                        <fmt:setLocale value="vi_VN" scope="session"/>
                                        <fmt:formatNumber type="currency" value="${result.totalPrice * (100 - result.discount) / 100}"/>
                                    </td>
                                </tr>

                                <tr>
                                    <th colspan="3">
                                        Time order
                                    </th>

                                    <td colspan="6
                                        ">
                                        ${result.createDate}
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="3">
                                        Status
                                    </th>

                                    <td class="statusColor" colspan="6
                                        " style="color: red">
                                        ${result.orderStatus}
                                    </td>
                                </tr>
                            </table>
                                        <c:if test="${result.orderStatus == status}" >
                                    <div style="text-align: center; margin-bottom: 20px;">
                                        <button onclick="location.href = '<c:url value="/cancelOrder/${result.id}"/>'"
                                                class="btn btn-info" style="width: 20%;">Cancel</button>
                                    </div>
                                                </c:if>
                                    
                        </div>
                    </div>
                </div>                  
            </div>
        </div>
    
        <jsp:include page="include/js-page.jsp"/>