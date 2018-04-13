package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Nodo_DB;
import model.Peso_DB;

/**
 * Servlet implementation class EliminaPeso
 */
@WebServlet("/EliminaPeso")
public class EliminaPeso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaPeso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int Id = Integer.parseInt(request.getParameter("id"));
        
        System.out.println("cis sono");
		
		Peso_DB pdb = new Peso_DB();
		
		if(pdb.delete(Id)) {
			response.sendRedirect(request.getContextPath() + "/ListPesi");
		}else {
			
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
