package controller.pagecontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageControllerG3 {
	//Trabajo común a todos los Page controllers
	public void hacerTrabajo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
