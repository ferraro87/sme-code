<%@page import="it.unical.ingsw.splitMyExpense.domain.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<title>SplitMyExpense &ndash; App</title>

<link rel="stylesheet" href="css/pure-min.css">
<link rel="stylesheet" href="css/layouts/side-menu.css">
<link rel="stylesheet" href="css/layouts/login.css">
<link href="font-awesome-4.0.3/css/font-awesome.css" rel="stylesheet">
<link href="font-awesome-4.0.3/css/font-awesome.min.css"
	rel="stylesheet">

<script type="text/javascript" src="javascript/jquery-1.9.1.js"></script>
<script type="text/javascript" src="javascript/script.js"></script>

<!-- Apple WEB Application -->

<link rel="apple-touch-icon" href="appleMobile/img/icon.png" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="SME">
<link href="appleMobile/img/apple-touch-startup-image-640x1096.png"	rel="apple-touch-startup-image">

<link rel="stylesheet" href="appleMobile/add2home.css">
<script type="application/javascript" src="appleMobile/add2home.js"></script>

<script>
function goToHome(){
	$("#appContainer").load("pages/elencoGruppiUtente.jsp");
}
</script>


<!-- Apple WEB Application -->

</head>
<body>

	<%
		Utente utente = (Utente) session.getAttribute("utente");
	%>

	<div id="container">

		<%
			if (utente == null) {
		%>
		<div id="logInPage"><%@ include file="pages/logIn.jsp"%></div>
		<%
			} else {
		%>
		<div id="appPage"><%@ include file="pages/app.jsp"%></div>
		<%
			}
		%>

		<div style="display: none" id='registrazionePage'><%@ include
				file='pages/registrazione.jsp'%></div>

	</div>

</body>
</html>