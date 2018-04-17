package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Nodo;
import entity.Peso;
import model.Arco_DB;
import model.Nodo_DB;
import model.PesoArco_DB;
import model.Peso_DB;

/**
 * Servlet implementation class InserimentoArco
 */
@WebServlet("/InserimentoArco")
public class InserimentoArco extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Peso> pesi;
	private ArrayList<Nodo> nodi;
    private Nodo_DB ndb;
    private Peso_DB pdb;
    private PesoArco_DB padb;
    private Arco_DB adb;
    private int piano;
    private int nPesi;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoArco() {
        super();
        // TODO Auto-generated constructor stub
        
        nodi = new ArrayList<Nodo>();
        ndb = new Nodo_DB();
        pdb = new Peso_DB();
        pesi = new ArrayList<Peso>();
        adb = new Arco_DB();
        padb = new PesoArco_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		piano = Integer.parseInt(request.getParameter("piano"));
		nPesi = Integer.parseInt(request.getParameter("nPesi"));
		
		ndb.findNodiByPiano(nodi, piano);
		pesi = pdb.getListPesi();
		
		request.setAttribute("NodiList", nodi);
		request.setAttribute("NPesi", nPesi);
		request.setAttribute("piano", piano);
		request.setAttribute("PesiList",pesi);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoArcoView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int NodoPartenzaId = Integer.parseInt(request.getParameter("NodoPartenzaId"));
        int NodoArrivoId = Integer.parseInt(request.getParameter("NodoArrivoId"));
        int piano = Integer.parseInt(request.getParameter("piano"));
        int NPesi = Integer.parseInt(request.getParameter("NPesi"));
        
        ArrayList<String> descrizioni = new ArrayList<String>();
        ArrayList<Integer> valori = new ArrayList<Integer>();
        
        boolean hasError = false;
        String errorString = null;
        int rowArco = 0;
        int rowPeso = 0;
        
        //Prelievo dei Valori e tipo di peso inseriti
        for(int i=0; i < NPesi ; i++) {
    		descrizioni.add(request.getParameter("descrizione"+i));
    		valori.add(Integer.parseInt(request.getParameter("valore"+i)));
		}
        
        //1^ controllo
        if(NodoPartenzaId == NodoArrivoId) {
        	hasError = true;
    		errorString = "Il Nodo di partenza e quello di arrivano non possono coincidere";	
        }else {
      
        	//2^ controllo
	    	if(!adb.controlloEsistenzaArco(NodoPartenzaId,NodoArrivoId)) {
	    		
	    		//inseriamo le info circa l arco
	    	    adb.insertArco(NodoPartenzaId, NodoArrivoId, piano);
	    	    rowArco = adb.findIDArcoByNodoPIDNodoAID(NodoPartenzaId,NodoArrivoId);
	    	    
	    	    //inseriamo i PesiArco
	    		for(int i=0; i< NPesi ; i++) {
	        		rowPeso = pdb.findByDescrizione(descrizioni.get(i));
	        		padb.insertPesoArco(rowArco, valori.get(i), rowPeso); 
	    		}
	    		
	    	}else {
	    		hasError = true;
	    		errorString = "Sembra che l'arco sia già esiste.";
	    	}
        }
        
        if (hasError) {		
	       
		    request.setAttribute("errorString", errorString);
		    request.setAttribute("NodiList", nodi);
		    request.setAttribute("PesiList",pesi);
			request.setAttribute("NPesi", NPesi);
			request.setAttribute("piano", piano);
		    
		    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoArcoView.jsp");
		    dispatcher.forward(request, response);
				
	   }
	}
}
