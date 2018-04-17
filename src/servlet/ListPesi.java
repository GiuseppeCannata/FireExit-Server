package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Peso;
import model.Peso_DB;

/**
 * Servlet implementation class ListPesi
 */
@WebServlet("/ListPesi")
public class ListPesi extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Peso> list;
	private Peso_DB pdb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPesi() {
        super();
        // TODO Auto-generated constructor stub
        
        list = new ArrayList<Peso>();
        pdb = new Peso_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  
        list = pdb.getListPesi();
       
        request.setAttribute("PesiList", list);
         
        // Forward to NodiListView.jsp
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/PesiListView.jsp");
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
