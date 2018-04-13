package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Peso_DB;
import entity.Peso;

/**
 * Servlet implementation class ModificaPeso
 */
@WebServlet("/ModificaPeso")
public class ModificaPeso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Peso_DB pdb;
	private Peso peso;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaPeso() {
        super();
        // TODO Auto-generated constructor stub
        pdb = new Peso_DB();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int Id = Integer.parseInt(request.getParameter("id"));
		
		peso = pdb.findById(Id);
		
		request.setAttribute("peso", peso);
		
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/ModificaPesoView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int Id = Integer.parseInt(request.getParameter("Id"));
        String Descrizione = (String) request.getParameter("Descrizione");
        int p = Integer.parseInt(request.getParameter("Peso"));
        
        peso = new Peso(Id,Descrizione,p);
        	
		if (pdb.updatePeso(peso)) 
			response.sendRedirect(request.getContextPath() + "/ListPesi");
		else {
			
			//errore view
		}  
	}
}
