package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import Entita.*;
import Utils.*;
import Store.*;

public class Inserimento_tragitto_cp extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		//Inserisco Tragitto_CP
		Tragitto_CP tcp = new Tragitto_CP(0, Boolean.parseBoolean(req.getParameter("smokers")),Integer.parseInt(req.getParameter("numero_posti")),req.getParameter("commento"),req.getParameter("fromprovince"),req.getParameter("fromcity"),req.getParameter("toprovince"),req.getParameter("tocity"),req.getParameter("email_utente"),req.getParameter("carplate"),Utils.TimeString.parseSQLTimestampToCalendar(req.getParameter("tempo_partenza")+":00"),Utils.TimeString.parseSQLTimestampToCalendar(req.getParameter("tempo_arrivo")+":00"));
		tcp = StoreTragitto_CP.insertTragitto_CP(tcp);
		
		//Se tutto va bene, comincio a definire la sessione
		HttpSession session = req.getSession();
		
		//Prendo dal DB la lista delle stazioni
		String sql = "SELECT id FROM tragitto_cp WHERE email_utente = '" + req.getParameter("email_utente") + "'";
		
		ResultSet rs = Utils.Query.doQueryRS(sql);
		if(rs != null){
			try {
				ArrayList<Integer> listatCP = new ArrayList<Integer>();
				while(rs.next()){
					listatCP.add(rs.getInt("id"));
				}
				ArrayList<Tragitto_CP> listaCompleta = Store.StoreTragitto_CP.readTragitto_CP_List(listatCP);
				session.setAttribute("lista_tragitti", listaCompleta);
			} catch(SQLException e){
				
			}
		}
		
			
		String page = "jsp/new_tragitto_cp_landing.jsp";
		res.sendRedirect(page);
	}
}