package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {

	// 글쓰기
	public int insertPost(
			@Param("userId") int userId, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	public List<Post> selectPostList();
	
	public Post selectPostById(int id);
	
	public void deletePostByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
}