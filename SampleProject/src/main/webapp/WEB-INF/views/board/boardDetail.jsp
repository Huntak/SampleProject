<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetail</title>
<script src="<c:url value='/resources/js/jquery-1.11.1.min.js'/>"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
<link href="<c:url value='/resources/css/boardDetail.css'/>" rel="stylesheet">
<script type="text/javascript">
function frmsubmit(){
	//grab all form data  
    var formData = new FormData($("#boardDetailForm")[0]);
    $.ajax({
        url: "${CONTEXT_PATH}/board/doProcessMst",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: function(data) {
	        if(data[0] == 0){
	        	console.log("게시판 저장 실패");
			}else if(data[1] == 0){
	        	console.log("파일 저장 실패");
	        	window.parent.document.location.href = "${CONTEXT_PATH}/board/boardList";
			}else{
				window.parent.document.location.href = "${CONTEXT_PATH}/board/boardList";
            }
        }
    });

    return false;
}
</script>
</head>
<body>
	<form id="boardDetailForm" action="${CONTEXT_PATH}/board/doProcessMst" method="post" enctype="multipart/form-data" onsubmit="return frmsubmit()">
		<input type="hidden" id="dat_flag" name="dat_flag">
		<table id="boardDetail">
			<colgroup>
				<col width="200">
				<col width="*">
			</colgroup>
			<tbody>
				<tr>
					<td>순번</td>
					<td><input id="boardId" type="text" name="id" value="<c:if test="${BOARD_MST_LIST.id != 0}">${BOARD_MST_LIST.id}</c:if>" readonly><span></span></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input id="boardTitle" type="text" name="title" value="${BOARD_MST_LIST.title}" readonly></td>
				</tr>
				<tr>
					<td>작성자<input type="hidden" id="boardWriter" name="writer" value="${BOARD_MST_LIST.writer}"></td>
					<td><input id="boardWriterName" type="text" name="writerName" value="${BOARD_MST_LIST.writerName}" readonly></td>
				</tr>
				<tr style="line-height:250px;">
					<td>내용</td>
					<td><input id="boardContents" type="text" name="contents" value="${BOARD_MST_LIST.contents}" readonly></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><input id="boardWriteDate" type="text" name="writeDate" value="${BOARD_MST_LIST.writeDate}" readonly></td>
				</tr>
				<tr>
					<td>수정일</td>
					<td><input id="boardModifiedDate" type="text" name="modifiedDate" value="${BOARD_MST_LIST.modifiedDate}" readonly></td>
				</tr>
				<tr>
					<td>파일 업로드</td>
					<td>
						<input multiple="multiple" type="file" id="files" name="files" />
					</td>
				</tr>
				<tr>
					<td>파일 목록</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>