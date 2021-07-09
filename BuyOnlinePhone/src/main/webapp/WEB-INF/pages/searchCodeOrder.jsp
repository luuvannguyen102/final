<%-- 
    Document   : searchCodeOrder
    Created on : Apr 4, 2021, 1:02:33 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="include/css-page.jsp" />
    </head>
    <body>

        <jsp:include page="include/header.jsp" />
        <jsp:include page="include/menu1.jsp" />
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">

                    <div class="col-md-12 order">
                        <div class="form-group">
                            <h3 for="code">Order Code</h3>
                            <input id="search" class="input input-search" type="text" name="code" placeholder="Enter code">
                            <button id="search-code" style="width: 30%" class="btn btn-warning">Search</button>
                        </div> 
                    </div>
                    <div>
                        <h3>${message}</h3>
                    </div>
                </div>

            </div></div>
        <div class="section">
            <div id="result" class="container" >

        </div>
        </div>
        
        <jsp:include page="include/footer.jsp"/>
        <jsp:include page="include/js-page.jsp"/>
    </body>
    <script>
        $('#search-code').on('click', function () {
            var a = $('#search').val();
            if (a.length != 10) {
                alert('code must be 10 characters');
            } else {
                $.ajax({
                    type: "get",
                    url: '${pageContext.request.contextPath}/searchOrder/'
                            + a,
                    success: function (result) {
                        if (JSON.parse(result) === "null") {
                            alert('No results found');
                           
                        } else if (JSON.parse(result) === "susccess") {
                            
                            $('#result').load('/BuyOnlinePhone/resultOrder');
                        }


                    }
                })
            }
        })
    </script>
</html>
