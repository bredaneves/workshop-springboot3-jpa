package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.resources.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

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
		//ao invés de usar o retorno simples abaixo que cai nos erros padrões, usaremos ...
		//outra chamada para cair em nosso erro personalizado
		//return obj.get();
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//salvar novo usuário no banco
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	//deleção de usuário
	public void delete (Long id) {
		try {
			repository.deleteById(id);		
		} catch (EmptyResultDataAccessException e) { //capturamos o tipo específico de erro nos logs
			throw new ResourceNotFoundException(id);	
		}
		catch(DataIntegrityViolationException e) {
			 throw new DatabaseException(e.getMessage());
		}
		catch (RuntimeException e) { //capturamos o tipo específico de erro nos logs
			e.printStackTrace();
		}
	}
	
	//atualização de usuário
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
