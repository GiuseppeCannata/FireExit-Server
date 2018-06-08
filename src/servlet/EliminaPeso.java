package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Peso_DB;

/**
 * Servlet implementation class EliminaPeso
 */
@WebServlet("/EliminaPeso")
public class EliminaPeso extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Peso_DB pdb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaPeso() {
        super();
        // TODO Auto-generated constructor stub
        
        pdb = new Peso_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
	        int Id = Integer.parseInt(request.getParameter("id"));
			
			if(pdb.delete(Id))
				response.sendRedirect(request.getContextPath() + "/ListPesi");
		
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
