<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>pagina inicio</title>
<link href="styles/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="./optionServlet" method="POST"> 
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
            <li><a href="clickQuienesSomos.jsp" class="thispage1">Quienes somos</a></li>
            <li><a href="clickComoFunciona.jsp" class="thispage2">Como funciona</a></li>
            <li><a href="clickSugerencias.jsp" class="thispage3">Sugerencias</a></li>
            <li><a href="clickAyuda.jsp" class="thispage4">Ayuda</a></li>
        </ul>
    </nav>
    </header>
	<br></br>
	 <table border="0">  
            <p class="main-text">   A&ntilde;ade cuantos ingredientes vayas a utilizar: </p>
            <td><input type="text" name="Ingredientes" value="${Receta.Ingredientes}" list="datalist1" /></td>
            <datalist id="datalist1">
                 <option value="Aceite"><option value="AceiteOliva"> <option value="AceitunasNegras"><option value="Ajo"><option value="Albahaca">
                 <option value="AlbahacaFresca"><option value="Azucar"><option value="CarnePicada"><option value="Cebolla"><option value="CebollaMorada">
                 <option value="Croutons"><option value="DienteDeAjo"><option value="Espinaca"><option value="FileteSalmon"><option value="Guisantes">
                 <option value="Harina"><option value="Huevo"><option value="LaminaParaCanelones"><option value="Lechuga"><option value="Macarrones">
                 <option value="Mantequilla"><option value="MasaPizza"><option value="Miel"><option value="Mostaza"><option value="Oregano">
                 <option value="PanRallado"><option value="Patata"><option value="PechugaPollo"><option value="Pepino"><option value="Perejil"><option value="Pimienta">
                 <option value="PimientoRojo"><option value="QuesoFeta"><option value="QuesoMozzarella"><option value="QuesoParmesano"><option value="QuesoRicota">
                 <option value="Sal"><option value="SalsaQueso"><option value="Tomate"><option value="TomateTriturado"><option value="Zanahoria">
            </datalist>
         </table>
            <div class="lista-ing">
                <p>Los ingredientes introducidos hasta ahora son: ${message}</p>
                <c:remove var="message" scope="session" />
            </div>
            <div class="col-ayuda">
        		<input type="submit" name="action" value="Agregar ingrediente"/>
        		<input type="submit" name="action" value="Buscar por ingredientes" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar todas las recetas ordenadas por tiempo de preparacion</p>
                <input type="submit" name="action" value="Buscar por tiempo de preparacion" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar todas las recetas con un tiempo maximo de preparacion</p>
                 <input type="text" name="TiempoMax" value="${Receta.TiempoMax}" />
                 <p>${messageMax}</p>
                <c:remove var="messageMax" scope="session" />
                <input type="submit" name="action" value="Buscar por tiempo maximo de preparacion" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar todas las recetas con un tiempo minimo de preparacion</p>
                <input type="text" name="TiempoMin" value="${Receta.TiempoMin}" />
                <p>${messageMin}</p>
                <c:remove var="messageMin" scope="session" />
                <input type="submit" name="action" value="Buscar por tiempo minimo de preparacion" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar todas las recetas que estén entre dos tiempos</p>
                <label>Tiempo minimo:</label>
                <input type="text" name="TiempoMin" value="${Receta.TiempoMin}" />
                <p>${messageMinEnclosed}</p>
                <c:remove var="messageMin" scope="session" />
                <label>Tiempo maximo:</label>
                <input type="text" name="TiempoMax" value="${Receta.TiempoMax}" />
                <p>${messageMaxEnclosed}</p>
                <c:remove var="messageMin" scope="session" />
                <input type="submit" name="action" value="Buscar por tiempo acotado de preparacion" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar recetas ordenadas por el grado de dificultad</p>
                <input type="submit" name="action" value="Buscar recetas por dificultad" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar recetas de dificultad baja y media</p>
                <input type="submit" name="action" value="Buscar recetas de dificultad baja y media" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar recetas de dificultad media y alta</p>
                <input type="submit" name="action" value="Buscar recetas de dificultad media y alta" />
            </div>
            <div class="col-ayuda">
                <p class="main-text">Buscar recetas con un máximo de calorias</p>
                <input type="text" name="Calorias" value="${Receta.Calorias}" />
                <p>${messageNok}</p>
                <c:remove var="message" scope="session" />
                <input type="submit" name="action" value="Buscar por calorias maximas" />
            </div>
        	<div class="col-ayuda">
                <p class="main-text">Si quieres buscar todas las recetas que tenemos haz click aqu&iacute;:</p>
        		<input type="submit" name="action" value="Buscar todas las recetas" />
        	</div>
        </div>
    </div>
</form>
</body>
</html>
