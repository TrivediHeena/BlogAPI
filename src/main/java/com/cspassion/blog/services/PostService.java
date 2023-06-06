package com.cspassion.blog.services;

import java.util.List;

import com.cspassion.blog.entities.Post;
import com.cspassion.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPost();
	PostDto getPostById(Integer postId);
	List<PostDto> getPostsByCategory(Integer categoryId);
	List<PostDto> getPostsByUser(Integer userId);
	List<Post> searchPosts(String keyword);
}
