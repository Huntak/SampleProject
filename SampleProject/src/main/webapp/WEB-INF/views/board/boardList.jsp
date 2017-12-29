<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA 게시판입니다.</title>
<script src="<c:url value='/resources/js/jquery-1.11.1.min.js'/>"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
<link href="<c:url value='/resources/css/boardList.css'/>" rel="stylesheet">
<script type="text/javascript">
$(function(){
	// 리스트 마우스 오버 이벤트
	$("#boardMid tr:gt(0)").mouseenter(function(){
		$(this).css("background", "#b5deff");
	});

	// 리스트 마우스 오버 이벤트
	$("#boardMid tr:gt(0)").mouseleave(function(){
		if($(this).attr("class") != "clicked"){
			$(this).css("background", "none");
		}
	});

	// 게시글 클릭했을 시 이벤트
	$("#boardMid tr:gt(0)").click(function(){
		if($(this).find("td").length > 1){
			$("#boardMid tr:gt(0)").removeClass("clicked");
			$("#boardMid tr:gt(0)").css("background", "none");
			$(this).addClass("clicked");
			$(this).css("background", "#b5deff");

			console.log($("#boardTitle"));
			console.log($("#boardContents"));
			console.log($("#files"));
			console.log($(".noFile"));
			console.log(document.getElementById("boardDetailIframe"));
			
			$("#boardDetailIframe").attr("src", "${CONTEXT_PATH}/board/boardDetail/" + $(this).find("td").eq(1).html());
			document.getElementById("boardDetailIframe").onload = function() {
				var iframe = $("#boardDetailIframe").contents();
				iframe.find("#boardTitle, #boardContents").css("background", "white");
				iframe.find("#boardTitle, #boardContents").removeAttr("readonly");
				iframe.find("#files").show();
				iframe.find(".noFile").show();
			};
		}
	});

	// 신규 버튼 클릭 시 이벤트
	$("#newBoardBtn").click(function(){
		var iframe = $("#boardDetailIframe").contents();
		iframe.find("#boardTitle, #boardContents").css("background", "white");
		iframe.find("#boardTitle, #boardContents").removeAttr("readonly");
		iframe.find("#files").show();
		iframe.find(".noFile").show();
		iframe.find("#boardId").hide();
		iframe.find("#boardId").val("0");
		iframe.find("#boardId").next().html("(자동 생성됩니다)");
		iframe.find("#boardTitle").val('').attr("placeholder", "제목을 입력해주세요.");
		/** 로그인 기능 만든 후 수정할 것 **/
		iframe.find("#boardWriter").val('1');
		iframe.find("#boardWriterName").val('오훈탁');
		iframe.find("#boardContents").val('').attr("placeholder", "내용을 입력해주세요.");
		iframe.find("#boardWriteDate").val('');
		iframe.find("#boardModifiedDate").val('');
	});

	// 저장 버튼 클릭 시 이벤트
	$("#saveBoardBtn").click(function(){
		var iframe = $("#boardDetailIframe").contents();

		// id가 비어있지 않고
		if(iframe.find("#boardId").val() != ""){
			// 신규도 아닐때 (수정)
			if(iframe.find("#boardId").val() != "0"){
				iframe.find("#dat_flag").val("U");
			}
			// 신규일때
			else{
				iframe.find("#dat_flag").val("I");
			}
		}

		iframe.find("#boardDetailForm").submit();
		
	});

	// 삭제 버튼 클릭 시 이벤트
	$("#deleteBoardBtn").click(function(){
		var delList = new Array();
		
		$(".deleteCheckbox").each(function(){
			if($(this).prop("checked") == true){
				delList.push($(this).parent().next().html());
			}
		});

		if(delList.length <= 0 && $(".clicked").length > 0){
			delList.push($(".clicked").find("td").eq(1).html());
		}

		$.ajax({
			type : "post",
			url : "${CONTEXT_PATH}/board/doProcessMst",
			data : {
				dat_flag : "D",
				delList : JSON.stringify(delList)
			},
			dataType : "json",
			success : function(data){
				if(data == 1){
					alert("성공적으로 삭제되었습니다.");
					document.location.href = "${CONTEXT_PATH}/board/boardList";
				}else{
					alert("삭제에 실패하였습니다.");
				}
			},
			error : function(e){
				console.log("error : " + e);
			}
		});
	});

	// 페이지 마우스 오버 이벤트
	$("#boardBot td").mouseenter(function(){
		$(this).css("background", "#b5deff");
	});

	// 페이지 마우스 오버 이벤트
	$("#boardBot td").mouseleave(function(){
		if($(this).attr("class") != "clicked"){
			$(this).css("background", "none");
		}
	});

	// 페이지 클릭 이벤트
	$("#boardBot td").click(function(){
		$("#boardBot td").removeClass("clicked");
		$("#boardBot td").css("background", "none");
		$(this).addClass("clicked");
		$(this).css("background", "#b5deff");

		if($(this).html() == "&lt;&lt;"){
			document.location.href = "${CONTEXT_PATH}/board/boardList?block=1";
		}else if($(this).html() == "&lt;"){
			document.location.href = "${CONTEXT_PATH}/board/boardList?block=" + (Number(${BOARD_MST_PAGING_LIST.block}) - 1);
		}else if($(this).html() == "&gt;"){
			document.location.href = "${CONTEXT_PATH}/board/boardList?block=" + (Number(${BOARD_MST_PAGING_LIST.block}) + 1);
		}else if($(this).html() == "&gt;&gt;"){
			document.location.href = "${CONTEXT_PATH}/board/boardList?block=" + (Number(${BOARD_MST_PAGING_LIST.blockCount}));
		}else{
			document.location.href = "${CONTEXT_PATH}/board/boardList?page=" + $(this).html();
		}
	});

	// 페이지 번호 선택 시 이벤트
	$("#pageNum").change(function(){
		document.location.href = "${CONTEXT_PATH}/board/boardList?page=" + $(this).val();
	});
});
</script>
</head>
<body>
<div id="boardDiv">
	<div id="boardTop">
		<div><pre> </pre></div>
		<div id="boardTopLeft">
			전체 : <span id="boardCount">${BOARD_MST_PAGING_LIST.boardCount}</span> 건
			페이지 번호 : 
			<select id="pageNum">
				<c:forEach begin="1" end="${BOARD_MST_PAGING_LIST.pageCount}" varStatus="status">
					<c:if test="${BOARD_MST_PAGING_LIST.page == status.index}">
					<option value="${status.index}" selected>${status.index}</option>
					</c:if>
					<c:if test="${BOARD_MST_PAGING_LIST.page != status.index}">
					<option value="${status.index}">${status.index}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div id="boardTopRight">
			<form action="${CONTEXT_PATH}/board/boardList" method="post" id="boardSearchForm">
				<div>
					제목 : <input type="text" name="title" placeholder="입력해주세요." value="${BOARD_MST_LIST.title}">
					내용 : <input type="text" name="contents" placeholder="입력해주세요." value="${BOARD_MST_LIST.contents}">
				</div>
				<div style="text-align: right;margin-top: 2%;">
					From : <input type="date" name="startDate" value="${BOARD_MST_LIST.startDate}">
					To : <input type="date" name="endDate" value="${BOARD_MST_LIST.endDate}">
					<input type="submit" id="searchBoardBtn" value="조회">
					<input type="button" id="newBoardBtn" value="신규">
					<input type="button" id="saveBoardBtn" value="저장">
					<input type="button" id="deleteBoardBtn" value="삭제">
				</div>
			</form>
		</div>
	</div>
	<table id="boardMid">
		<colgroup>
			<col width="50">
			<col width="50">
			<col width="*">
			<col width="100">
			<col width="150">
			<col width="150">
		</colgroup>
		<tbody>
			<tr>
				<th>삭제</th>
				<th>순번</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>수정일</th>
			</tr>
			<c:if test="${fn:length(BOARD_MST_PAGING_LIST.list) > 0}">
				<c:forEach var="board" items="${BOARD_MST_PAGING_LIST.list}">
					<tr>
						<td><input type="checkbox" class="deleteCheckbox"></td>
						<td>${board.id}</td>
						<td>${board.title}</td>
						<td>${board.writerName}</td>
						<td>${board.writeDate}</td>
						<td>${board.modifiedDate}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(BOARD_MST_PAGING_LIST.list) <= 0}">
				<tr>
					<td colspan="6">조회된 결과가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<table id="boardBot">
		<tr>
			<c:if test="${fn:length(BOARD_MST_PAGING_LIST.list) > 0}">
				<c:if test="${BOARD_MST_PAGING_LIST.block != 1}">
				<td><<</td>
				<td><</td>
				</c:if>
				<c:forEach begin="${BOARD_MST_PAGING_LIST.blockStart}" end="${BOARD_MST_PAGING_LIST.blockEnd}" varStatus="status">
					<c:if test="${BOARD_MST_PAGING_LIST.page == status.index}">
					<td class="clicked">${status.index}</td>
					</c:if>
					<c:if test="${BOARD_MST_PAGING_LIST.page != status.index}">
					<td>${status.index}</td>
					</c:if>
				</c:forEach>
				<c:if test="${BOARD_MST_PAGING_LIST.block != BOARD_MST_PAGING_LIST.blockCount}">
				<td>></td>
				<td>>></td>
				</c:if>
			</c:if>
			<c:if test="${fn:length(BOARD_MST_PAGING_LIST.list) <= 0}">
				<td>1</td>
			</c:if>
		</tr>
	</table>
	<iframe id="boardDetailIframe" src="${CONTEXT_PATH}/board/boardDetail/-1" style="width: 100%;margin-top: 3%;height: 550px;border: 0;"></iframe>
	<%-- <c:import id="boardDetailImport" url="/WEB-INF/views/board/boardDetail.jsp"></c:import> --%>
</div>
</body>
</html>