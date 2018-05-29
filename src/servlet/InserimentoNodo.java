package servlet;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Pattern;

import entity.Nodo;
import model.Mappa_DB;
import model.Nodo_DB;

/**
 * Servlet implementation class InseriemntoNodo
 */
@WebServlet("/InserimentoNodo")
public class InserimentoNodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Mappa_DB mdb;
	private Nodo nodo;
	private ArrayList<Integer> pianiList;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoNodo() {
        super();
        // TODO Auto-generated constructor stub
        
        mdb = new Mappa_DB();
        pianiList = mdb.getPiani();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int piano = Integer.parseInt(request.getParameter("piano"));
			request.setAttribute("piano", piano);
			
	        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoNodoView.jsp");
	        dispatcher.forward(request, response);	
        
		} catch(Exception e) {
			System.out.println("INPUT ERRATO");
			response.sendRedirect(request.getContextPath() + "/ListMappe");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String BeaconId = (String) request.getParameter("BeaconId");
        int mappaId = Integer.parseInt(request.getParameter("mappaId"));
        int X = Integer.parseInt(request.getParameter("X"));
        int Y = Integer.parseInt(request.getParameter("Y"));
        boolean TipoIncendio = false;
        boolean TipoUscita = Boolean.parseBoolean(request.getParameter("TipoUscita"));
        
        boolean hasError = false;
        String errorString = null;
        
        nodo = new Nodo(0,BeaconId,X,Y,TipoUscita,TipoIncendio,mappaId);

        //1^ controllo
        if(!Pattern.matches("^([A-Z0-9]{2}[:]){5}([A-Z0-9]{2})$", BeaconId)) {
        	hasError = true;
            errorString = "BeaconId sembra non essere corretto";
        }else{
           
	        Nodo_DB ndb = new Nodo_DB();
	        
	        try {
	        	
				if(ndb.inserimentoNodo(nodo))
				   response.sendRedirect(request.getContextPath() + "/CaricaMappa?piano="+mappaId);
				
			} catch (SQLException e) {
				e.printStackTrace();
				//3000 code di Sql Exception per campi UNIQUE
				if(e.getErrorCode() == 30000) {
					hasError = true;
		            errorString = "BeaconId già presente nel DB. Il MacAdrs deve essere univoco per ogni beacon.";
				}
			} 
        }
        
        if (hasError) {		
	       
		    request.setAttribute("errorString", errorString);
		    request.setAttribute("nodo", nodo);
		    request.setAttribute("piano", mappaId);
		    
		    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoNodoView.jsp");
		    dispatcher.forward(request, response);		
	   }
	}
}
