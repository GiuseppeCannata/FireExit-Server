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
		
		int piano = Integer.parseInt(request.getParameter("piano"));
		request.setAttribute("piano", piano);
		
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoNodoView.jsp");
        dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String BeaconId = (String) request.getParameter("BeaconId");
        int mappaId = Integer.parseInt(request.getParameter("mappaId"));
        int X = Integer.parseInt(request.getParameter("X"));
        int Y = Integer.parseInt(request.getParameter("Y"));
        boolean TipoIncendio = Boolean.parseBoolean(request.getParameter("TipoIncendio"));
        boolean TipoUscita = Boolean.parseBoolean(request.getParameter("TipoUscita"));
        
        boolean hasError = false;
        String errorString = null;
        
        nodo = new Nodo(0,BeaconId,X,Y,TipoUscita,TipoIncendio,mappaId);

        //1^ controllo
        if (BeaconId.length() == 0) {
            hasError = true;
            errorString = "Alcuni campi sembrano essere vuoti!";
        } else {
           
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
