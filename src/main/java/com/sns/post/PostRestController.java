package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam(value="content", required=false) String content,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpSession session) {
		String userLoginId = (String)session.getAttribute("userLoginId");
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			
		}
		
		int row = postBO.addPost(userId, userLoginId, content, file);
				
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			result.put("code", 100);
			result.put("result", "success");
		} else {
			result.put("code", 400);
			result.put("errorMessage", "글 저장에 실패했습니다. 관리자에게 문의해 주세요.");
		}
		
		return result;
		
	}
}
