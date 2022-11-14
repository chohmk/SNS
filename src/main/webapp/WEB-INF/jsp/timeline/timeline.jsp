<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역 --%>
		<%-- 로그인 된 사람만 보이게 조건 --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		</c:if>
		<%--// 글쓰기 영역 끝 --%>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<c:forEach items="${cardList}" var="card">
			
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${card.user.loginId}</span>
					<%-- 내가 쓴 글일 때만 더보기 노출 --%>
					<c:if test="${userId eq card.user.id}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
					</c:if>
				</div>
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<a href="#" class="like-btn" data-user-id="${userId}" data-post-id="${card.post.id}">
					<c:if test="${card.filledLike eq true}">
						<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18px" height="18px" alt="filled heart">
					</c:if>
					<c:if test="${card.filledLike eq false}">
						<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18px" height="18px" alt="empty heart">
					</c:if>
						좋아요 ${card.likeCount}개
					</a>
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.user.loginId}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				<div class="card-comment-list m-2">
					<%-- 댓글 목록 --%>
					<c:forEach items="${card.commentList}" var="commentView">
					<div class="card-comment m-1">
						<span class="font-weight-bold">${commentView.user.loginId}:</span>
						<span>${commentView.comment.content}</span>
						
						<%-- 댓글 삭제 버튼 --%>
						<a href="#" class="commentDelBtn">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
					</div>
					</c:forEach>
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
					</div>
				</div>
			</div> <%--// 카드1 닫기 --%>
			</c:forEach>
		</div> <%--// 타임라인 영역 닫기  --%>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<%-- modal-dialog-centered: 모달창을 수직 가운데 정렬, modal-sm: 작은 모달창 --%>
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content">
      		<%-- 모달 창 안에 내용 넣기 --%>
      		<div class="text-center">
      			<div class="py-3 border-bottom">
      				<a href="#" id="delPostBtn">삭제하기</a>
      			</div>
      			<div class="py-3">
      				<%-- data-dismiss="modal" 모달창 닫힘 --%>
      				<a href="#" data-dismiss="modal">취소</a>
      			</div>
      		</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	// 파일 업로드 이미지(a) 클릭 => 파일 선택 창이 떠야함
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault();  // a태그의 기본 동작 멈춤(화면이 위로 올라가는 것 방지)
		$('#file').click(); // input file을 클릭한 것과 같은 효과
	});
	
	// 사용자가 파일업로드를 했을 때, 유효성 확인 및 업로드 된 파일 이름 노출
	$('#file').on('change', function(e) {
		//alert("체인지");
		
		let fileName = e.target.files[0].name; // ex) cat-g4c8e76014_640.jpg
		//alert(fileName);
		let ext = fileName.split('.').pop().toLowerCase();
		
		// 확장자 유효성 확인
		if (fileName.split('.').length < 2 || 
				(ext != 'gif'
						&& ext != 'png'
							&& ext != 'jpg'
								&& ext != 'jpeg')) {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$(this).val(''); // 파일 태그에 실제 파일 제거
			$('#fileName').text(''); // 파일 이름 비우기
			return;
		}
		
		// 상자에 업로드 된 이름 노출
		$('#fileName').text(fileName);
	});
	
	// 글쓰기 게시
	$('#writeBtn').on('click', function() {
		// validation 
		let content = $('#writeTextArea').val();
		console.log(content);
		if (content.length < 1) {
			alert("글 내용을 입력해주세요");
			return;
		}
		
		let file = $('#file').val();
		if (file == '') {
			alert('파일을 업로드 해주세요');
			return;
		}
		
		// 파일이 업로드 된 경우 확장자 체크
		let ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
		if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
			alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
			$('#file').val(''); // 파일을 비운다.
			return;
		}
		
		// 폼태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]는 업로드된 첫번째 파일
		
		// AJAX form 데이터 전송
		$.ajax({
			type: "post"
			, url: "/post/create"
			, data: formData
			, enctype: "multipart/form-data"    // 파일 업로드를 위한 필수 설정
			, processData: false    // 파일 업로드를 위한 필수 설정
			, contentType: false    // 파일 업로드를 위한 필수 설정
			, success: function(data) {
				if (data.code == 100) {
					location.reload();
				} else if (data.code == 300) { // 비로그인 일 때
					location.href = "/user/sign_in_view";
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(e) {
				alert("글 저장에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});  // --- ajax 끝
	}); //--- 글쓰기 버튼 끝
	
	// 댓글 게시버튼 클릭
	$('.comment-btn').on('click', function() {
		//alert("댓글 게시 클릭");
		let postId = $(this).data('post-id');  // data-post-id
		//alert(postId);
		// 지금 클릭된 게시 버튼의 형제인 input 태그를 가져온다.(siblings)
		let comment = $(this).siblings('input').val().trim();
		//alert(comment);
		
		if (comment == '') {
			alert("댓글 내용을 입력해주세요");
			return;
		}
		
		$.ajax({
			type:'POST'
			,url:'/comment/create'
			,data: {"postId":postId, "content":comment}
			,success: function(data) {
				if (data.result == 'success') {
					location.reload(); // 새로고침
				} else if (data.code == 300) {
					alert("로그인을 해주세요.");
					location.href = "/user/sign_in_view";
				}
			}
			,error: function(jqXHR, textStatus, errorThrown) {
				var errorMsg = jqXHR.responseJSON.status;
				alert(errorMsg + ":" + textStatus);
			}
		});
	});
	
	// 좋아요/해제 toggle
	$('.like-btn').on('click', function(e) {
		e.preventDefault();
		
		let userId = $(this).data('user-id');
		//alert(userId);
		if (userId == '') {
			alert("로그인을 해주세요");
			return;
		}
		
		let postId = $(this).data('post-id');
		//alert(postId);
		$.ajax({
			url:"/like/" + postId
			, success:function(data) {
				if (data.code == 100) {
					location.reload(true);
				} else {
					alert(data.errorMessage);	
				}
			}
			, error: function(e) {
				alert("좋아요/해제 하는데 실패했습니다.");
			}
		});
	});
	
	// 더보기(...) 버튼 클릭 - 글 삭제를 위해서   => 삭제될 글번호를 모달에 넣어준다.
	$('.more-btn').on('click', function(e) {
		e.preventDefault();
		
		let postId = $(this).data('post-id'); // getting
		//alert(postId);
		
		$('#modal').data('post-id', postId); // setting       data-post-id="1"
	});
	
	// 모달창 안에있는 (글)삭제하기 버튼 클릭
	$('#modal #delPostBtn').on('click', function(e) {
		e.preventDefault();
		
		let postId = $('#modal').data('post-id');
		//alert(postId);
		
		// ajax 글삭제
		$.ajax({
			type:"delete"
			, url:"/post/delete"
			, data: {"postId":postId}
			, success: function(data) {
				if (data.result == "success") {
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(e) {
				alert("삭제하는데 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	
}); //-- ready 끝
</script>