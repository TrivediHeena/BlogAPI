package com.cspassion.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cspassion.blog.entities.Category;
import com.cspassion.blog.entities.Post;
import com.cspassion.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category); 
}
