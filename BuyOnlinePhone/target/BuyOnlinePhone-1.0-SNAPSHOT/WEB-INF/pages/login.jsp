<%-- 
    Document   : login
    Created on : Jun 20, 2019, 8:17:26 PM
    Author     : AnhLe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body style="background-color: rgba(201, 76, 76, 0.2)">
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />


        <!-- /login?error=true -->

        <div class="container">
            <!-- row -->
            <div class="row">
                <c:if test="${message != null && message != ''}">
                    <label style="color: red">${message}</label>>
                </c:if>
                <div class="col-md-12">
                    <div class="css-login">
                        <div class="div-login">
                            <h1 class="h-title">Login</h1>
                        </div>
                        <div>
                            <form action="<c:url value="j_spring_security_check"/>" method="post">
                                <div class="div-login">
                                    <label>User Name</label><br>
                                    <input class="input-login" name="username" type="email" placeholder="Enter username"/>
                                </div> 

                                <div class="div-login">
                                    <label>Password</label>    <br>                         
                                    <input class="input-login" name="password" type="password" placeholder="Enter password"/>   
                                </div>

                                <div class="div-login">
                                    <input class="hover input-login btn-danger" type="submit" value="login"/>
                                </div>                             


                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
</html>
