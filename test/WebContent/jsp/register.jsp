<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "Servlet.Interrogazione" %>
<%@ page import = "Utils.HTMLManager" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crea un nuovo account | Car+</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.17/jquery-ui.min.js" ></script>
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.17/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="../css/base.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="../img/favicon.png" />
    <script>
        $(document).ready(function() {
            $("#radio").buttonset();
			
			$("button#submit").click(function(e) {
				$("div#error_modal").slideUp('slow').remove();
                var error = false;
				var missing = '';
				if ($("input#mail").val() == "") {
					error = error || true;
					missing += 'mail, ';
				}
				
				if ($("input#password").val() == "") {
					error = error || true;
					missing += 'password, ';
				}
				
				if ($("input#retype-password").val() == "") {
					error = error || true;
					missing += 'ripeti password, ';
				}
				
				if ($("input#name").val() == "") {
					error = error || true;
					missing += 'nome, ';
				}
				
				if ($("input#surname").val() == "") {
					error = error || true;
					missing += 'cognome, ';
				}
				
				if ($("input#address").val() == "") {
					error = error || true;
					missing += 'indirizzo, ';
				}
				
				if ($("input#phone").val() == "") {
					error = error || true;
					missing += 'telefono, ';
				}
				
				if (! (($("input#radio1").attr("checked") != "undefined") && ($("input#radio1").attr("checked") == "checked")) ||
					 ($("input#radio2").attr("checked") != "undefined") && ($("input#radio2").attr("checked") == "checked")) {
					error = error || true;
					missing += 'sesso, ';
				}
				if (error) {
					var modal_box =  '<div class="ui-widget" id="error_modal" ><div class="ui-state-error ui-corner-all"style="padding:30px;"> <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> <strong>Attenzione:</strong>I campi '+ missing + ' non sono stati correttamente impostati! Riempi tutti i campi per completare la registrazione</p></div></div>';
					
					$("div#content > div.wrapper").prepend(modal_box);
					$(document).scrollTop();
					
					 return false;
				}
				
				if ($("input#password").val() != $("input#retype-password").val()) {
					var modal_box =  '<div class="ui-widget" id="error_modal" ><div class="ui-state-error ui-corner-all"style="padding:30px;"> <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> <strong>Attenzione:</strong>Le password digitate non coincidono. Verificale per completare la registrazione!</p></div></div>';
					$("div#content > div.wrapper").prepend(modal_box);
					$(document).scrollTop();
					return false;
				}
				
				var reg_exp_mail = new RegExp('^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+[\.]([a-z0-9-]+)*([a-z]{2,3})$');
				var email = $("input#mail").val();
				if ( !reg_exp_mail.test(email) ) {
					var modal_box =  '<div class="ui-widget" id="error_modal" ><div class="ui-state-error ui-corner-all"style="padding:30px;"> <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> <strong>Attenzione:</strong>La mail inserita non Ã¨ una mail valida. Controllala per completare registrazione</p></div></div>';
					$("div#content > div.wrapper").prepend(modal_box);
					$(document).scrollTop();
					return false;
				}
				return true;
            });
			
			$("div#content input").click(function(e) {
                $("div#error_modal").slideUp('slow', function() {$("div#error_modal").remove();});
				
            });
        });
    </script>
</head>

<body>
	<%= HTMLManager.getHeader(session) %>
    <div id="content">
        <div class="wrapper">
       
        <form method="GET" action="../RegistrazioneUtente" id="register">
            <ul>
                <li><h1>Registrati ed entra nel mondo Car+!</h1></li>
                <fieldset>
                	<legend>Informazioni Account:</legend>
	                <li><input name="mail" type="text" id="mail" placeholder="Mail" maxlength="320"/></li>
	                <li><input name="password" type="password" id="password" placeholder="Password"/></li>
	                <li><input name="retype-password" type="password" id="retype-password" placeholder="Ripeti Password"/></li>
                </fieldset>
                <fieldset>
                <legend>Informazioni Personali:</legend>
	                <li><input name="name" type="text" id="name" placeholder="Nome" maxlength="30"/></li>
	                <li><input name="surname" type="text" id="surname" placeholder="Cognome" maxlength="30"/></li>
	                <li>
	                	<div id="radio">
	                        <input type="radio" id="radio1" name="radio" value="M"/><label for="radio1">Maschio</label>
	                        <input type="radio" id="radio2" name="radio" value="F"/><label for="radio2">Femmina</label>
	                    </div>
					</li>
	                <li><input name="address" type="text" id="address" placeholder="Indirizzo" maxlength="50"/></li>
	                <li><input name="provincia" type="text" id="provincia" placeholder="Provincia" maxlength="50"/></li>
	                <li><input name="citta" type="text" id="citta" placeholder="Citta" maxlength="50"/></li>
	                <!-- QUA VA GENERATO DINAMICAMENTE UNA DROP-DOWN LIST PER LE LOCALITA -->
	                <li><input name="phone" type="text" id="phone" placeholder="Telefono" maxlength="15"/></li>
                </fieldset>
                <li><button name="submit" type="submit" id="submit">Completa la Registrazione</button></li>
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
