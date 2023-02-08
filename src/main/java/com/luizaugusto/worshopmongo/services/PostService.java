package com.luizaugusto.worshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizaugusto.worshopmongo.domain.Post;
import com.luizaugusto.worshopmongo.repository.PostRepository;
import com.luizaugusto.worshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("POST NOT FOUND"));
	}
}
