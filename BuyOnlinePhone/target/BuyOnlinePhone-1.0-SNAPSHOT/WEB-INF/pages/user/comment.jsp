<%-- 
    Document   : comment
    Created on : Apr 5, 2021, 1:45:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="../include/header.jsp" />
        <jsp:include page="../include/menu1.jsp" />
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- Review Form -->
                    <div class="col-md-12">
                        <div id="review-form">
                            <mvc:form class="review-form" action="${pageContext.request.contextPath}/user/comment" method="POST">
                                <h1 class="centerTh">Please leave a comment</h1>
                                <input class="input" type="text" value="${email}">
                                <input class="input" type="email" value="${fullName}">
                                <textarea name="content" class="input" placeholder="Your Review"></textarea>
                                <div class="input-rating">
                                    <span>Your Rating: </span>
                                    <div class="stars">
                                        <input id="star5" name="vote" value="5" type="radio"><label for="star5"></label>
                                        <input id="star4" name="vote" value="4" type="radio"><label for="star4"></label>
                                        <input id="star3" name="vote" value="3" type="radio"><label for="star3"></label>
                                        <input id="star2" name="vote" value="2" type="radio"><label for="star2"></label>
                                        <input id="star1" name="vote" value="1" type="radio"><label for="star1"></label>
                                    </div>
                                </div>
                                <button type="submit" class="primary-btn">Submit</button>
                            </mvc:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Review Form -->
        <jsp:include page="../include/footer.jsp"/>
        <jsp:include page="../include/js-page.jsp"/>
    </body>
</html>
