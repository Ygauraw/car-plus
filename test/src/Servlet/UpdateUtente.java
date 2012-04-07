package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Entita.*;
import Entita.Utente.Type;
import Utils.*;
import Store.*;

public class UpdateUtente extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String email = req.getParameter("mail");
		Utente ut = new Utente(req.getParameter("name"), req.getParameter("surname"), req.getParameter("radio"), req.getParameter("address"), req.getParameter("citta"), req.getParameter("provincia"), MD5.encrypt(req.getParameter("password")), email, req.getParameter("phone"), false, false);

		// Aggiornamento account (aggiornando, o meno, la password)
		if(req.getParameter("password").equals(""))	{	ut = StoreUtente.UpdateUtenteWithoutPassword(ut); }
		else										{	ut = StoreUtente.UpdateUtente(ut); }

		HttpSession session = req.getSession();
		
		if (StoreCliente_business.readCliente_business(email) != null)	{ut.setUserType(Type.BUSINESS);	}
		else	if(StoreCliente.readCliente(email) != null)				{ut.setUserType(Type.CLIENTE);	}
		else 															{ut.setUserType(Type.USER);		}
		
		session.setAttribute("utente_loggato", ut);
		
		String page = "jsp/login.jsp"; //mi mander� alla home dell'utente o dell'amministratore in base alla sessione	
		res.sendRedirect(page);
	}
}
