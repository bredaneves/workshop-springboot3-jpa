package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

//indicando que é uma classe de configurações pela notação
//a notação profile faz referência ao arquivo application.properties
//a cláusula "implements CommandLineRunner" serve para implementar lógica ...
//para executar no início dessa apicação
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	//com a notação abaixo já ocorre automaticamente o vínculo com o repositório
	//para inserção de dados no banco, ou seja, temos uma injeção de dependência
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		//todo o conteúodo a seguir será executado no momento do início da aplicação
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//abaixo gravamos os novos dados, usando o repositório
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
