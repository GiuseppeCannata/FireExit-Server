package servlet;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Mappa_DB;

/**
 * Servlet implementation class InserimentoInfoMappa
 */
@WebServlet("/InserimentoInfoMappa")
@MultipartConfig
public class InserimentoInfoMappa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoInfoMappa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoInfoMappaView.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	     int piano = Integer.parseInt((String)request.getParameter("piano"));
	    // System.out.println(piano);
	     final Part filePart = request.getPart("piantina");
	     String fileName = "map"+piano;
	    // final String fileName = getFileName(filePart);
	    // System.out.println("FileName:"+fileName);
	     InputStream filecontent = null;
	     OutputStream out = null;
	     
	     boolean hasError = false;
	     String errorString = null;
	     
	     try {
	         out = new FileOutputStream(new File("C:\\Users\\User\\Desktop\\glassfish5\\glassfish\\domains\\domain1\\docroot\\src\\images" + File.separator + fileName+".png"));
	         filecontent = filePart.getInputStream();

	         int read = 0;
	         final byte[] bytes = new byte[1024];

	         while ((read = filecontent.read(bytes)) != -1) {
	             out.write(bytes, 0, read);
	         }
	         System.out.print("ok");
	         //LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
	         //        new Object[]{fileName, path});
	     } catch (FileNotFoundException fne) {
	    	 fne.printStackTrace();
	         System.out.print("errore");
	     } finally {
	         if (out != null) {
	             out.close();
	         }
	         if (filecontent != null) {
	             filecontent.close();
	         }
	         
	     }
	     
	     Mappa_DB mdb = new Mappa_DB();
	     try {
	    	 
			if(mdb.InserimentoInfo(piano,fileName)) {
				 response.sendRedirect(request.getContextPath() + "/CaricaMappa?piano="+piano);
			}else {
				request.setAttribute("messaggio", "Sembra esserci stato un errore. La invitiamo a riprovare scusandoci per l incoveniete"); 
			    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Messaggio.jsp");
			    dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if(e.getErrorCode() == 30000) {
				hasError = true;
	            errorString = "Piano già presente nel DB. Il piano deve essere univoco";
			}
		}
	     
	     
	     if (hasError) {		
		       
			    request.setAttribute("errorString", errorString);
			    
			    
			    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/InserimentoInfoMappaView.jsp");
		 
			    dispatcher.forward(request, response);
					
		   }
		   
	 }


	/*     
	 private String getFileName(final Part part) {
	     final String partHeader = part.getHeader("content-disposition");
	     
	     for (String content : part.getHeader("content-disposition").split(";")) {
	         if (content.trim().startsWith("filename")) {
	             return content.substring(
	                     content.indexOf('=') + 1).trim().replace("\"", "");
	         }
	     }
	     return null;
	 }
	 */

}
