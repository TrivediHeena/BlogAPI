package com.cspassion.blog.services;

import java.util.List;

import com.cspassion.blog.entities.Post;
import com.cspassion.blog.payloads.PostDto;

public interface PostService {

	Post createPost(PostDto postDto);
	Post updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	List<Post> getAllPost();
	Post getPostById(Integer postId);
	List<Post> getPostsByCategory(Integer postId);
	List<Post> getPostsByUser(Integer postId);
	List<Post> searchPosts(String keyword);
}
