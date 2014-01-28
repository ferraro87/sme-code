<script>
	function tornaToLogIn() {
		$container = $("#container");
		$container.find("#logInPage").slideToggle();
		$container.find("#appPage").slideToggle();
		$container.find("#registrazionePage").slideToggle();
	}
	function registrazionePressed(){
		registrazione();
	}
</script>
<div class="registrazione">
<form id="formRegistrazione" class="pure-form">

<center><img id="logoLogin" src="img/logo.png"></center>
		<fieldset>
			<legend>Registrati</legend>

	<fieldset class="pure-group">
		<input id="emailRegistrazione" type="email" class="pure-input-1-2"
			placeholder="Email"> <input id="passwordRegistrazione"
			type="password" class="pure-input-1-2" placeholder="Password" >
		<input id="confermaPasswordRegistrazione" type="password"
			class="pure-input-1-2" placeholder="Conferma Password">
	</fieldset>

	<fieldset class="pure-group">
		<input id="nomeRegistrazione" type="text" class="pure-input-1-2"
			placeholder="Nome">
	</fieldset>

	</label> <a id="registrazioneButton"
		class="pure-button pure-input-1-2 pure-button-primary" onclick="registrazionePressed();">Registrati</a>

</form>

</div>
<center>
	<a id="creaAccountButton" href="#" onclick="tornaToLogIn();">torna
		indietro</a>
</center>