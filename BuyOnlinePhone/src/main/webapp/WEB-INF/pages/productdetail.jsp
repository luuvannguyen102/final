<%-- 
    Document   : productdetail
    Created on : Mar 15, 2021, 10:55:56 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>
        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->

                <!-- Product main img -->
                <div class="col-md-5 col-md-push-2">
                    <div class="row">
                        <div id="product-main-img">                            
                            <div class="product-preview">
                                <img  src="<c:url value="/resources/img/${product.image}" />">
                            </div>
                            <c:forEach items="${color}" var="c">    
                                <c:if test="${product.image != c.image}" >
                                    <div class="product-preview">                                                                              
                                        <img  src="<c:url value="/resources/img/${c.image}"/>" >                                       
                                    </div>    
                                </c:if>

                            </c:forEach>
                        </div>
                    </div>
                </div>
                <!-- /Product main img -->
                <!-- Product thumb imgs -->
                <mvc:form action="${pageContext.request.contextPath}/addOrder" method="post">
                    <input id="productId" name="id" value="${product.id}" hidden />
                    <input id="numberGroup" value="${product.numberGroup}" hidden>

                    <div class="col-md-2  col-md-pull-5">
                        <div class="row">
                            <div id="product-imgs">
                                <div class="product-preview h-title">
                                    <img onclick="cc(${c.id})" src="<c:url value="/resources/img/${product.image}" />">
                                    <span>${product.color}</span>
                                </div>

                                <c:forEach items="${color}" var="c">   
                                    <c:if test="${product.image != c.image}" >
                                        <div class="product-preview h-title">                                                                              
                                            <img onclick="cc(${c.id})"  src="<c:url value="/resources/img/${c.image}"/>" >     
                                            <span>${c.color}</span>                                     
                                        </div> 
                                    </c:if>    
                                </c:forEach>

                            </div>
                        </div>
                    </div>   
                    <!-- /Product thumb imgs -->
                    <!-- Product details -->
                    <div class="col-md-5">
                        <div class="row">
                            <div class="product-details">                       
                                <h2 class="product-name">${product.name}-${product.memory}</h2> 
                                <label for="category.name">${product.category.name}</label>
                                <span class="product-available status">${product.status}</span>
                                <div>
                                    <div class="product-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                </div>
                                <div>                                                                                                                                                                                                 
                                    <fmt:setLocale value="vi_VN" scope="session"/>
                                    <h3 class="product-price">
                                        <fmt:formatNumber type="currency"  value="${product.salePrice}"/> </h3>
                                    <del class="product-old-price"><fmt:formatNumber type="currency"  value="${product.price}"/></del>   
                                    <input id="price" type="hidden" value="${product.price}">
                                </div>
                                <div class="row">
                                    <c:forEach items="${listProduct}" var="p">
                                        <a href="<c:url value="/productdetail/${p.id}"/>">
                                            <div class="input-change-select">                                           
<!--                                                <input class="input-select" type="radio" id="${p.memory}" name="memory" value="${p.memory}">-->
                                                <fmt:setLocale value="vi_VN" scope="session"/>
                                                <label for="${p.memory}">${p.memory}<br><fmt:formatNumber type="currency"  value="${p.salePrice}"/></label>
                                            </div> 
                                        </a>
                                    </c:forEach>
                                </div>
                                <div id="myModal" class="modal">

                                    <!-- Modal content -->
                                    <div class="modal-content">
                                        <span class="close">&times;</span>
                                        <p class="btnjs">quantity is not enough</p>
                                        <button id="ok" class="btn btn-warning btnjs" type="button">Ok</button>
                                    </div>
                                </div>


                                <div class="add-to-cart">
                                    <div class="qty-label">
                                        Quantity
                                        <div class="input-number">
                                            <input id="quantity" name="quantity" type="number" value="1">
                                            <span class="qty-up">+</span>
                                            <span class="qty-down">-</span>
                                        </div>
                                    </div>                                                                      
                                </div>
                                <div class="add-to-cart">
                                    <button id="submit" 
                                            class="add-to-cart-btn btn-primary"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                </div>
                                <ul class="product-links">
                                    <li>Share:</li>
                                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                    <li><a href="#"><i class="fa fa-envelope"></i></a></li>
                                </ul>

                            </div>
                        </div>
                    </mvc:form>
                </div>            
                <!-- /Product details -->
                <!-- Product tab -->
                <div class="col-md-12">
                    <div id="product-tab">
                        <!-- product tab nav -->
                        <ul class="tab-nav">
                            <li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>

                            <li><a  data-toggle="tab" href="#tab3">Reviews</a></li>
                        </ul>
                        <!-- /product tab nav -->

                        <!-- product tab content -->
                        <div class="tab-content">
                            <!-- tab1  -->
                            <div id="tab1" class="tab-pane fade in active">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p>${product.description}</p>
                                    </div>
                                </div>
                            </div>
                            <!-- /tab1  -->                     
                            <!--                                                         tab3  -->
                            <div id="tab3" class="tab-pane fade in">
                                <div class="row">
                                    <!--                                Rating -->
                                    <div class="col-md-12">
                                        <h3>Vote & Commemt ${product.name}-${product.memory}</h3>
                                    </div>

                                    <div class="col-md-6">
                                        <div id="rating">

                                            <div class="rating-avg">

                                                <span>${product.vote}</span>
                                                <div class="rating-stars">
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
                                            </div>
                                            <div class="rating-avg">
                                                <span>${product.count}</span>
                                                <span>Count Rate</span>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 h-title">
                                        <c:if test="${userName != null}">
                                            <h3>
                                                Have you used this product before?
                                            </h3>
                                            <button id="review" class="btn-danger primary-btn">Submit your review</button>
                                        </c:if>
                                    </div>                         

                                    <!--                 Review Form -->
                                    <div id="rating-review" class="col-md-12 rating-review">
                                        <div id="login" class="modal">
                                            <!-- Modal content -->
                                            <div class="login-content">
                                                <span class="close">&times;</span>
                                                <div>
                                                    <h3>Please login</h3>
                                                </div>
                                                <div>
                                                    <button onclick="location.href = '<c:url value="/login"/>'">Ok</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="review-form">
                                            <form class="review-form form-css">
                                                <div class="col-md-4">
                                                    <h3>How many stars do you rate this product?</h3>
                                                    <div class="input-rating">
                                                        <span>Your Rating: </span>
                                                        <div class="stars">
                                                            <input id="star5" name="rating" value="5" type="radio"><label for="star5"></label>
                                                            <input id="star4" name="rating" value="4" type="radio"><label for="star4"></label>
                                                            <input id="star3" name="rating" value="3" type="radio"><label for="star3"></label>
                                                            <input id="star2" name="rating" value="2" type="radio"><label for="star2"></label>
                                                            <input id="star1" name="rating" value="1" type="radio"><label for="star1"></label>

                                                        </div>
                                                        <p id="errorRating" style="color: red; display: none;">Please rate this product</p>
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <textarea id="comment" class="input" placeholder="Your Review"></textarea>
                                                    <p id="errorComment" style="color: red; display: none;">Please write a comment.(Minimum 3 characters)</p>
                                                </div>

                                                <div class="col-md-12 h-title">
                                                    <button id="vote" class="primary-btn" type="button">Submit</button>
                                                </div>

                                            </form>
                                        </div>
                                    </div>
                                    <!--                                /Review Form -->
                                    <!-- Reviews -->
                                    <div class="col-md-12">

                                        <div id="reviews">
                                            <ul class="reviews">
                                                <c:forEach items="${product.voteAndComments}" var="rating">
                                                    <li>
                                                        <div class="review-heading">
                                                            <h5 class="name">${rating.account.fullName}</h5>
                                                            <p class="date">${rating.createDate}</p>
                                                            <div class="review-rating">
                                                                <c:if test="${rating.vote > 0 && rating.vote < 1.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${rating.vote >= 1.5 && rating.vote < 2.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${rating.vote >= 2.5 && rating.vote < 3.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${rating.vote >= 3.5 && rating.vote < 4.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star-o"></i>
                                                                </c:if>
                                                                <c:if test="${rating.vote >= 4.5}" >
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                        <div class="review-body">
                                                            <p>${rating.content}</p>
                                                        </div>
                                                    </li> 
                                                </c:forEach>
                                            </ul>

                                        </div>
                                    </div>
                                    <!-- /Reviews -->

                                </div><!--
                                 /tab3  -->
                            </div>
                            <!-- /product tab content  -->
                        </div>
                    </div>
                    <!-- /product tab -->

                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /SECTION -->
            <jsp:include page="include/footer.jsp"/>
            <jsp:include page="include/js-page.jsp"/>
    </body>

    <script>
        function  cc (a) {
            
            $('#productId').attr('value', a);
        }
        var oldQuantity = $("#quantity").val();
        $('#memory').on('change', function () {
            var memory = $(this).val();
            var color = $('#color').val();
            var numberGroup = $('#numberGroup').val();
            $.ajax({
                type: "get",
                url: '${pageContext.request.contextPath}/getMemoryByAjax/' + memory + '/' + color + '/' + numberGroup,
                success: function (result) {
                    var price = parseFloat(JSON.parse(result));

                    $('#price').attr('value', parseFloat(price).toLocaleString('vi-VN', {
                        style: 'currency',
                        currency: 'VND'
                    }));
                }
            });
        });

        $('#colorId').on('change', function () {
            var colorId = $(this).val();
            var memoryId = $('#memoryId').val();
            var productId = $('#productId').val();
            $.ajax({
                type: "get",
                url: '${pageContext.request.contextPath}/get price by memory/' + memoryId + '/' + colorId + '/' + productId,
                success: function (result) {
                    var price = parseFloat(JSON.parse(result));
                    //                   $('#price').attr('value', parseInt(ticket.price).toLocaleString('vi-VN', {
                    //                                            style: 'currency',
                    //                                            currency: 'VND'
                    //                                        }));
                    //                   $('#price').attr('value',price);                      
                    $('#price').attr('value', parseInt(price).toLocaleString('vi-VN', {
                        style: 'currency',
                        currency: 'VND'
                    }));
                }
            });
        });
        //        parseInt(ticket.price).toLocaleString('vi-VN', {
        //                                         style: 'currency',
        //                                          currency: 'VND'
        //                                      }));

        $('#quantity').on('change', function () {
            var quantity = $(this).val();
            var productId = $('#productId').val();
            var modal = document.getElementById("myModal");
            $.ajax({
                type: "get",
                url: '${pageContext.request.contextPath}/checkQuantityByAjax/'
                        + productId + '/' + quantity,
                success: function (result) {
                    if (result.length - 2 > 0) {
                        modal.style.display = "block";

                    } else {
                        oldQuantity = quantity;
                    }
                }
            });
        });

        var modal = document.getElementById("myModal");
        var span = document.getElementsByClassName("close")[0];
        var ok = document.getElementById("ok");
        span.onclick = function () {
            document.getElementById('quantity').value = oldQuantity;
            modal.style.display = "none";
        };
        ok.onclick = function () {
            document.getElementById('quantity').value = oldQuantity;
            modal.style.display = "none";
        };

        var review = document.getElementById('review');
        var rating = document.getElementById('rating-review');
        var comment = document.getElementById('comment');
        review.onclick = function () {
            rating.style.display = "block";
        }
        $('#vote').on('click', function () {
            var checkbox = document.getElementsByName('rating');
            var rating = 0;
            var comment = $('#comment').val();
            var productId = $('#productId').val();
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked === true) {
                    rating = checkbox[i].value;
                }
            }
            if (rating == 0) {
                document.getElementById('errorRating').style.display = "block";
                setTimeout(function () {
                    document.getElementById('errorRating').style.display = "none";
                }, 3000);
            }
            if (comment.length < 3) {
                document.getElementById('errorComment').style.display = "block";
                setTimeout(function () {
                    document.getElementById('errorComment').style.display = "none";
                }, 3000);
            }
            debugger;
            ;
            $.ajax({
                type: "get",
                url: '${pageContext.request.contextPath}/rating/'
                        + comment + '/' + rating + '/' + productId,
                success: function (result) {
                    if (JSON.parse(result) === "error account") {
                        document.getElementById('login').style.display = "block";

                    } else if (JSON.parse(result) === "error rating") {
                        alert("Sorry you have already rated this product");
                    } else if (JSON.parse(result) === "susccess") {
                        alert("susccess");
                    } else if (JSON.parse(result) === "error product") {
                        alert("you have not purchased this product");
                    }
                }
            });

        });

        document.getElementsByClassName('close')[1].onclick = function () {
            document.getElementById('login').style.display = "none";
        }


    </script>
</html>
