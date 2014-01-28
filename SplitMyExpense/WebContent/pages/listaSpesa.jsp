<%@page
	import="it.unical.ingsw.splitMyExpense.technicalServices.aggregateData.*"%>
<%@page
	import="it.unical.ingsw.splitMyExpense.technicalServices.dataAccess.*"%>
<%@page import="it.unical.ingsw.splitMyExpense.domain.*"%>
<%@page import="java.util.List"%>
<%DataAccess dataAccess = new DataAccess(
		ConnectionFactory.getConnection()); %>

<script>
$parent=$("#nuovoProdottoButton");
$parent.find("#piu").click(function(){
	$parent.find("#row1").html('<input id="nuovoProdottoInput" type="text" size=10>');
	$parent.find("#row2").html("<i id='nuovoProdottoSalva' style='color: green' class='fa fa-shopping-cart fa-2x nuovoProdottoSalva'></i>");
	$parent.find("#row3").html("<i id='nuovoProdottoSalva' style='color: green' class='fa fa-shopping-cart fa-2x nuovoProdottoSalva'></i>");
	$parent.find("#row4").html("<i id='nuovoProdottoAnnulla' style='color: red' class='fa fa-trash-o fa-2x'></i>");
$parent.find(".nuovoProdottoSalva").click(function(){	
	nuovoProdotto=$parent.find("#nuovoProdottoInput").val();
	if(nuovoProdotto!=""){
		aggiungiProdottoToLista(<%=request.getParameter("idLista")%>,nuovoProdotto);
	}else{
		alert("scrivi il nome del nuovo prodotto");
	}
});
$parent.find("#nuovoProdottoAnnulla").click(function(){
	$("#appContainer").load("pages/listaSpesa.jsp", { idLista: <%=request.getParameter("idLista")%>});
});
});
$(".togglePartecipazioneProdottoLista").click(function(){
	idProdottoLista=$(this).attr("data-idProdottoLista");
	partecipoIo=$(this).attr("data-partecipoIo");
	togglePartecipazioneProdottoLista(idProdottoLista,partecipoIo,<%=request.getParameter("idLista")%>);
});
$(".apriPartecipazioniProdottoLista").click(function(){
	htmlLista=$(this).parent().find(".pure-hidden-phone").html();
	pop=$("#overlay");
	pop.html("<center>"+htmlLista);
	pop.toggle();
	pop.append("<br><a id='chiudiOverlay' class='pure-button'>Chiudi</a></center>");
	pop.find("#chiudiOverlay").click(function(){
		pop.toggle();
	});
});
</script>

<style>
	#overlay{
		padding-top: 20%;
		position: absolute;
  		top: 0%;
 		left: 0%;
  		width: 100%;
  		height: 100%;
  		z-index: 10;
  		background-color: rgba(0,0,0,0.8);  
	}
	#overlay img{
		margin: 5px;
		width: 50px;
		height: 50px;
	}
</style>

<span id="overlay" style="display: none"></span>

<div class="header">

	<button class="pure-button" onclick="goToHome()"> <i class="fa fa-home"></i><span
		id="bottonTitle">Home</span>
	</button>
	<button class="pure-button">
		<i class="fa fa-bar-chart-o"></i><span id="bottonTitle">Stat</span>
	</button>
	<button class="pure-button">
		<i class="fa fa-list-alt"></i><span id="bottonTitle">List</span></span>
	</button>
	<button class="pure-button linkToCreaListaSpesa"> <i
		class="fa fa-plus-square-o"></i><span id="bottonTitle">Crea
			Lista Spesa</span>
	</button>

</div>
<br>

<%	ListaSpesaAggregata lsa = dataAccess.getListaSpesaAggregata(Integer
			.parseInt(request.getParameter("idLista")));
%>

<div>
	<h2>
		<img src="img\clock.png" width="20px">&nbsp;<%=lsa.getListaSpesa().getDataCreazione()%></h2>
	<h4>

		<%
			if (lsa.getListaSpesa().isAperta()) {
		%>
		<img src="img\listaSpesaAperta.png" width="20px">&nbsp;Aperta&nbsp;-
		<%
			} else {
		%>
		<img src="img\listaSpesaChiusa.png" width="20px">&nbsp;Chiusa
		il&nbsp;<%=lsa.getListaSpesa().getDataChiusura()%>
		<%
			}
		%>
		<img src="img\info.png" width="20px">&nbsp;<%=lsa.getListaSpesa().getDescrizione()%></h4>
</div>

<table class="pure-table pure-table-horizontal">

	<thead>
		<tr>
			<th>nome</th>
			<th class="pure-hidden-phone">Partecipanti</th>
			<th class="pure-hidden-desktop pure-hidden-tablet">Part.</th>
			<th>Tu</th>
		</tr>
	</thead>

	<tbody>
		<%
			for (Prodotto p : lsa.getListaProdotti()) {
		%>
		<tr data-idProdotto="<%=p.getId()%>" data-tableColor="true">

			<td><%=p.getNome()%></td>

			<td class="pure-hidden-phone">
				<%
					boolean partecipoIo = false;
						for (Utente u : lsa.getUtentiAssociati(p)) {
							if (u.getId() == ((Utente) session.getAttribute("utente"))
									.getId())
								partecipoIo = true;
				%> <img src="<%=u.getFoto()%>"
				title="<%=u.getNome()%>" height="20px" width="20px"> <%
 	}
 %>
			</td>

			<td class="pure-hidden-desktop pure-hidden-tablet apriPartecipazioniProdottoLista"><i
				class="fa fa-group fa-2x"></i></td>

			<td><i data-idProdottoLista="<%=p.getId()%>" data-partecipoIo="<%=partecipoIo%>" class="togglePartecipazioneProdottoLista fa fa-thumbs-<%if(partecipoIo){%>up fa-2x" style='color: #1f8dd6;'<%}else{%>down fa-2x" style='color: red;'<%}%>></i></td>
		</tr>
		<%
			}
		%>

		<tr id="nuovoProdottoButton" data-tableColor="true">
			<td id="row1" class="pure-form"><img id="piu" src="img/add.png" height="20px" width="20px"></td>
			<td id="row2" class="pure-hidden-phone"></td>
			<td id="row3" class="pure-hidden-desktop pure-hidden-tablet"></td>
			<td id="row4"></td>
		</tr>

	</tbody>

</table>
