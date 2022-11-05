package com.cspassion.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cspassion.blog.entities.Post;
import com.cspassion.blog.payloads.PostDto;
import com.cspassion.blog.services.PostService;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto post=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
}
