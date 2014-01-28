<script type="text/javascript">
function creaPressed(){
	creaGruppoSpesa();
}
</script>

<form id="formCreaGruppo" class="pure-form">

	<fieldset>
		<legend>Crea un nuovo Gruppo Spesa</legend>
	</fieldset>
	<fieldset class="pure-group">
		<input id="nomeGruppoCreazione" type="text" class="pure-input-1-2"
			placeholder="...scrivi il nome del gruppo spesa...">
	</fieldset>
	<fieldset class="pure-group">
		<textarea id="descrizioneGruppoCreazione" type="text"
			class="pure-input-1-2"
			placeholder="...scrivi la descrizione del gruppo spesa..." rows="10"
			style="width: 100%"></textarea>
	</fieldset>
	<a id="creaGruppoSpesaButton"
		class="pure-button pure-input-1-2 pure-button-primary" onclick="creaPressed();">Crea</a>

</form>