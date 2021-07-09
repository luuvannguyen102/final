<%-- 
    Document   : test-json
    Created on : Jul 3, 2021, 5:47:31 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div id="main">

        </div>
        <button id="hang">click here</button>
    </body>
    <jsp:include page="include/js-page.jsp"/>
    <script>
        $('#hang').on('click', function () {
             debugger;
            $.ajax({
                
                type: 'get',
                url: "${pageContext.request.contextPath}/test-json",
                success: function (result) {
                   
                    var a = JSON.parse(result);
                    var c = a.JSON.values();
                    alert(c);
                   var b = parseInt(a);
                     alert(typeof(b));
//                    document.getElementById('main').innerHTML = a.name;
                }
            })
        })

        function printValues(obj) {
            var a = '';
            for (var k in obj) {

                if (obj[k] instanceof Object) {
                    printValues(obj[k]);
                } else {
                    a = a + obj[k];

                }

                ;
            }
            alert(a);
        }
        ;

// In ra tất cả các giá trị

    </script>
</html>
