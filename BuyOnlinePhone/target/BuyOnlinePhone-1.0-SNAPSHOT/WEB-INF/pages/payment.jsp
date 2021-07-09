<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Credit Card Info</title>
        <jsp:include page="include/css-page.jsp" />
        <style>
            .error {
                color : red;
            }
        </style>
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <div class="section"  style="background-color: #269abc">
            <!-- container -->
            <div class="container" > 
                 <!--modal balance error-->
                 <mvc:form action="${pageContext.request.contextPath}/creditCard">
                        <div id="myModal" class="modal">

                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <p class="btnjs">Balance is not enough !!!</p>                              
                                <button class="btn btn-warning btnjs">return CreditCard page</button>
                            </div>

                        </div>
                        </mvc:form>
                        <!--modal suscess-->
                        <mvc:form action="${pageContext.request.contextPath}/" method="get">
                        <div id="customer" class="modal">

                            <!-- Modal content -->
                            <div class="customer-content">
                                <span class="close">&times;</span>
                                <span><i class="fas fa-check-circle"></i></span>
                                <p class="btnjs">Order Susccess<br>please check email</p>                               
                                <button id="ok" class="btn btn-warning btnjs">return home page</button>
                            </div>

                        </div>
                        </mvc:form>
                <!--    CreditCard info--> 
                <div class="row">
                    <div class="col-md-12">
                        <div class="section-title h-title">
                                <h3 class="title">credit card information</h3>
                            </div> 
                    </div>
                </div>
                <div class="row customer-credit">
                    <div class="col-md-6 ">                                               
                        <div class="billing-details ">
                            <input id="id" type="hidden" value="${creditCard.id}">
                            
                            <div class="form-group font">
                                first and last name: 
                                                             
                            </div>
                            <div class="form-group font">
                                number card:
                                                                    
                            </div>
                            <div class="form-group font">
                                cvcCode: 
                                                               
                            </div>  
                            <div class="form-group font">
                               expDate:
                                
                            </div>  
                            <div class="form-group font">
                               payment amount: 
                                
                            </div>  
                        </div>
                    </div>  
                            <div class="col-md-6">
                                <div class="billing-details ">
                            <input id="id" type="hidden" value="${creditCard.id}">
                            
                            <div class="form-group font">
                                <span>${creditCard.name}</span>                                   
                            </div>
                            <div class="form-group font">
                                <span>${creditCard.cardNumber}</span>                                    
                            </div>
                            <div class="form-group font">
                                <span> ${creditCard.cvcCode}</span>                                 
                            </div>  
                            <div class="form-group font">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${creditCard.expDate}"/>
        
                            </div>  
                            <div class="form-group font">
                                <fmt:setLocale value="vi_VN" scope="session"/>
                                <span><fmt:formatNumber type="currency"  value="${sessionScope.orders.totalPrice - sessionScope.orders.totalPrice * sessionScope.orders.discount / 100}"/></span> 
                            </div>  
                        </div>
                            </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="button">
                            <button id="button-pay" class="btn-danger btn-group customer-button">Next</button>
                        </div>

                    </div>
                </div>


            </div>
        </div>

        <!-- /Billing Details -->



        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
    <script>
        var modal = document.getElementById("myModal");
        var span = document.getElementsByClassName("close")[0];
        var ok = document.getElementById("ok");
        var close = document.getElementsByClassName("close")[1];
        span.onclick = function () {
            modal.style.display = "none";
        };
        ok.onclick = function () {
            customer.style.display = "none";
        }
        close.onclick = function  () {
            customer.style.display = "none";
        }
        var customer = document.getElementById("customer");
        $('#button-pay').on('click', function () {
            var id = $('#id').val();
            debugger;
            $.ajax({
                type: 'get',
                url: '${pageContext.request.contextPath}/pay/' + id,
//                data: id,
                success: function (data) {
                    if (data.length - 2 > 0) {
                        customer.style.display = "block";
                    } else {
                        modal.style.display = "block";
                    }
                }
            })
        });
    </script>
</html>





