package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Arco;
import entity.Mappa;
import entity.Nodo;
import model.Arco_DB;
import model.Mappa_DB;
import model.Nodo_DB;
import services.Mappa_service;

/**
 * Servlet implementation class EliminaMappa
 */
@WebServlet("/EliminaMappa")
public class EliminaMappa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Mappa_service ms;
	private Mappa mappa;
	private Mappa_DB mdb;
	private Arco_DB adb;
	private Nodo_DB ndb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaMappa() {
        super();
        // TODO Auto-generated constructor stub
        
        ms = new Mappa_service();
        mdb = new Mappa_DB();
        adb = new Arco_DB();
        ndb = new Nodo_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
	        int piano = Integer.parseInt((String)request.getParameter("piano"));
	        
	        mappa = ms.CostruzioneMappa(piano);
	        
	       //eleminazione archi
	        if(mappa.getArchi() != null)
		        for(Arco arco: mappa.getArchi()) 
			    	adb.deleteArco(arco);
	        
	      //eleminazione nodi
	        if(mappa.getNodi() != null)
	        	for(Nodo nodo: mappa.getNodi())
	        		ndb.delete(nodo.getId());
	        
			//eliminazione info mappa
			if(mdb.delete(piano))
				response.sendRedirect(request.getContextPath() + "/ListMappe");
			
		} catch(Exception e) {
			System.out.println("INPUT ERRATO");
			response.sendRedirect(request.getContextPath() + "/ListMappe");
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
