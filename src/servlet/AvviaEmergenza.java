package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Mappa_DB;
import services.Mappa_service;

/**
 * Servlet implementation class AvviaEmergenza
 */
@WebServlet("/AvviaEmergenza")
public class AvviaEmergenza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvviaEmergenza() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int piano = Integer.parseInt((String)request.getParameter("piano"));
		Mappa_service ms = new Mappa_service();
        Mappa_DB mdb = new Mappa_DB();
		mdb.updateStatoEmergenza(1,piano);
		ms.inviaAlert(/*piano*/);
		
		response.sendRedirect(request.getContextPath() + "/CaricaMappa?piano="+piano);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
