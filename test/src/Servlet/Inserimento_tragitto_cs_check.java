package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.*;
import javax.servlet.http.*;

import Entita.*;
import Utils.*;
import Store.*;

public class Inserimento_tragitto_cs_check extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		Contratto contratto = StoreContratto.readContratto(Integer.parseInt(req.getParameter("contratto")));
		ArrayList<Tessera> listaTessere = StoreTessera.getTesseraFromContratto(Integer.parseInt(req.getParameter("contratto")));
		String tempoPrelievo = req.getParameter("tempoPrelievo");
		String tempoConsegna = req.getParameter("tempoConsegna");
		//Se tutto va bene, comincio a definire la sessione
		HttpSession session = req.getSession();
		session.setAttribute("listaTessere", listaTessere);
		session.setAttribute("contratto", contratto);
		session.setAttribute("tempoPrelievo", tempoPrelievo);
		session.setAttribute("tempoConsegna", tempoConsegna);
		
		String sql = "(SELECT MCS.targa FROM macchina_cs AS MCS INNER JOIN disponibilita AS D ON MCS.id_modello = D.modello_macchina WHERE MCS.provincia = '" + req.getParameter("provincia_stazione").replace("'", "''") + "' AND MCS.citta = '" + req.getParameter("citta_stazione").replace("'", "''") + "' AND MCS.nome_stazione_cs = '" + req.getParameter("nome_stazione").replace("'", "''") + "' AND MCS.prenotabile = 'true' AND D.nome_abbonamento = '"+ contratto.getNome_abbonamento().replace("'", "''") + "') EXCEPT (SELECT targa_cs FROM tragitto_cs WHERE tempo_consegna_prenotazione > '" + tempoPrelievo +"' AND tempo_prelievo_prenotazione < '" + tempoConsegna +"')";
		ResultSet rs = Utils.Query.doQueryRS(sql);
		if(rs != null){
			try {
				ArrayList<String> listaTarghe = new ArrayList<String>();
				while(rs.next()){
					listaTarghe.add(rs.getString("targa"));
				}
				ArrayList<Macchina_CS> listaMacchine = StoreMacchina_CS.readMacchina_CS_List(listaTarghe);
				session.setAttribute("listaMacchine", listaMacchine);
			} catch(SQLException e){
				
			}
		}
		String page = "jsp/new_tragitto_cs.jsp";
		res.sendRedirect(page);
	}
}
