<%-- 
    Document   : clickSugerencias
    Created on : 10-mar-2015, 18:16:02
    Author     : Karen
--%>
<!doctype html>
</body>
</html>
<meta charset="utf-8">
<title>pagina inicio</title>
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
    <div class="container">
        <form class="contact-form" name="form1" method="post" action="mailto:kwiznia@gmail.com">
            <label for="nombre">Nombre: </label><input type="text" name="nombre" id="nombre">
            <label for="email">Email: </label><input type="text" name="nombre" id="email">
            <label>&iquest;Qu&eacute; te pareci&oacute; la p&aacute;gina?</label>
            <textarea id="opinion" name="opinion"></textarea>
            <input type="submit" name="action" value="Enviar formulario"/>
        </form>
    </div>
</div>
</body>
</html>
