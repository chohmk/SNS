package com.sns.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.dao.PostDAO;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(
			int userId, 
			String userLoginId,  
			String content, 
			MultipartFile file) {
		
		String imagePath = null;
		if (file != null) {
			imagePath = "fileManagerService.saveFile(userLoginId, file)";
		}
		
		return postDAO.insertPost(userId, content, imagePath);
	}
}
