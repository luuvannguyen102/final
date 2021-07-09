<%-- 
    Document   : arlet
    Created on : Apr 2, 2021, 3:39:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="include/css-page.jsp" />
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-lg-12">                   
                    <div class="div-body">              
                        <div class="div-title">
                            <h3 class="h-title">Order Success <i class="fas fa-check-circle i"></i></h3>
                        </div>

                        <div id="review-form">
                            <mvc:form class="review-form" action="${pageContext.request.contextPath}/rating" method="post">
                                <input type="hidden" value="${user.id}">
                                <label for="name">Your Name</label>
                                <input class="input" name="name" type="text" value="${user.fullName}">
                                <label for="email">Your Email</label>
                                <input class="input" name="email" type="email" value="${user.email}">
                                <label for="comment">Your comment</label>
                                <textarea class="input" name="comment" placeholder="Your Review"></textarea>
                                <div class="input-rating">
                                    <span>Your Rating: </span>
                                    <div class="stars">
                                        <input id="star5" name="rating" value="5" type="radio"><label for="star5"></label>
                                        <input id="star4" name="rating" value="4" type="radio"><label for="star4"></label>
                                        <input id="star3" name="rating" value="3" type="radio"><label for="star3"></label>
                                        <input id="star2" name="rating" value="2" type="radio"><label for="star2"></label>
                                        <input id="star1" name="rating" value="1" type="radio"><label for="star1"></label>
                                    </div>
                                </div>
                                <div class="btn-center">
                                    <button class="primary-btn ">Submit</button>
                                </div>
                                
                            </mvc:form>
                        </div>

                    </div>
                </div>                  
            </div>
        </div>
        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
</html>
