<%-- 
    Document   : detail
    Created on : Jul 2, 2021, 4:40:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<jsp:include page="include/css-page.jsp" />
<table style="border: 2px solid #269abc" class="table table-bordered table-hover">

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
        <fmt:formatNumber type="currency" value="${detail.product.salePrice}"/>   
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

   
</table>
<jsp:include page="include/js-page.jsp"/>