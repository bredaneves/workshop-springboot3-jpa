package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

//aqui nós temos um controlador de chamadas http sempre que houver chamada do subendereço /users
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	//auto vínculo com o serviço
	@Autowired
	private UserService service;
	
	//ação para ação GET
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//mapeamento para o caminho com o id do usuário...
	//@PathVariable para tornar o id do usuário como parte do caminho
	//aqui não temos uma tratativa para algum subcaminho de users, ainda ao usar um GET
	@GetMapping (value = "/{id}")	
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//aqui temos uma tratativa para ação de POST, no caso, para inserir um usuário
	//a notação @RequestBody precisa ser especificada, indicando que precisamos do body para...
	//obter os dados que serão utilizados
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		
		//o retorno padrão de sucesso é 200, porém nesse caso que estamos inserindo nova...
		//informação o ideal é retorno 201 e para fazer isso, o código deve estar como abaixo...
		//o ResponseEntity.created exige uma URI como parâmetro
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	//notação específica para deleção
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		//esse tipo de retorno indica o código 204
		return ResponseEntity.noContent().build();
	}
	
	//notação para ação de update
	@PutMapping(value="/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
