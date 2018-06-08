package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PesoArco_DB;
import model.Peso_DB;
import entity.Peso;

/**
 * Servlet implementation class AggiungiPeso
 */
@WebServlet("/AggiungiPesoArco")
public class AggiungiPesoArco extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private int Idarco;
	private int piano;
	private Peso_DB pdb;
	private PesoArco_DB padb;
	private ArrayList<Peso> ListPesi;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiPesoArco() {
        super();
        // TODO Auto-generated constructor stub
        
        ListPesi = new ArrayList<Peso>();
        pdb = new Peso_DB(); 
        padb = new PesoArco_DB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Controllo input derivante dalla query string
		try {
			
			Idarco = Integer.parseInt(request.getParameter("id"));
			piano = Integer.parseInt(request.getParameter("piano"));
			
			ListPesi = pdb.getListPesi();
			
			request.setAttribute("PesiList", ListPesi);
			request.setAttribute("piano", piano);
			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/AggiungiPesoArcoView.jsp");
	        dispatcher.forward(request, response);
	        
		} catch(Exception e) {
			
			response.sendRedirect(request.getContextPath() + "/ListMappe");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int valore = Integer.parseInt(request.getParameter("valore"));
		String descrizione = request.getParameter("descrizione");
		
		boolean hasError = false;
        String errorString = null;
		
		int rowpeso = pdb.findByDescrizione(descrizione);
		
		if(padb.controlloEsistenzaPeso(rowpeso,Idarco)) {
			
			hasError = true;
    		errorString = "Sembra che il peso per il seguente arco sia già esistente";	
    		
		}else {
			
			padb.insertPesoArco(Idarco, valore, rowpeso);
			response.sendRedirect(request.getContextPath() + "/CaricaMappa?piano="+piano);
		}
		
		if(hasError) {
			
			request.setAttribute("errorString", errorString);
			request.setAttribute("PesiList", ListPesi);
			request.setAttribute("piano", piano);
			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/AggiungiPesoArcoView.jsp");
	        dispatcher.forward(request, response);
		}
	}
}
