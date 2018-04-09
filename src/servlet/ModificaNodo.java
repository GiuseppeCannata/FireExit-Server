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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaNodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Nodo_DB ndb = new Nodo_DB();
		Mappa_DB mdb = new Mappa_DB();
		Nodo nodo;
		ArrayList<Integer> piani;
		
		int a = Integer.parseInt(request.getParameter("id"));
		
		nodo = ndb.FindNodoById(a);
		piani = mdb.getPiani();
		
		piani.add(134);
		piani.add(150);
		System.out.println(piani.size());
		System.out.println(piani.get(0));
		System.out.println(piani.get(1));
		System.out.println(piani.get(2));
		
		
		request.setAttribute("nodo", nodo);
		request.setAttribute("ListPiani", piani);
		
		 
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/ModificaNodoView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Nodo nodo;
		int Id = Integer.parseInt(request.getParameter("Id"));
        String BeaconId = (String) request.getParameter("BeaconId");
        String mappaId = request.getParameter("mappaId");
        String X = request.getParameter("X");
        String Y = request.getParameter("Y");
        String TipoIncendio = request.getParameter("TipoIncendio");
        String TipoUscita = request.getParameter("TipoUscita");
        
        boolean hasError = false;
        String errorString = null;
        
       // Nodo nodo = new Nodo(Id,BeaconId,X,Y,TipoUscita,TipoIncendio,mappaId);

        if (BeaconId.length() == 0) {
            hasError = true;
            errorString = "Alcuni campi sembrano essere vuoti!";
        } else {
           
	        Nodo_DB ndb = new Nodo_DB();
	        nodo = new Nodo(Id,BeaconId,Integer.parseInt(X),Integer.parseInt(Y),
	        		Boolean.parseBoolean(TipoUscita),Boolean.parseBoolean(TipoIncendio),Integer.parseInt(mappaId));
	        
	        try {
	        	
				if (ndb.updateNodo(nodo)) 
					response.sendRedirect(request.getContextPath() + "/ListNodi");
				else {
					
					//errore view
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
	            errorString = e.getMessage();
			}
        }
        
        if (hasError) {		
	        nodo = new Nodo(Id,BeaconId,Integer.parseInt(X),Integer.parseInt(Y),
	        		Boolean.parseBoolean(TipoUscita),Boolean.parseBoolean(TipoIncendio),Integer.parseInt(mappaId));
	        
	    
	    request.setAttribute("errorString", errorString);
	    request.setAttribute("nodo", nodo);
	    
	    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ModificaNodoView.jsp");
 
	    dispatcher.forward(request, response);
				
			
	   }
	}

}
