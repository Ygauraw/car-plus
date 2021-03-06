package Servlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import Entita.*;
import Utils.*;
import Store.*;


public class Inserimento_targa_e_modello extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		//Inserisco nuovo modello macchina  marca,modello,cilindrata,anno,alimentazione)
		Modello_Macchina mm = new Modello_Macchina(0, req.getParameter("marca"), req.getParameter("modello"), Integer.parseInt(req.getParameter("cilindrata")),Integer.parseInt(req.getParameter("anno")), req.getParameter("alimentazione"), Integer.parseInt(req.getParameter("emissioni_co2")), Integer.parseInt(req.getParameter("num_posti")));		
		StoreModello_Macchina.insertModello_Macchina(mm);
				
		//Se tutto va bene, comincio a definire la sessione
		HttpSession session = req.getSession();
		String page = "";

		//Prendo dal DB la lista delle macchine
		ArrayList<String> listamm = new ArrayList<String>();
		listamm.add(""+mm.getId());
		ArrayList<Modello_Macchina> listaCompleta = Store.StoreModello_Macchina.readModello_Macchina_List(listamm);
		session.setAttribute("modello_macchina", listaCompleta);
		
		session.setAttribute("targa",req.getParameter("targa")); //passo il costrutto parametri alla pagina
		page = "jsp/new_inserimento_macchina_cp.jsp?macchina_inserita=ok";	

		res.sendRedirect(page);
	}
}
