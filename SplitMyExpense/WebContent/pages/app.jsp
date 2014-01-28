<%@page import="it.unical.ingsw.splitMyExpense.domain.*"%>

<div id="layout">
	<a href="#menu" id="menuLink" class="pure-menu-link"> <span></span>
	</a>
	<div id="menu">
		<div class="pure-menu pure-menu-open">
			<a class="pure-menu-heading" href="#">MENU</a>

			<ul>
				<li><a href="#" onclick="goToHome()">Home</a></li>
				<li><a class="linkToCreaGruppoSpesa" href="#">Crea Gruppo
						Spesa</a></li>
				<li><a href="#">LinkA</a></li>
				<li class="pure-menu-selected menu-item-divided"><a href="#">ActiveLink</a>
				</li>
				<li><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>

				<li class="menu-item-divided"><a href="#">Divisorio</a></li>
			</ul>
			<a class="pure-menu-heading" href="#">ACCOUNT</a>
			<ul>
				<li><a class="goToModificaProfilo" href="#">Modifica
						Profilo</a></li>
				<li><a href="#" id="logOutButton">Esci</a></li>
			</ul>

		</div>
	</div>

	<div id="main">
		
		<div class="content">

			<span id="profileInformation"><table>
				<tr>
					<td><img style="padding: 20px"
						src="<%=((Utente) session.getAttribute("utente")).getFoto()%>"
						height="50px" width="50px"></td>
					<td>
						<h2 style="padding: 0; margin: 0" class="content-subhead">
							Ciao
							<%=((Utente) session.getAttribute("utente")).getNome()%></h2> <a
						class="goToModificaProfilo" href="#"
						style="text-decoration: none; font-weight: bolder">Modifica
							Profilo</a>
					</td>
				</tr>


			</table></span>
			<center>
				<span id="appContainer">
				</span>
			</center>
		</div>
	</div>
</div>
