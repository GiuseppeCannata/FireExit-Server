package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Nodo;
import model.Mappa_DB;
import model.Nodo_DB;
import utils.Parametri;

/**
 * Servlet implementation class GestisciEmergenza
 */
@WebServlet("/TerminaEmergenza")
public class TerminaEmergenza extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Nodo_DB ndb; 
	private Mappa_DB mdb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TerminaEmergenza() {
        super();
        // TODO Auto-generated constructor stub
        
        ndb = new Nodo_DB();
        mdb = new Mappa_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		   int piano = Integer.parseInt((String)request.getParameter("piano"));
		   
		   ArrayList<Nodo> nodiList = new ArrayList<Nodo>();
		   ndb.findNodiByPiano(nodiList, piano);
		   
		   for(Nodo nodo: nodiList)
			   if(nodo.isTipoIncendio() && nodo.isTipoUscita())
				   ndb.setTipo(Parametri.TIPO_USCITA_NO_INCENDIATO, nodo.getBeaconId());
			   else if(nodo.isTipoIncendio())
				   ndb.setTipo(Parametri.TIPO_BASE_NO_INCENDIATO, nodo.getBeaconId());
		 
		   if(mdb.updateStatoEmergenza(0, piano)) 
		      response.sendRedirect(request.getContextPath() + "/CaricaMappa?piano="+piano);
		    
		} catch(Exception e) {
			System.out.println("INPUT ERRATO");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
