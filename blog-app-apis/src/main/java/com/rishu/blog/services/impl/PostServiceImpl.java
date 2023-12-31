package com.rishu.blog.services.impl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.rishu.blog.entity.Category;
import com.rishu.blog.entity.Post;
import com.rishu.blog.entity.User;
import com.rishu.blog.exceptions.ResourceNotFoundException;
import com.rishu.blog.payloads.PostDto;
import com.rishu.blog.repositories.CategoryRepo;
import com.rishu.blog.repositories.PostRepo;
import com.rishu.blog.repositories.UserRepo;
import com.rishu.blog.services.PostService;

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


	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		User users = this.userRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		Category category = this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		Post post = this.modelMapper.map(postDto , Post.class);
		post.setImageName("default.png" );
		post.setAddDate(new Date());
		post.setCategory(category);
		post.setUser(users);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		// TODO Auto-generated method stub
		//implementing pagination 
		
		Pageable p = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).descending());
		
		
		Page<Post>pagePost = this.postRepo.findAll(p);
		
		List<Post>allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().
				map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
				
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundException("User","Id",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		List<Post>posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().
				map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
				
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("Post","Id",userId));
		List<Post>posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().
				map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
			
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post>posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto>postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
