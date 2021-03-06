<%@page import="Store.StoreUtente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "Servlet.Interrogazione" %>
<%@ page import = "Utils.HTMLManager" %>
<%@ page import="Entita.Utente" %>
<%@ page import="Entita.Utente.Type" %>
<% Utente user = (Utente) session.getAttribute("utente_loggato");
if (user == null) { //Controllo che sia aperta una connessione, altrimenti faccio il redirect a login.jsp
	response.sendRedirect("login.jsp");
} else {
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>Il tuo profilo:</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.17/jquery-ui.min.js" ></script>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.17/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="../img/favicon.png" />
</head>
<body>
	<%= HTMLManager.getHeader(session) %>
    <div id="content">
        <div class="wrapper">
        <form id="register">
            <ul>
               <fieldset>
                <legend>Informazioni Personali:</legend>
	                <li>
	                	<label for="name">Nome:</label>
	                	<input name="name" type="text" id="name" placeholder="Nome" readonly="readnly" Value="<%= user.getNome() %>" maxlength="30"/>
	                </li>
	                <li>
	                	<label for="surname">Cognome:</label>
	                	<input name="surname" type="text" id="surname" placeholder="cognome" readonly="readnly" Value="<%= user.getCognome() %>" maxlength="30"/>
	                </li>
	                <li>
	                	<label for="gender">Sesso:</label>
	                	<input name="gender" type="text" placeholder="Sesso" readonly="readonly" value="<%= user.getSesso().compareTo("M") == 0 ? "Maschio" : "Femmina" %>" />
				    </li>
	                <li>
	                	<label for="address">Indirizzo:</label>
	                	<input name="address" type="text" id="address" placeholder="Indirizzo" readonly="readnly" Value="<%= user.getIndirizzo() %>" maxlength="50"/>
	                </li>
	                <li>
		                <label for="citta">Citt&agrave;:</label>
		                <input name="citta" type="text" id="citta" placeholder="Città" readonly="readnly" Value="<%= user.getCitta() %>" maxlength="50"/>
		            </li>
		            <li>
		                <label for="provincia">Provincia:</label>
		                <input name="provincia" type="text" id="provincia" placeholder="Provincia" readonly="readnly" Value="<%= user.getProvincia() %>" maxlength="50"/>
		            </li>
	                <li>
	                	<label for="phone">Telefono:</label>
	                	<input name="phone" type="text" id="phone" placeholder="Telefono" readonly="readnly" Value="<%= user.getTelefono() %>" maxlength="15"/>
	                </li>
                </fieldset>
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
<%}%>