<%-- 
    Document   : home2
    Created on : Feb 4, 2021, 8:04:47 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Favicon-->
        <link rel="shortcut icon" href="<c:url value="/resources/img/fav.png"/>">
        <!-- Author Meta -->
        <meta name="author" content="CodePixar">
        <!-- Meta Description -->
        <meta name="description" content="">
        <!-- Meta Keyword -->
        <meta name="keywords" content="">
        <!-- meta character set -->
        <meta charset="UTF-8">
        <title>Electro.</title>
        <jsp:include page="include/css-page.jsp" />

    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title"> New Products</h3>                        
                        </div>
                    </div>
                    <!-- /section title -->
                    <!-- Products tab & slick -->
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">
                                <!-- tab -->
                                <div id="tab1" class="tab-pane active">
                                    <div class="products-slick" data-nav="#slick-nav-1">
                                        <!-- product -->
                                        <c:forEach items="${newproduct}" var="product">

                                            <div class="product">
                                                <div class="product-img">
                                                    <a href="<c:url value="productdetail/${product.id}"/>"> <img class="imagetest" src="<c:url value="/resources/img/${product.image}"/>" alt=""></a>                                                       
                                                    <div class="product-label">                                                         
                                                        <span class="new">NEW</span>
                                                    </div>
                                                </div>
                                                <div class="product-body">
                                                    <p class="product-category">${product.category.name}</p>
                                                    <h3 class="product-name"><a href="<c:url value="productdetail/${product.id}"/>">${product.name} - ${product.memory}</a></h3>
                                                    <fmt:setLocale value="vi_VN" scope="session"/>
                                                    <h4 class="product-price"><fmt:formatNumber type="currency"  value="${product.salePrice}"/><del class="product-old-price"><fmt:formatNumber type="currency" value="${product.price}"/></del></h4>                                                        
                                                    <div class="product-rating">
                                                        <c:if test="${product.vote > 0 && product.vote < 1.5}" >
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </c:if>
                                                        <c:if test="${product.vote >= 1.5 && product.vote < 2.5}" >
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </c:if>
                                                        <c:if test="${product.vote >= 2.5 && product.vote < 3.5}" >
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </c:if>
                                                        <c:if test="${product.vote >= 3.5 && product.vote < 4.5}" >
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                        </c:if>
                                                        <c:if test="${product.vote >= 4.5}" >
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </c:if>
                                                    </div>
                                                    <div class="product-btns">
                                                        <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                        <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                        <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                    </div>
                                                </div>
                                                <div class="add-to-cart">
                                                    <mvc:form action="${pageContext.request.contextPath}/addOrder" method="post">
                                                        <input type="hidden" name="id" value="${product.id}">
                                                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                    </mvc:form>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <!-- /product -->


                                    </div>
                                    <div id="slick-nav-1" class="products-slick-nav"></div>
                                </div>
                                <!-- /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->



        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12">
                        <div class="section-title">
                            <h3 class="title">Top selling</h3>                            
                        </div>
                    </div>
                    <!-- /section title -->

                    <!-- Products tab & slick -->                   
                    <div class="col-md-12"> 
                        <div class="row">
                            <div class="products-tabs">
                                <!--                                 tab -->
                                <div id="tab2" class="tab-pane fade in active">
                                    <div class="products-slick" data-nav="#slick-nav-2">
                                        <c:if test="${topselling != null && fn:length(topselling)>0}">

                                            <c:forEach items="${topselling}" var="p">
                                                <!--                                         product -->

                                                <div class="product">
                                                    <mvc:form action="addOrder" method="post">
                                                        <input type="hidden" name="id" value="${p.id}">
                                                        <div class="product-img">                                                       
                                                            <img class="imagetest" src="<c:url value="/resources/img/${p.image}"/>" alt="">
                                                        </div>
                                                        <div class="product-body">
                                                            <p class="product-category">${p.category.name}</p>
                                                            <h3 class="product-name"><a href="<c:url value="productDetail/${p.id}"/>">${p.name}-${p.memory}</a></h3>
                                                            <fmt:setLocale value="vi_VN" scope="session"/>
                                                            <h4 class="product-price"><fmt:formatNumber type="currency"  value="${p.salePrice}"/> <del class="product-old-price"></del></h4>
                                                            <div class="product-rating">
                                                                <c:if test="${p.vote > 0 && p.vote < 1.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${p.vote >= 1.5 && p.vote < 2.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${p.vote >= 2.5 && p.vote < 3.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${p.vote >= 3.5 && p.vote < 4.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${p.vote >= 4.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                </c:if>
                                                            </div>
                                                            <div class="product-btns">
                                                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                                                <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                                                                <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                            </div>
                                                        </div>
                                                        <div class="add-to-cart">
                                                            <button  class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                                        </div>
                                                    </mvc:form>
                                                </div>

                                                <!--                                         /product -->
                                            </c:forEach>
                                        </c:if>

                                    </div>
                                    <div id="slick-nav-2" class="products-slick-nav"></div>
                                </div>
                                <!--                                 /tab -->
                            </div>
                        </div>
                    </div>
                    <!-- /Products tab & slick -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->




        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
</html>
