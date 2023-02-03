package com.luizaugusto.worshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.luizaugusto.worshopmongo.domain.User;
import com.luizaugusto.worshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User luiz = new User(null, "Luiz Augusto", "luizaugusto@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, luiz));
		
	}
}
