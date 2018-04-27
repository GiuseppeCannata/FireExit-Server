package servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Nodo_DB;
import entity.Nodo;

/**
 * Servlet implementation class ListNodi
 */
@WebServlet("/TabellaNodi")
public class TabellaNodi extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Nodo_DB ndb;
	private ArrayList<Nodo> nodiList; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TabellaNodi() {
        super();
        // TODO Auto-generated constructor stub
        
        ndb = new Nodo_DB();
        nodiList = new ArrayList<Nodo>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		nodiList = ndb.getListNodi();
		
		request.setAttribute("NodiList", nodiList);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/TabellaNodiView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
