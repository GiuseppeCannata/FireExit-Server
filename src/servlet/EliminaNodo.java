package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Arco;
import model.Arco_DB;
import model.Nodo_DB;


/**
 * Servlet implementation class EliminaNodo
 */
@WebServlet("/EliminaNodo")
public class EliminaNodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Nodo_DB ndb;
	private Arco_DB adb;
	private ArrayList<Arco> archiList;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaNodo() {
        super();
        // TODO Auto-generated constructor stub
        
        ndb = new Nodo_DB();
        adb = new Arco_DB();
        archiList = new ArrayList<Arco>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int Id = Integer.parseInt(request.getParameter("id"));
		
		//importante all eliminazione di un nodo vanno eliminati anche tutti gli archi relativi a quel nodo
		//con archi si intende anche i pesi degli archi
		//TODO: POSSO FARE DIVERSAMENTE?
		archiList = adb.getArchiByNodoId(Id);
		if(archiList != null)
			for(Arco arco: archiList) 
		    	adb.deleteArco(arco);
	    	
		if(ndb.delete(Id)) 
			response.sendRedirect(request.getContextPath() + "/ListNodi");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
