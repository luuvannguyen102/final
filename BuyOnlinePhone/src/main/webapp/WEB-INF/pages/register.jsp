<%-- 
    Document   : login
    Created on : Jun 20, 2019, 8:17:26 PM
    Author     : AnhLe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <jsp:include page="include/css-page.jsp"/>
    </head>
    <body>       
        <mvc:form action="${pageContext.request.contextPath}/newAccount" method="post" modelAttribute="account">
            <div class="col-md-6">
                <div class="billing-details">
                    <div class="section-title">
                        <h3 class="title">Register</h3>
                    </div>
                    <div class="form-group">
                        <input class="input" type="email"  name="email" placeholder="Enter Email">
                    </div>
                    <div class="form-group">
                        <input class="input" type="password"  name="password" placeholder="Enter Password">
                    </div>
                    <div class="form-group">
                        <input class="input" type="password"  name="repassword" placeholder="ReEnter Password">
                    </div>
                    
                    <div class="form-group">
                        <input class="input" type="text" name="fullName" placeholder="Full Name">
                    </div>                                              
                    <div class="form-group">
                        <input class="input" type="text" name="address" placeholder="Address">
                    </div>                            
                    <div class="form-group">
                        <input class="input" type="date" name="birthDate" placeholder="birthDate">
                    </div>
                    <div class="form-group">
                        <select name="gender">
                            <option>${MALE}</option>
                            <option>${FEMALE}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input class="input" type="tel" name="phone" placeholder="Telephone">
                    </div> 
                    <div class="form-group">
                        <button type="submit">Register</button>
                    </div>

                </div>
            </div>
        </mvc:form>
    </body>
</html>
