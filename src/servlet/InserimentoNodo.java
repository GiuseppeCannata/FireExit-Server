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
	private ArrayList<Integer> piani;
	private Nodo nodo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoNodo() {
        super();
        // TODO Auto-generated constructor stub
        
        mdb = new Mappa_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		piani = mdb.getPiani();
		
		piani.add(134);
		piani.add(150);
		
		request.setAttribute("ListPiani", piani);
		
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoNodoView.jsp");
        dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String BeaconId = (String) request.getParameter("BeaconId");
        System.out.println("size"+BeaconId.length());
        int mappaId = Integer.parseInt(request.getParameter("mappaId"));
        int X = Integer.parseInt(request.getParameter("X"));
        int Y = Integer.parseInt(request.getParameter("Y"));
        boolean TipoIncendio = Boolean.parseBoolean(request.getParameter("TipoIncendio"));
        boolean TipoUscita = Boolean.parseBoolean(request.getParameter("TipoUscita"));
        
        boolean hasError = false;
        String errorString = null;
        
        nodo = new Nodo(0,BeaconId,X,Y,TipoUscita,TipoIncendio,mappaId);
        piani = mdb.getPiani();
		
		piani.add(134);
		piani.add(150);
        

        if (BeaconId.length() == 0) {
            hasError = true;
            errorString = "Alcuni campi sembrano essere vuoti!";
        } else {
           
	        Nodo_DB ndb = new Nodo_DB();
	        
	        if (ndb.inserimentoNodo(nodo)) 
				response.sendRedirect(request.getContextPath() + "/ListNodi");
			else {
				
				request.setAttribute("messaggio", "Sembra esserci stato un errore. La invitiamo a riprovare scusandoci per l incoveniete");
				  
			    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Messaggio.jsp");
		 
			    dispatcher.forward(request, response);
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
