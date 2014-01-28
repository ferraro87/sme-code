<%@page import="it.unical.ingsw.splitMyExpense.domain.*"%>

<style>
<!--
#modificaProfiloForm input{
width: 100%
}
-->
</style>

<script>
function salvaPressed(){
		modificaProfilo();
	}
function annullaPressed(){
	$("#profileInformation").slideToggle("slow");
	$("#appContainer").load("pages/elencoGruppiUtente.jsp");
}
$("#passwordNuova").click(function(){
	$(this).css("color","black");
});
$("#confermaPasswordNuova").click(function(){
	$(this).css("color","black");
});
</script>

<div class="modificaProfiloPage">
	<form id="modificaProfiloForm" class="pure-form">
		<fieldset>
			<legend>Modifica il tuo Profilo</legend>

			<fieldset class="pure-group">
				<input id="nomeNuovo" type="text" class="pure-input-1-2"
					value="<%=((Utente) session.getAttribute("utente")).getNome()%>" placeholder="Nome">
			</fieldset>

			<fieldset class="pure-group">
					<input id="passwordVecchia" type="password"
					data-vp="<%=((Utente) session.getAttribute("utente")).getPassword()%>" class="pure-input-1-2" placeholder="Password Corrente"> 
					<input
					id="passwordNuova" type="password" class="pure-input-1-2"
					placeholder="Nuova Password"> 
					<input
					id="confermaPasswordNuova" type="password"
					class="pure-input-1-2" placeholder="Conferma Nuova Password">
			</fieldset>
			
			<fieldset class="pure-group">
				<input id="fotoNuova" type="file" class="pure-input-1-2"
					value="<%=((Utente) session.getAttribute("utente")).getFoto()%>" placeholder="Foto">
			</fieldset>

			</label> <a id="modificaButton"
				class="pure-button" style="background: rgb(28,184,65)"
				onclick="salvaPressed();">Salva</a>
				
				<a id="annullaButton"
				class="pure-button" style="background: rgb(202,60,60)"
				onclick="annullaPressed();">Annulla</a>
	</form>

</div>