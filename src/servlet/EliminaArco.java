package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Nodo;
import entity.PesoArco;
import entity.Arco;
import model.Arco_DB;
import model.Nodo_DB;
import model.PesoArco_DB;

/**
 * Servlet implementation class EliminaArco
 */
@WebServlet("/EliminaArco")
public class EliminaArco extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Nodo_DB ndb;
	private Arco_DB adb;
	private PesoArco_DB padb;
	private ArrayList<PesoArco> paList;
	private ArrayList<Arco> archiList;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaArco() {
        super();
        // TODO Auto-generated constructor stub
        
        ndb = new Nodo_DB();
        adb = new Arco_DB();
        padb = new PesoArco_DB();
        paList = new ArrayList<PesoArco>();
        archiList = new ArrayList<Arco>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int Idarco = Integer.parseInt(request.getParameter("id"));
		int piano = Integer.parseInt(request.getParameter("piano"));
		int NodoPartenzaId = Integer.parseInt(request.getParameter("NodoPartenza"));
		int NodoArrivoId = Integer.parseInt(request.getParameter("NodoArrivo"));
			
		Nodo NodoPartenza = ndb.FindNodoById(NodoPartenzaId);
		Nodo NodoArrivo = ndb.FindNodoById(NodoArrivoId);
		paList = padb.findPesiByIdArco(Idarco);
		
		adb.deleteArco( new Arco(Idarco,NodoPartenza,NodoArrivo,paList) );
		
		//controllo se è il caso di eliminare nodi
		// i nodi saranno cancellati solo nel caso in cui non abbiano una stella
		archiList = adb.findArchiByPiano(piano);
		
		boolean esitoA = false;
		boolean esitoP = false;
		
		// eliminazione di eventuali nodi che non possiedono una stella
		for(Arco arco: archiList) {
			
			if(arco.getNodoPartenza().getId() == NodoPartenzaId || arco.getNodoArrivo().getId() == NodoPartenzaId  )
				esitoP = true;  

			if(arco.getNodoArrivo().getId() == NodoArrivoId || arco.getNodoPartenza().getId() == NodoArrivoId )
				esitoA = true;
		}
		
		if(!esitoA)
			ndb.delete(NodoArrivoId);
			
	    if(!esitoP)
	    	ndb.delete(NodoPartenzaId);
		
	    response.sendRedirect(request.getContextPath() + "/CaricaMappa?piano="+piano);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
