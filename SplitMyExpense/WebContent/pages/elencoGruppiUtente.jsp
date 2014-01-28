<%@page
	import="it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.*"%>
<%@page import="it.unical.ingsw.splitMyExpense.domain.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<script>
$(".linkToCreaGruppoSpesa").click(function() {
	$("#appContainer").load("pages/creaGruppoSpesa.jsp");
});

$linkToGruppo = $("[data-class=linkToGruppo]");

$("[data-tableColor=true]").hover(function() {
	$(this).css("background", "#3399cc");
	$(this).css("color", "white");
	$(this).css("cursor","pointer");
}, function() {
	$(this).css("background", "none");
	$(this).css("color", "#666666");
});

$linkToGruppo.click(function() {
	idGruppo=$(this).attr("data-idGruppo");
	$("#appContainer").load("pages/elencoListeSpesaGruppo.jsp", { id: idGruppo});
});
</script>

<div class="header">

			<button class="pure-button" onclick="goToHome()"> <i
				class="fa fa-home"></i><span id="bottonTitle">Home</span>
			</button>
			<button class="pure-button">
				<i class="fa fa-bar-chart-o"></i><span id="bottonTitle">Stat</span>
			</button>
			<button class="pure-button">
				<i class="fa fa-list-alt"></i><span id="bottonTitle">List</span></span>
			</button>
			<button class="pure-button linkToCreaGruppoSpesa"> <i
				class="fa fa-plus-square-o"></i><span id="bottonTitle">Crea
					Gruppo Spesa</span>
			</button>

		</div>
		<br>

<table class="pure-table pure-table-horizontal">

	<%
		DataAccess dataAccess = new DataAccess(
				ConnectionFactory.getConnection());
		List<GruppoSpesa> gsu = dataAccess
				.getListaGruppiUtente(((Utente) session
						.getAttribute("utente")).getId());
		if (gsu.isEmpty()) {
	%><h2>non fai ancora parte di nessun gruppo spesa</h2>
		<a class="linkToCreaGruppoSpesa" href="#">creane uno adesso</a> 
	<%
		} else {
	%><thead>
		<tr>
			<th>Foto</th>
			<th>Nome</th>
			<th class="pure-hidden-phone">Descrizione</th>
		</tr>
	</thead>

	<tbody>
		<%
			for (int i = 0; i < gsu.size(); i++) {
		%>
		<tr data-class="linkToGruppo" data-tableColor="true" data-idGruppo="<%=gsu.get(i).getId()%>">
			<td><img src="<%=gsu.get(i).getFoto()%>" height="20px"
				width="20px"></td>
			<td><%=gsu.get(i).getNome()%></td>
			<td class="pure-hidden-phone"><%=gsu.get(i).getDescrizione()%></td>
		</tr>
		<%
			}
		%>
		<tr class="linkToCreaGruppoSpesa" data-tableColor="true">
		<td><img src="img/add.png" height="20px"
				width="20px"></td>
			<td>nuovo</td>
			<td class="pure-hidden-phone"></td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>



