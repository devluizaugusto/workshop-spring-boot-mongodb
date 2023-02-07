package com.luizaugusto.worshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.luizaugusto.worshopmongo.domain.Post;
import com.luizaugusto.worshopmongo.domain.User;
import com.luizaugusto.worshopmongo.repository.PostRepository;
import com.luizaugusto.worshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User luiz = new User(null, "Luiz Augusto", "luizaugusto@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("30/09/2022"), "Partiu Viagem!", "Vou viajar para Campina Grande, abraços!", maria);
		Post post2 = new Post(null, sdf.parse("07/02/2023"), "Bom dia!", "Acordei Feliz hoje!", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, luiz));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}
}
