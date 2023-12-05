package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.entity.OradorRequest;
import ar.com.codoacodo.repository.MySqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nuevo")
public class CreateOradorController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String json = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);

		String nombre = oradorRequest.getNombre();
		String apellido = oradorRequest.getApellido();
		String email = oradorRequest.getMail();
		String tema = oradorRequest.getTema();
		LocalDateTime fechaAlta = LocalDateTime.now();	
		
		try {
			Orador nuevoOrador = new Orador(nombre, apellido, email, tema, fechaAlta);
			OradorRepository repository = new MySqlOradorRepository();
			repository.save(nuevoOrador);
			response.getWriter().print(mapper.writeValueAsString(nuevoOrador));

		} catch (Exception e) {		
			response.getWriter().print(mapper.writeValueAsString(e));			
		}
	}
}
