package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// 로그인이 안 된 사람도 카드 목록이 보여야 하기 때문에 Integer userId
	public List<CardView> generateCardList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글목록들을 가져온다.
		List<Post> postList = postBO.getPostList();
		
		// 반복문 -> cardView
		for (Post post : postList) {
			CardView card = new CardView();
			// 글 정보
			card.setPost(post);
			
			// 글쓴이 정보
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			// 글 하나에 해당하는 댓글 목록
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commentList);
			
			// 내가 좋아요를 눌렀는지
			if (userId == null) {
				card.setFilledLike(false);
			} else {
				int count = likeBO.getLikeCountByPostIdOrUserId(post.getId(), userId);
				card.setFilledLike(count > 0 ? true : false);
			}
			
			// 좋아요 개수
			card.setLikeCount(likeBO.getLikeCountByPostIdOrUserId(post.getId(), null));
			
			// 카드 리스트에 채우기!!!!!!!!
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}