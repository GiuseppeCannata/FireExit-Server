package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Discover;

/**
 * Servlet implementation class ATTIVA
 */
@WebServlet("/ATTIVA")
public class ATTIVA extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ATTIVA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Thread discoveryThread = new Thread(Discover.getInstance());
	    discoveryThread.start();
	    
	    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Messaggio.jsp");
	    request.setAttribute("Messaggio", "Ora ogni device con istallata FireExit sar� in grado di localizzare il server");
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
