package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Hotel;

@WebServlet("/CloseSession")
public class CloseSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Hotel hotel = new Hotel();
	       
	/**
	 * @return 
	 * @see HttpServlet#HttpServlet()
	 */
	public CloseSession() {
	    super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
	    sesion.invalidate();
	    hotel.destroy();
	    response.sendRedirect("loggin.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
	    sesion.invalidate();
	    hotel.destroy();
	    response.sendRedirect("loggin.jsp");
	}
}
