<%@page
	import="it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.*"%>
<%@page import="it.unical.ingsw.splitMyExpense.domain.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%DataAccess dataAccess = new DataAccess(
				ConnectionFactory.getConnection());
		int idGruppo=Integer.parseInt(request.getParameter("id"));
	%>
	
	<script>
	$(".linkToCreaListaSpesa").click(function(){
		creaListaSpesa("<%=idGruppo%>");
	});
	$("[data-tableColor=true]").hover(function() {
		$(this).css("background", "#3399cc");
		$(this).css("color", "white");
		$(this).css("cursor","pointer");
	}, function() {
		$(this).css("background", "none");
		$(this).css("color", "#666666");
	});
	$("[data-class=linkToLista]").click(function(){
		$idLista=$(this).attr("data-idLista");
		$("#appContainer").load("pages/listaSpesa.jsp", { idLista: $idLista});
	});
	$(".aggiungiUtenteButton").click(function(){
		$("#aggiungiUtenteForm").slideToggle("slow");
	});
	$("#annullaAggiungiUtenteButton").click(function(){
		$("#aggiungiUtenteForm").slideToggle("slow");
	});
	$("#aggiungiUtenteToGruppoButton").click(function(){
		emailNuovo=$("#emailNuovoUtenteInput").val();
		aggiungiUtenteToGruppo(emailNuovo,"<%=idGruppo%>");
	});
	$(".esciDaGruppo").click(function(){
		var result = window.confirm("sei sicuro di voler uscire dal gruppo?");
		if(result){
			abbandonaGruppo("<%=idGruppo%>");	
		}
	});
	</script>
	
<div class="header">

			<button class="pure-button" onclick="goToHome()"> <i
				class="fa fa-home"></i><span id="bottonTitle">Home</span>
			</button>
			<button class="pure-button aggiungiUtenteButton">
				<i class="fa fa-male"></i><span id="bottonTitle">Aggiungi Utente</span>
			</button>
			<button class="pure-button esciDaGruppo">
				<i class="fa fa-sign-out"></i><span id="bottonTitle">Abbandona il Gruppo</span></span>
			</button>
			<button class="pure-button linkToCreaListaSpesa"> <i
				class="fa fa-plus-square-o"></i><span id="bottonTitle">Crea
					Lista Spesa</span>
			</button>

		</div>
 	
		<div style="display: none;width:100%" id="aggiungiUtenteForm" class="pure-form">
    <fieldset>
        <legend>Aggiungi un utente al gruppo</legend>

        <input id="emailNuovoUtenteInput" type="email" placeholder="Email">

        <button id="aggiungiUtenteToGruppoButton" class="pure-button pure-button-primary"  style="background: rgb(28,184,65)">Aggiungi</button>
        <button id="annullaAggiungiUtenteButton" class="pure-button" style="background: rgb(202,60,60)">Annulla</button>
    </fieldset>    
	</div>
	

	
	
	
	
		
		<br>
		<% GruppoSpesa gruppo=dataAccess.getGruppo(idGruppo);
		%>
		<table>
		<tr>
			<td><img src="<%=gruppo.getFoto()%>" height="50px"></td>
			<td><h2 class="content-subhead">&nbsp;<%=gruppo.getNome()%></h2></td>
		</tr>
		<tr>
			<td colspan="2"><center><p><%=gruppo.getDescrizione()%></p></center></td>
		</tr>
		</table>
		

<table class="pure-table pure-table-horizontal">

	<%	List<ListaSpesa> lsg = dataAccess.getListeSpesaGruppoSpesa(gruppo.getId());
		if (lsg.isEmpty()) {
	%><h2>nessuna lista spesa presente nel gruppo</h2>
		<a data-idGruppo="<%=idGruppo%>" class="linkToCreaListaSpesa" href="#">creane una adesso</a> 
	<%
		} else {
	%><thead>
		<tr>
			<th>&nbsp</th>
			<th>Data Creazione</th>
			<th>Stato</th>
			<th class="pure-hidden-phone">Data Chiusura</th>
			<th class="pure-hidden-phone">Descrizione</th>
		</tr>
	</thead>

	<tbody>
		<%
			for (int i = 0; i < lsg.size(); i++) {
		%>
		<tr data-class="linkToLista" data-idLista="<%=lsg.get(i).getId()%>" data-tableColor="true">
			<td><img src="img/iconaListaSpesa.png" height="20px"
				width="20px"></td>
			<td><%=lsg.get(i).getDataCreazione()%></td>
			<%if(lsg.get(i).isAperta()){%>
			<td><img src="img/listaSpesaAperta.png" height="20px"
				width="20px"></td>
				<td class="pure-hidden-phone">lista aperta</td>
			<%}else{%>
				<img src="img/listaSpesaChiusa.png" height="20px"
				width="20px"></td>
				<td class="pure-hidden-phone"><%=lsg.get(i).getDataChiusura()%></td>
			<%}%>
			<td class="pure-hidden-phone"><%=lsg.get(i).getDescrizione()%></td>
		</tr>
		<%
			}
		%>
		<tr class="linkToCreaListaSpesa" data-tableColor="true">
		<td><img src="img/add.png" height="20px"
				width="20px"></td>
			<td>nuova</td>
			<td></td>
			<td class="pure-hidden-phone"></td>
			<td class="pure-hidden-phone"></td>
		</tr>
		<%
			}
		%>

	</tbody>
</table>