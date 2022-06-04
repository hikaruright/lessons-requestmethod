<%@page
	import="jp.co.solxyz.jsn.samples.web.methods.entity.ChatEntity,java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
	integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
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

footer input[type=text] {
	width: 100%;
}

footer textarea {
	width: 100%;
	height: 6em;
	min-height: 6em;
	max-height: 6em;
}

footer form {
	padding-top: 0.6em;
	padding-bottom: 0.6em;
}
</style>
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
		List<ChatEntity> data = (List<ChatEntity>) request.getAttribute("data");
		%>
		<div class="row">
			<%
			for (ChatEntity row : data) {
			%>
			<div class="col-lg-6 offset-lg-3">
				<div class="card">
					<h4><%=row.getName()%></h4>
					<p><%=row.getMessage()%></p>
					<p class="footer">
					<%=row.getPosted()%>
					<a href="chat?id=<%=row.getId() %>" class="link-primary">編集</a>
					</p>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<form style="padding: 1em;" action="./" method="post">
						<h4>
							<input type="text" name="name" placeholder="なまえ"
								class="form-control">
						</h4>
						<p>
							<textarea name="message" placeholder="メッセージ" class="form-control"></textarea>
						</p>
						<input type="submit" value="送信" class="btn btn-primary" />
					</form>
				</div>
			</div>
		</div>
	</footer>
</body>

</html>