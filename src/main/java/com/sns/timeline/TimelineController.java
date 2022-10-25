package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	/**
	 * 타임라인 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/timeline_view")
	public String timelineView(Model model) {
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}