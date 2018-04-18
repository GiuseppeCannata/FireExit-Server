package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PesoArco_DB;

/**
 * Servlet implementation class EliminaPesoArco
 */
@WebServlet("/EliminaPesoArco")
public class EliminaPesoArco extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaPesoArco() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int Id = Integer.parseInt(request.getParameter("id"));
		int piano = Integer.parseInt(request.getParameter("piano"));
		
		PesoArco_DB padb = new PesoArco_DB();
		
		padb.delete(Id);
		
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
