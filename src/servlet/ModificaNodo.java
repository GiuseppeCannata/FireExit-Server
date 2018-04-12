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
 * Servlet implementation class ModificaNodo
 */
@WebServlet("/ModificaNodo")
public class ModificaNodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Nodo_DB ndb;
	private Mappa_DB mdb;
	private Nodo nodo;
	private ArrayList<Integer> piani;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaNodo() {
        super();
        // TODO Auto-generated constructor stub
        
        ndb = new Nodo_DB();
        mdb = new Mappa_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int Id = Integer.parseInt(request.getParameter("id"));
		
		nodo = ndb.FindNodoById(Id);
		piani = mdb.getPiani();
		
		piani.add(134);
		piani.add(150);
		
		request.setAttribute("nodo", nodo);
		request.setAttribute("ListPiani", piani);
		
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/ModificaNodoView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int Id = Integer.parseInt(request.getParameter("Id"));
		System.out.println(Id);
        String BeaconId = (String) request.getParameter("BeaconId");
        System.out.println("size"+BeaconId.length());
        int mappaId = Integer.parseInt(request.getParameter("mappaId"));
        int X = Integer.parseInt(request.getParameter("X"));
        int Y = Integer.parseInt(request.getParameter("Y"));
        boolean TipoIncendio = Boolean.parseBoolean(request.getParameter("TipoIncendio"));
        boolean TipoUscita = Boolean.parseBoolean(request.getParameter("TipoUscita"));
        
        boolean hasError = false;
        String errorString = null;
        
        nodo = new Nodo(Id,BeaconId,X,Y,TipoUscita,TipoIncendio,mappaId);;
        piani = mdb.getPiani();
		
		piani.add(134);
		piani.add(150);
        

        if (BeaconId.length() == 0) {
            hasError = true;
            errorString = "Alcuni campi sembrano essere vuoti!";
        } else {
             
	        try {
	        	
				if (ndb.updateNodo(nodo)) 
					response.sendRedirect(request.getContextPath() + "/ListNodi");
				else {
					
					//errore view
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				if(e.getErrorCode() == 30000) {
					hasError = true;
		            errorString = "BeaconId già presente nel DB. Il MacAdrs deve essere univoco per ogni beacon.";
				}
			}
        }
        
        if (hasError) {		
	       
		    request.setAttribute("errorString", errorString);
		    request.setAttribute("nodo", nodo);
		    request.setAttribute("ListPiani", piani);
		    
		    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ModificaNodoView.jsp");
	 
		    dispatcher.forward(request, response);		
	   }
	}
}
