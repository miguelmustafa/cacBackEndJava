package ar.com.codoacodo.repository;

import java.time.LocalDateTime;
import java.util.List;

import ar.com.codoacodo.entity.Orador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OradorRepository repository = new MySqlOradorRepository();
		//Orador orador = repository.getById(1L);
		List<Orador> orador = repository.getAll();
		System.out.println(orador);	
		
		
		//repository.save(new Orador("sapo","pepe","sapopepe@gmail.com","prueba",LocalDateTime.now()));
		
		//Orador oo = repository.getById(1L);
		//System.out.println(oo);
		
		Orador nuevoOradorUpdate = new Orador(3L,"sapo","pepe","sapopepe@gmail.com","como vivir en el agua",LocalDateTime.now());
		repository.update(nuevoOradorUpdate);
		//repository.delete(12L);

	}

}
