package Servlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Entita.*;
import Utils.*;
import Store.*;

public class Inserimento_macchina_cs extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Calendar scadenza_bollo = Utils.TimeString.dataStringToCalendar(req.getParameter("scadenza_bollo"));
		Calendar scadenza_assicurazione = Utils.TimeString.dataStringToCalendar(req.getParameter("scadenza_assicurazione"));
		Calendar scadenza_revisione = Utils.TimeString.dataStringToCalendar(req.getParameter("scadenza_revisione"));
		//Inserisco Macchina car sharing
		Macchina_CS mCS = new Macchina_CS(req.getParameter("car_plate"),Integer.parseInt(req.getParameter("model")),req.getParameter("city"),req.getParameter("province"), req.getParameter("name_cs"),Integer.parseInt(req.getParameter("purchase_year")),scadenza_bollo, scadenza_assicurazione, scadenza_revisione, Integer.parseInt(req.getParameter("tot_km")),Boolean.parseBoolean("TRUE"));		
		StoreMacchina_CS.insertMacchina_CS(mCS);

		//Se tutto va bene, comincio a definire la sessione
		HttpSession session = req.getSession();
		String page = "";

		//Prendo dal DB la lista delle macchine
		String sql = "SELECT targa FROM Macchina_CS";
		ResultSet rs = Utils.Query.doQueryRS(sql);
		if(rs != null){
			try {
				ArrayList<String> listamCS = new ArrayList<String>();
				while(rs.next()){
					listamCS.add(rs.getString("targa"));
				}
				ArrayList<Macchina_CS> listaCompleta = Store.StoreMacchina_CS.readMacchina_CS_List(listamCS);
				session.setAttribute("lista_macchine", listaCompleta);
			} catch(SQLException e){
				
			}
		}

		page = "jsp/lista_macchine_cs.jsp";
		session.setAttribute("descrizione", "Registrazione nuova macchina car sharing avvenuta con successo!");

		res.sendRedirect(page);
	}
}
