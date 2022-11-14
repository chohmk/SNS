package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	public void createComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
	private List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	// input: 글번호
	// output: 가공된 댓글 객체(화면용)
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		
		// 결과물
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 댓글 목록 가져온다.(원본)
		List<Comment> commentList = getCommentListByPostId(postId);
		
		// 반복문 순회   => CommentView   => List<CommentView>에 채운다.
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			commentView.setComment(comment);
			
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user); // 댓글쓴이
			
			// 리스트에 담는다.
			commentViewList.add(commentView);
		}
		
		// 리스트를 리턴한다.
		return commentViewList;
	}
	
	public void deleteCommentsByPostId(int postId) {
		commentDAO.deleteCommentsByPostId(postId);
	}
}