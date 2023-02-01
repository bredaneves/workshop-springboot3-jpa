package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

//@Service registra classe como serviço do spring
@Service
public class UserService {

	//repositório para comunicação com o banco e notação de vínculo automático
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	//salvar novo usuário no banco
	public User insert(User obj) {
		return repository.save(obj);
	}
}
