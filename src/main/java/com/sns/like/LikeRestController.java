package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		// 로그인 여부
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 300); // 비로그인
			result.put("result", "error");
			return result;
		}
		
		// 좋아요 있으면 삭제 / 없으면 추가
		likeBO.likeToggle(postId, userId);
		
		result.put("code", 100); // 성공
		result.put("result", "success");
		
		return result;
	}
}
