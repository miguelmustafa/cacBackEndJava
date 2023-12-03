package ar.com.codoacodo.controllers;

import java.io.IOException;

import ar.com.codoacodo.entity.Orador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/nuevo")
public class NuevoOrador extends HttpServlet {	
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String nombre = request.getParameter("nombre");	
		
        System.out.println(nombre);
	}

}
