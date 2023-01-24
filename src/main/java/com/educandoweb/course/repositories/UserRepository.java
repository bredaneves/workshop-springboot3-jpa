package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

//JpaRepository<T, ID> - T objeto e ID tipo
//@Repository - pode ser colocado, mas nesse caso não é preciso devido a herança
public interface UserRepository extends JpaRepository<User, Long> {
	
	//não é preciso implementações nesse caso, pois a extensão do JpaRepository já...
	//traz implementações padrões que usaremos

}
