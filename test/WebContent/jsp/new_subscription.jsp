<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "Utils.HTMLManager" %>
<%	if (session.getAttribute("ADMIN") == null) {
	response.sendRedirect("login.jsp");
} else { %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>Nuovo Abbonamento | Car+</title>
<link href="../css/base.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" href="../img/favicon.png" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
</head>
<body>
	<%= HTMLManager.getHeader(session) %>
    <div id="content">
        <div class="wrapper">
        <form method="GET" action="../Inserimento_abbonamento" id="new_subscription">
            <ul>
                <li><h1>Nuovo Abbonamento</h1></li>
                <fieldset>
                	<legend>Informazioni Generali:</legend>
	                <li><input name="nome" type="text" id="nome" placeholder="Nome" maxlength="30"/></li>
	                <li><input name="descrizione" type="text" id="descrizione" placeholder="Descrizione"/></li>
	            </fieldset>
	            <fieldset>
	            	<legend>Tariffe:</legend>
	                <li><input name="tariffa_magg_100km" type="text" id="tariffa_magg_100km" placeholder="Tariffa per tratte > 100Km"/></li>
	                <li><input name="tariffa_min_100km" type="text" id="tariffa_min_100km" placeholder="Tariffa per tratte < 100Km"/></li>
	                <li><input name="tariffa_notturna" type="text" id="tariffa_notturna" placeholder="Tariffa notturna"/></li>
	                <li><input name="tariffa_diurna" type="text" id="tariffa_diurna" placeholder="Tariffa diurna"/></li>
	                <li><input name="costo_mensile" type="text" id="costo_mensile" placeholder="Costo mensile"/></li>
                </fieldset>
                <fieldset>
                	<legend>Disponibilit&agrave; Tessere: </legend>
	                <li><input name="num_min_tessere" type="text" id="num_min_tessere" placeholder="Numero minimo di tessere"/></li>
	                <li><input name="num_max_tessere" type="text" id="num_max_tessere" placeholder="Numero massimo di tessere"/></li>
				</fieldset>
                <li><button name="submit" type="submit" id="submit">Inserisci</button></li>
            </ul>
        </form>
        
        <div style="clear: both;"></div>
    	
        </div>
    </div>
    <div id="footer">
    	<div class="wrapper">
        </div>
    </div>
</body>
</html>
<% } %>