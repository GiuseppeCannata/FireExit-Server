package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Mappa;
import services.Mappa_service;

/**
 * Servlet implementation class CaricaMappa
 */
@WebServlet("/CaricaMappa")
public class CaricaMappa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Mappa_service ms;
	private Mappa mappa;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaMappa() {
        super();
        // TODO Auto-generated constructor stub
        
        ms = new Mappa_service();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		int piano = Integer.parseInt(request.getParameter("piano"));
	 
		mappa = ms.CostruzioneMappa(piano);
		
		request.getSession().setAttribute("mappa", mappa);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/MappaView.jsp");
	    dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
