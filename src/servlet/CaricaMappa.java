package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Mappa;
import model.Mappa_DB;
import services.Mappa_service;

/**
 * Servlet implementation class CaricaMappa
 */
@WebServlet("/CaricaMappa")
public class CaricaMappa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Mappa_service ms;
	private Mappa mappa;
	private Mappa_DB mdb;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaMappa() {
        super();
        // TODO Auto-generated constructor stub
        
        ms = new Mappa_service();
        mdb = new Mappa_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		
		 
		try {
			
			int piano = Integer.parseInt(request.getParameter("piano"));
		    int statoEmergenza;
		
			mappa = ms.CostruzioneMappa(piano);
			statoEmergenza = mdb.getStatoEmergenzaByPiano(piano);
			
			request.getSession().setAttribute("mappa", mappa);
			request.getSession().setAttribute("statoEmergenza", statoEmergenza);
			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/MappaView.jsp");
		    dispatcher.forward(request, response);	
	    
		}catch(Exception e) {
			System.out.println("INPUT ERRATO");
			response.sendRedirect(request.getContextPath() + "/ListMappe");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//VUOTO
	}
}
