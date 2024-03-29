package com.cspassion.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspassion.blog.entities.Category;
import com.cspassion.blog.entities.Post;
import com.cspassion.blog.entities.User;
import com.cspassion.blog.exceptions.ResourceNotFoundException;
import com.cspassion.blog.payloads.PostDto;
import com.cspassion.blog.repositories.CategoryRepo;
import com.cspassion.blog.repositories.PostRepo;
import com.cspassion.blog.repositories.UserRepo;
import com.cspassion.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","User Id ",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","Category Id ",categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName("default.png");
		post.setCreatedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> allPost=this.postRepo.findAll();
		List<PostDto> postDtos=allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ", "category id", categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		
		List<PostDto> postCol=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postCol;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
