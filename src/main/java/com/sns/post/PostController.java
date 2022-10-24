package com.sns.post;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
	@RequestMapping("/post_list_view")
	public String postListView(HttpSession session, Model model) {
		Integer userId = (Integer)session.getAttribute("userId"); // 로그인이 풀려있으면 null이기 때문에 Integer
		if (userId == null) {
			// 로그인이 풀려있으면 로그인 페이지로 리다이렉트
			return "redirect:/user/sign_in_view";
		}

		model.addAttribute("viewName", "post/postList");
		return "template/layout";
	}
}
