package Servlet;
import java.util.Calendar;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import Entita.*;
import Utils.*;
import Store.*;

public class Inserimento_macchina_cs extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String descrizione;
		
		//gestisco le date
		Calendar scadenza_bollo = Utils.TimeString.dataStringToCalendar(req.getParameter("scadenza_bollo"));
		Calendar scadenza_assicurazione = Utils.TimeString.dataStringToCalendar(req.getParameter("scadenza_assicurazione"));
		Calendar scadenza_revisione = Utils.TimeString.dataStringToCalendar(req.getParameter("scadenza_revisione"));
		
		//Inserisco Macchina car sharing
		Macchina_CS mCS = new Macchina_CS(req.getParameter("car_plate"),Integer.parseInt(req.getParameter("model")),req.getParameter("citta_stazione"),req.getParameter("provincia_stazione"), req.getParameter("nome_stazione"),Integer.parseInt(req.getParameter("purchase_year")),scadenza_bollo, scadenza_assicurazione, scadenza_revisione, Integer.parseInt(req.getParameter("tot_km")),Boolean.parseBoolean("TRUE"));		
		
		if(StoreMacchina_CS.insertMacchina_CS(mCS))		
			descrizione = "Inserimento della macchina di car sharing con targa " + mCS.getTarga() + " avvenuto con successo!";
		else
			descrizione = "Inserimento della macchina di car sharing con targa " + mCS.getTarga() + " non avvenuta! (Errore SQL: " + Query.erroreSQL + ")  <a href=\"javascript:history.go(-1)\">Torna indietro</a>";
		
		req.getSession().setAttribute("descrizione", descrizione);
		res.sendRedirect("jsp/lista_macchine_cs.jsp");
	}
}
