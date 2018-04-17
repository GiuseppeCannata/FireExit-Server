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
	private ArrayList<Arco> Archi;
	private Nodo_DB ndb;
	private Arco_DB adb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaNodo() {
        super();
        // TODO Auto-generated constructor stub
        
        ndb = new Nodo_DB();
        adb = new Arco_DB();
        Archi = new ArrayList<Arco>();
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
		Archi = adb.getArchiByNodoId(Id);
		if(Archi != null)
			for(Arco arco: Archi) 
		    	adb.deleteArco(arco);
	    	
		if( ndb.delete(Id) ) 
			response.sendRedirect(request.getContextPath() + "/ListNodi");
		else {
			
			request.setAttribute("messaggio", "Sembra esserci stato un errore. La invitiamo a riprovare scusandoci per l incoveniete");
		    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Messaggio.jsp");
		    dispatcher.forward(request, response);
		    
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
