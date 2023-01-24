package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	//auto vínculo com o serviço
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//mapeamento para o caminho com o id do usuário...
	//@PathVariable para tornar o id do usuário como parte do caminho
	@GetMapping (value = "/{id}")	
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
