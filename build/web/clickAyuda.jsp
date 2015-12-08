<%-- 
    Document   : clickAyuda
    Created on : 10-mar-2015, 18:16:15
    Author     : Karen
--%>


<!doctype html>
<html>
<head>
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
        <p class="main-text">&iquest;Tienes problemas para entender c&oacute;mo funciona? No te preocupes, te vamos a dar los tips que necesitas para solventar todos tus problemas!</p>
        <ul class="ayuda-list">
            <li> En el desplegable tienes todos los ingredientes que forman nuestra base de datos</li>
            <li> Para seleccionar un ingrediente tienes que apretar en la flecha del desplegable o escribirlo a mano</li>
            <li> Cuidado! Si el ingrediente no est&aacute; escrito igual que en la base de datos, es posible que la b&uacute;squeda no sea la mejor</li>
            <li> Agrega los ingredientes apretando en el s&iacute;mbolo de agregar ingrediente que aparece justo debajo</li>
            <li> Cuando hayas seleccionado todos los ingredientes dale a "buscar por ingredientes"</li>
            <li> Chan! Ah&iacute; tienes todos las recetas que puedes hacer con esos ingredientes</li>  
        </ul>
        <ul class="ayuda-list">
            <li> IMPORTANTE!:</li>
            <li> Las recetas est&aacute;n ordenadas de tal forma que primero aparecen las que tienen unicamente esos ingredientes</li>
            <li> Luego las que tienen esos ingredientes y uno menos, y dos menos, y tres menos...</li>
            <li> Y finalmente las que tienen esos ingredientes y uno m&aacute;s, y dos m&aacute;s, y tres m&aacute;s...</li>
        </ul>
        <p class="main-text">Si sigues teniendo dudas, no dudes en contactar con nosotros a trav&eacute;s de las redes sociales, gracias!</p>
    </div>
</div>
</body>
</html>