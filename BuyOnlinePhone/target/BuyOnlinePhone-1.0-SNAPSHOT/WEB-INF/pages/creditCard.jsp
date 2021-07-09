<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Credit</title>
        <jsp:include page="include/css-page.jsp" />
        <style>
            .error {
                color : red;
            }
        </style>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <div class="section"  style="background-color: #269abc">
            <!-- container -->
            <div class="container" >
                <!-- row -->
                <div class="row">
                    <div class="col-md-12" style="color: red">
                        ${errorCvcCode}
                    </div>
                </div>

                <mvc:form action="${pageContext.request.contextPath}/checkCreditCard" method="POST">
                    <!--    CreditCard info-->
                    <div class="row">
                        <div class="col-md-12" style="color: red">
                            ${errorCreditCard}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="billing-details customer-credit">
                                <div class="error">
                                    ${statusError}
                                </div>
                                <div class="section-title">
                                    <h3 class="title">Please enter credit card information</h3>
                                </div> 

                                <div class="form-group">
                                    <span class="error">${cardNumberError}</span>
                                    <input class="input" type="text" name="cardNumber" placeholder="Enter Card Number" >
                                </div>
                                <div class="form-group">
                                    <span class="error">${cvcCodeError}</span>
                                    <input class="input" type="text" name="cvcCode" placeholder="Enter CvcCode">
                                </div>                                                                            
                            </div>


                        </div>
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

        <!-- /Billing Details -->



        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
</html>





