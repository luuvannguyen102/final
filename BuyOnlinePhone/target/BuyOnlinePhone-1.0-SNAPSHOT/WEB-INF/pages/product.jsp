<%-- 
    Document   : product
    Created on : Mar 10, 2021, 5:10:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store</title>
        <jsp:include page="include/css-page.jsp"/>
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb-tree">
                            <li><a href="<c:url value="home"/>">Home</a></li>

                            <li class="active"><a href="<c:url value="product"/>">Product(Result ${quantity})</a></li>                                                 
                        </ul>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /BREADCRUMB -->
        <!-- SECTION -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div id="aside" class="col-md-3">
                        <!-- aside Widget -->
                        <div class="aside">
                            <h3 class="aside-title">Categories</h3>
                            <div class="checkbox-filter">
                                <c:forEach items="${category}" var="c">
                                    <div class="input-checkbox">
                                        <input onclick="loadProduct(${c.id})" type="radio" name="checkbox" value="${c.name}">
                                        <label for="category-${c.id}">
                                            <span></span>
                                            ${c.name}
                                            <small id="${c.id}">(${c.count})</small>
                                        </label>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                        <!-- /aside Widget -->

                        <!-- aside Widget -->
                        <div class="aside">
                            <h3 class="aside-title">Price</h3>
                            <div class="price-filter">

                                <div class="input-number price-min">
                                    <input id="price-min" type="number" value="0">
                                    <span class="qty-up">+</span>
                                    <span class="qty-down">-</span>
                                </div>
                                <span>-</span>
                                <div class="input-number price-max">
                                    <input id="price-max" type="number" value="0">
                                    <span class="qty-up">+</span>
                                    <span class="qty-down">-</span>
                                </div>
                            </div>
                            <div style="text-align: center; margin: 10px">
                                <button class="btn btn-danger  "  style="width: 50%"id="search">Search</button>
                            </div>
                        </div>
                        <!-- /aside Widget -->
                    </div>
                    <!-- /ASIDE -->
                    <!-- STORE -->
                    <div  class="col-md-9">

                        <!-- store products -->
                        <div id="store" class="row">
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
                                            <mvc:form action="${pageContext.request.contextPath}/addOrder" method="post">
                                                <input name="id" value="${p.id}" type="hidden">
                                                <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                            </mvc:form>
                                        </div>
                                    </div>

                                </div>

                            </c:forEach>

                        </div>

                    </div>
                    <!-- /STORE -->
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /SECTION -->
        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
    <script>
        var check = 'all';
        var id =0;
        function  loadProduct (a) {
            id = a;
        var checkbox = document.getElementsByName('checkbox');
        debugger;
        for (var i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked === true) {
        check = checkbox[i].value;
        }
        }
        $("#store").load('/BuyOnlinePhone/category/' + check);
        };
        $("#search").on('click', function () {
        var min = $("#price-min").val();
        var max = $("#price-max").val();
        $('#store').load("/BuyOnlinePhone/searchPrice/" + check + '/' + min +
                        '/' + max
                        );
//        $.ajax({
//        type: "get",
//                url: '${pageContext.request.contextPath}/count/'
//                + check + '/' + min +
//                '/' + max
//                success: function (result) {
//                if (JSON.parse(result) === "") {
//
//                } else {
//                    document.getElementById(id).innerHTML = JSON.parse(result);
//                
//                });
//                }
//        }
//        });
        });
//        $('#sort').on('change', function () {
//            var type = $(this).val();
//            $('#change').load("/BuyOnlinePhone/sort/" + type, function () {
//
//            });
//        })


    </script>
</html>
