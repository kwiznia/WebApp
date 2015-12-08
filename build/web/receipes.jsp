<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="styles/main.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="wrapper">
        <header id="top">
            <div class="container">
            <img class="logo" src="images/fridge.png" alt="Nevera" />
                <h1>EasyCook</h1>
                <div class="social-networks">
                    <p>Contacta con nosotros</p>
                    <a href="https://plus.google.com/"> <img src="images/google.png" width="20" height="20" alt=""/></a>
                    <a href="https://twitter.com/"> <img src="images/twitter.png" width="20" height="20" alt=""/></a> 
                    <a href="https://www.facebook.com/"> <img src="images/facebook.png" width="20" height="20" alt=""/></a>
                </div>
                <nav id="mainnav">
                    <ul>
                        <li><a href="option.jsp">Inicio</a></li>
                        <li><a href="clickQuienesSomos.jsp" class="thispage">Quienes somos</a></li>
                        <li><a href="clickComoFunciona.jsp" class="thispage">Como funciona</a></li>
                        <li><a href="clickSugerencias.jsp" class="thispage">Sugerencias</a></li>
                        <li><a href="clickAyuda.jsp" class="thispage">Ayuda</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <form action="./BuscarRecetaServlet" method="POST">     
        <table border="1">
            <tr>
                <th><FONT FACE="Times New Roman" SIZE=3> NombreReceta </th>
                <th><FONT FACE= "Times New Roman" SIZE=3> CantidadIngredientes </th>
                <th><FONT FACE= "Times New Roman" SIZE=3> Ingredientes </th>
                <th><FONT FACE= "Times New Roman" SIZE=3> ModoPreparacion </th>
                <th><FONT FACE= "Times New Roman" SIZE=3> Calorias </th>
                <th><FONT FACE= "Times New Roman" SIZE=3> Tiempo </th>
                <th><FONT FACE= "Times New Roman" SIZE=3> Dificultad </th>
            </tr>
      <c:forEach items="${AllReceipes}" var="receipt">        
        <tr>
            <td><c:out value="${receipt.nombreReceta}"/></td>
            <td><c:out value="${receipt.cantidadIngredientes}"/></td>
            <td><c:out value="${receipt.ingredientes}"/></td>
            <td><c:out value="${receipt.modoPreparacion}"/></td>
            <td><c:out value="${receipt.calorias}"/></td>
            <td><c:out value="${receipt.tiempo}"/></td>
            <td><c:out value="${receipt.dificultad}"/></td>
        </tr>
    </c:forEach>
</table>
        </form>
    </body>
</html>