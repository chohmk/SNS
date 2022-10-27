package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	@Autowired
	private PostBO postBO;
	
	@Autowired
	private TimelineBO timelineBO;
	
	/**
	 * 타임라인 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/timeline_view")
	public String timelineView(Model model) {
		//List<Post> postList = postBO.getPostList();
		List<CardView> cardViewList = timelineBO.generateCardList();
		
		//model.addAttribute("postList", postList);
		model.addAttribute("cardList", cardViewList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}