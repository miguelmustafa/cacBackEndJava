package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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

@WebServlet("/orador")
public class OradorController extends HttpServlet{	
	
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		OradorRepository repository = new MySqlOradorRepository();
		List<Orador> oradores = repository.getAll();
		resp.getWriter().print(mapper.writeValueAsString(oradores));
		System.out.println(oradores);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String json = req.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);

		Long id = oradorRequest.getId();
		String nombre = oradorRequest.getNombre();
		String apellido = oradorRequest.getApellido();
		String mail = oradorRequest.getMail();
		String tema = oradorRequest.getTema();	
		
		try {
			
			Orador nuevoOrador = new Orador(id,nombre,apellido,mail,tema);
			OradorRepository repository = new MySqlOradorRepository();
			repository.update(nuevoOrador);

		} catch (Exception e) {			
			resp.getWriter().print(mapper.writeValueAsString(e));			
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String _id = req.getParameter("id");
		Long id = Long.parseLong(_id);

		OradorRepository repository = new MySqlOradorRepository();
		repository.delete(id);
	}
	
}
