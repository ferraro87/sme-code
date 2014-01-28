//document.ontouchmove = function(e) {
//	e.preventDefault()
//};

$(document).ready(function() {
	UI();
	
	$("#appContainer").load("pages/elencoGruppiUtente.jsp");

	$("#logInButton").click(function() {
		logIn();
	});

	$("#logOutButton").click(function() {
		logOut();
	});

	$("#creaAccountButton").click(function() {
		toggleRegistration();
	});

	$(".goToModificaProfilo").click(function() {
		$("#profileInformation").slideToggle("slow");
		$("#appContainer").load("pages/modificaProfilo.jsp");
	});

});
// FINE READY

function togglePartecipazioneProdottoLista(idProdottoLista,partecipoIo,idLista){
	$.ajax({
		url : "session/TogglePartecipazioneProdottoLista",
		data : {
			idProdottoLista: idProdottoLista,
			partecipoIo: partecipoIo
		},
		type : "POST",
		success : function() {
			$("#appContainer").load("pages/listaSpesa.jsp", { idLista: idLista});
		},
		error : function() {
			alert("errore di comunicazione");
		}
	});
}

function abbandonaGruppo(idGruppo){
	$.ajax({
		url : "session/AbbandonaGruppo",
		data : {
			idGruppo: idGruppo
		},
		type : "POST",
		success : function() {
			location.reload();
		},
		error : function() {
			alert("errore di comunicazione");
		}
	});
}

function aggiungiUtenteToGruppo(emailNuovo,idGruppo){
	if(emailNuovo!=""){
		$.ajax({
			url : "session/AggiungiUtenteToGruppo",
			data : {
				emailNuovoUtente : emailNuovo,
				idGruppo: idGruppo
			},
			type : "POST",
			success : function(text) {
				alert(text);
				$("#aggiungiUtenteForm").slideToggle("slow");
			},
			error : function() {
				alert("errore di comunicazione");
			}
		});
	}else{
		alert("scrivi l'email dell'utente che vuoi aggiungere al gruppo");
	}
}

function aggiungiProdottoToLista(idListaSpesa,nomeNuovoProdotto){
	$.ajax({
		url : "session/AggiungiProdottoToLista",
		data : {
			idLista : idListaSpesa,
			nomeNuovoProdotto: nomeNuovoProdotto
		},
		type : "POST",
		success : function() {
			$("#appContainer").load("pages/listaSpesa.jsp", { idLista: idListaSpesa});
		},
		error : function() {
			alert("errore di comunicazione");
		}
	});
}

function creaListaSpesa(idGruppo){
	$.ajax({
		url : "session/CreaListaSpesa",
		data : {
			idGruppo : idGruppo
		},
		type : "POST",
		success : function(text) {
			$("#appContainer").load("pages/listaSpesa.jsp", { idLista: text});
		},
		error : function() {
			alert("errore di comunicazione");
		}
	});
}

function modificaProfilo() {
	$form = $("#modificaProfiloForm");
	$nomeNuovo = $form.find("#nomeNuovo").val();
	$passwordUtente = $form.find("#passwordVecchia").attr("data-vp");
	$passwordVecchia = $form.find("#passwordVecchia").val();
	$passwordNuova = $form.find("#passwordNuova").val();
	$confermaPasswordNuova = $form.find("#confermaPasswordNuova").val();
	$fotoNuova = $form.find("#fotoNuova").val();
	if ($passwordVecchia == ""){
		alert("...devi scrivere la password corrente...");
		return;
	}
	if ($passwordNuova != $confermaPasswordNuova){
		alert("...le due password devono coincidere...");
		$form.find("#passwordNuova").css("color","red");
		$form.find("#confermaPasswordNuova").css("color","red");
		return;
	}
	if ($passwordNuova == "" && $confermaPasswordNuova == "") {
		$passwordNuova = $passwordVecchia;
	}
	$.ajax({
		url : "session/ModificaProfilo",
		data : {
			nomeNuovo : $nomeNuovo,
			passwordNuova : $passwordNuova,
			fotoNuova : $fotoNuova
		},
		type : "POST",
		success : function() {
			location.reload();
		},
		error : function() {
			alert("errore di comunicazione");
		}
	});
}

function creaGruppoSpesa() {
	$form = $("#formCreaGruppo");
	$nome = $form.find("#nomeGruppoCreazione").val();
	$descrizione = $form.find("#descrizioneGruppoCreazione").val();
	if ($nome == "") {
		alert("...ops! manca il nome del gruppo...");
	} else {
		$.ajax({
			url : "session/CreaGruppoSpesa",
			data : {
				nome : $nome,
				descrizione : $descrizione
			},
			type : "POST",
			success : function(text) {
				location.reload();
			},
			error : function() {
				alert("errore di comunicazione");
			}
		});
	}
}

function registrazione() {
	$form = $("#formRegistrazione");
	$email = $form.find("#emailRegistrazione").val();
	$password = $form.find("#passwordRegistrazione").val();
	$confermaPassword = $form.find("#confermaPasswordRegistrazione").val();
	$nome = $form.find("#nomeRegistrazione").val();
	if ($email == "" || $password == "" || $password != $confermaPassword
			|| $nome == "") {
		alert("...ops! manca qualcosa...");
	} else {
		$.ajax({
			url : "session/Registrazione",
			data : {
				email : $email,
				password : $password,
				nome : $nome
			},
			type : "POST",
			success : function(text) {
				if (text != "") {
					alert(text);
				} else {
					alert("registrazione effettuata con successo");
					location.reload();
				}
			},
			error : function() {
				alert("errore di comunicazione");
			}
		});
	}
}

function toggleRegistration() {
	$container = $("#container");
	$container.find("#logInPage").slideToggle();
	$container.find("#appPage").slideToggle();
	$container.find("#registrazionePage").slideToggle();
}

function logOut() {
	var result = window.confirm("sei sicuro di voler uscire?");
	if (result) {
		$.ajax({
			url : "session/LogOut",
			type : "POST",
			success : function(text) {
				location.reload();
			},
			error : function() {
				alert("errore di comunicazione");
			}
		});
	}
}

function logIn() {

	var email = $("#emailLogInInput").val();
	var password = $("#passwordLogInInput").val();

	if (email == null) {
		alert("manca email");
	} else if (password == null) {
		alert("manca password");
	} else {
		$.ajax({
			url : "session/LogIn",
			data : {
				email : email,
				password : password
			},
			type : "POST",
			success : function(text) {
				if (text != "") {
					alert(text);
				} else {
					location.reload();
				}
			},
			error : function() {
				alert("errore di comunicazione");
			}
		});
	}

}

function UI() {
	(function(window, document) {

		var layout = document.getElementById('layout'), menu = document
				.getElementById('menu'), menuLink = document
				.getElementById('menuLink');

		function toggleClass(element, className) {
			var classes = element.className.split(/\s+/), length = classes.length, i = 0;

			for (; i < length; i++) {
				if (classes[i] === className) {
					classes.splice(i, 1);
					break;
				}
			}
			// The className is not found
			if (length === classes.length) {
				classes.push(className);
			}

			element.className = classes.join(' ');
		}

		if (menuLink != null) {
			menuLink.onclick = function(e) {
				var active = 'active';

				e.preventDefault();
				toggleClass(layout, active);
				toggleClass(menu, active);
				toggleClass(menuLink, active);
			};
		}
	}(this, this.document));

}