<%-- 
    Document   : bill
    Created on : Mar 27, 2021, 3:44:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="include/css-page.jsp" />
        <style>
            .error {
                color : red;
                
               
            }
            label {
                margin-right: 70px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <!-- SECTION -->
        <div class="section"  style="background-color: #269abc">
            <!-- container -->
            <div class="container" >
                <!-- row -->
                <div class="row">

                    <div class="col-md-12">
                        <!-- Billing Details -->
                        <div class="billing-details customer-credit">                            
                            <div class="section-title h1title">
                                <h3 class="title">Customer Info</h3>
                            </div>
                            <mvc:form action="${pageContext.request.contextPath}/credit" method="POST" modelAttribute="orderInfo">
                                
                                <div class="form-group">
                                   <label>Full Name</label>
                                    <input class="input" type="text" name="fullName" value="${orderInfo.fullName}">
                                    <mvc:errors path="fullName" cssClass="error"/>
                                </div>  
                                
                                <div class="form-group">
                                    <label>Email</label>
                                    <input class="input" type="email" name="email" value="${orderInfo.email}">
                                    <mvc:errors  path="email" cssClass="error"/>
                                </div>
                                
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="input" type="text" name="address" value="${orderInfo.address}">
                                     <mvc:errors path="address" cssClass="error"/>
                                </div>                        
                                
                                <div class="form-group">
                                    <label>BirthDate</label>
                                    <input class="input" type="date" name="birthDate" value="${orderInfo.birthDate}">
                                    <mvc:errors path="birthDate" cssClass="error"/>
                                </div>
                                
                                <div class="form-group">
                                    <select name="gender">
                                        <option>MALE</option>
                                        <option>FEMALE</option>
                                    </select>
                                </div>
                               
                                <div class="form-group">
                                     <label>numberPhone</label>
                                    <input class="input" type="tel" name="numberPhone" value="${orderInfo.numberPhone}">
                                    <mvc:errors path="numberPhone" cssClass="error"/>
                                </div>
                                
                                <div class="form-group">
                                    <label>Note</label>
                                    <input class="input" type="text" name="note" value="">
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="button">
                                            <button class="btn-danger btn-group customer-button">Next</button>
                                        </div>
                                    </div>
                                </div>

                            </mvc:form>
                        </div>

                    </div>

                </div>
            </div>
        </div>

        <!-- /Billing Details -->



        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
</html>
