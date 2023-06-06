package com.cspassion.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cspassion.blog.entities.Post;
import com.cspassion.blog.payloads.PostDto;
import com.cspassion.blog.services.PostService;

@RestController
@RequestMapping("/api/post/")
public class PostController {

	@Autowired
	private PostService postService;
	
	//create post
	@PostMapping("/user/{userId}/category/{categoryId}/createpost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto post=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> allPosts=this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
	}
	
	//get post by post id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
}
