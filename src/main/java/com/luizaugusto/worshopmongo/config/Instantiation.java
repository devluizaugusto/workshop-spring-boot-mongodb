package com.luizaugusto.worshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.luizaugusto.worshopmongo.domain.Post;
import com.luizaugusto.worshopmongo.domain.User;
import com.luizaugusto.worshopmongo.dto.AuthorDTO;
import com.luizaugusto.worshopmongo.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(maria, alex, luiz));
		
		Post post1 = new Post(null, sdf.parse("30/09/2022"), "Partiu Viagem!", "Vou viajar para Campina Grande, abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("07/02/2023"), "Bom dia", "Acordei Feliz hoje!",new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa Viagem mano!", sdf.parse("30/09/2022"), new AuthorDTO(luiz));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("01/10/2022"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("07/02/2023"), new AuthorDTO(luiz));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
}
