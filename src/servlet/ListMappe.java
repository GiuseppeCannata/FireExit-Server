package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Mappa;
import model.Mappa_DB;

/**
 * Servlet implementation class ListMappe
 */
@WebServlet("/ListMappe")
public class ListMappe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Mappa_DB mdb;
	private ArrayList<Mappa> mappe;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListMappe() {
        super();
        // TODO Auto-generated constructor stub
       mdb = new Mappa_DB();
       mappe = new ArrayList<Mappa>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		mappe = mdb.getListInfoMappe();
		
		request.setAttribute("ListMappe",mappe);
         
        // Forward 
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/ListMappView.jsp");
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
