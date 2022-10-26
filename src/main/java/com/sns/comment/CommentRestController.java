package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId, 
			@RequestParam(value="content", required=false) String content,
			HttpSession session) {
		

		Map<String, Object> result = new HashMap<>();
		result.put("code", 100);
		result.put("result", "success");
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 300);	// 비로그인 상태
			result.put("result", "error");
			result.put("result", "로그인을 해주세요");
			return result;
		}
		
		// bo
		return result;
	}
	
}
