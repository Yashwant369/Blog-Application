package com.rishu.blog.services;

import java.util.List;

import com.rishu.blog.payloads.PostDto;

public interface PostService {
	
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	public PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	
	List<PostDto>getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	PostDto getPostById(Integer postId);
	List<PostDto>getPostByCategory(Integer categoryId);
	List<PostDto>getPostByUser(Integer userId);
	List<PostDto>searchPosts(String keyword);
	

}
