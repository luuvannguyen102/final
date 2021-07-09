<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="include/css-page.jsp"/>



    <!-- store products -->
    <div id="change" class="row">
        <!-- product -->                 
        <c:forEach items="${products}" var="p">
            <div class="col-md-4 col-xs-6">
                <div class="product">
                    <div class="product-img">
                        <ul>
                            <li>
                                <a href="<c:url value="/productdetail/${p.id}"/>"><img class="imagetest" src="<c:url value="/resources/img/${p.image}" />" alt="photo"/></a>
                            </li>
                        </ul>                                           
                        <div class="product-label">                                                   
                            <span class="new">NEW</span>
                        </div>
                    </div>
                    <div class="product-body">
                        <p class="product-category">${p.category.name}</p>
                        <h3 class="product-name"><a href="<c:url value="/productdetail/${p.id}"/>">${p.name}-${p.memory}</a></h3>
                        <fmt:setLocale value="vi_VN" scope="session"/>
                        <h4 class="product-price"><fmt:formatNumber type="currency" value="${p.salePrice}"/><del class="product-old-price"><fmt:formatNumber type="currency" value="${p.price}"/></del></h4>                                                
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
                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                    </div>
                </div>

            </div>

        </c:forEach>

    </div>


<!-- /STORE -->
<jsp:include page="include/js-page.jsp"/>