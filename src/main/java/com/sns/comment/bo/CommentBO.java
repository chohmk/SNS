package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;

	public void createComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}

	public List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}

	// input: 글번호
	// output: 가공된 댓글 객체(화면용)
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		// 댓글 목록 가져온다.

		// 반복문 순회   => CommentView   => List<CommentView>에 채운다.

		// 리스트를 리턴한다.
	}
}