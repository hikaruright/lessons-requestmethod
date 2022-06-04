<%@page import="java.util.Optional"%>
<%@page import="jp.co.solxyz.jsn.samples.web.methods.entity.ChatEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<style>
.card {
	background-color: #e2e2e2;
	border: solid 1px gray;
	min-height: 4em;
	padding: 0.6em;
	margin: 1em;
}

.card p.footer {
	font-size: small;
	color: gray;
	text-align: right;
	padding: 0;
	margin: 0;
}

footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 250px;
	background-color: #f5f5f5;
}

input[type=text] {
	width: 100%;
}

textarea {
	width: 100%;
	height: 6em;
	min-height: 6em;
	max-height: 6em;
}

form {
	padding-top: 0.6em;
	padding-bottom: 0.6em;
}
</style>

<script>

function deleteData(id) {
	
	alert(id);
	// 確認アラートを出す
	if(confirm("削除します、よろしいですか？")) {
		// JavaScriptを用いた非同期通信
		$.ajax({
			url: `./chat?id=${id}`,
			method: "delete",
			data: {
				id: id
			}
		}).done(() => {
			alert("success.");
			location.href="./";
		}).fail((err) => {
			alert("Error.");
		});
	}
}

</script>

</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">チャットアプリ（β）</a>
		</div>
	</nav>
	<div class="container">
		<%
		@SuppressWarnings("unchecked")
		Optional<ChatEntity> data = (Optional<ChatEntity>) request.getAttribute("data");

		if (data.isPresent()) {
			ChatEntity entity = data.get();
		%>

		<form action="chat" method="post">
			<div class="row">
				<input type="hidden" name="id" value="<%=entity.getId()%>">
				<div class="col-lg-6 offset-lg-3">
					<div class="card">
						<h4><%=entity.getName()%></h4>
						<input type="hidden" name="name" value="<%=entity.getName()%>">
						<p>
							<textarea name="message"><%=entity.getMessage()%></textarea>
						</p>
						<p class="footer">
							投稿日<%=entity.getPosted()%></p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-2 offset-lg-3">
					<button type="button" onclick="deleteData(<%=entity.getId()%>)"
						class="btn btn-danger">削除</button>
				</div>
				<div class="col-lg-2 offset-lg-3">
					<input type="submit" value="更新" class="btn btn-primary">
				</div>
			</div>
		</form>
		<%
		} else {
		%>
		<div class="row">
			<h3>The data is not found.</h3>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>