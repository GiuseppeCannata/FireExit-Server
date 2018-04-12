/**
 * Presenta le funzioni javaScript utilizzate
 */

/**
 * confirmActionForm(e) è utilizzata per il pop-up di conferma per l utente.
 * Se l utente clicca "ok" si attiverà il submit della Form.
 * Al contrario, se l utente clicca "Annulla" si rimmarra sulla pagina corrente
 */
function confirmActionForm(e){
	    	 
   var form = document.getElementById('form');
   var conf = window.confirm("Si e' sicuri di continuare?");
   
   if( conf )
	  form.submit();
   else
	  e.preventDefault() ;  
}

/**
 * back(e,link) è utilizzata per il pop-up di conferma per l utente.
 * 
 */
function back(e,link){
	 
	 var ButtonIndietro = document.getElementById("indietro");
	 var conf = window.confirm("Tornando indietro perderai tutti i dati non salvati. Continuare?");
	 
	 if(conf)
		ButtonIndietro.href = link;
	 else
		e.preventDefault();	 
}

/**
 * elimina(e,link,nome) è utilizzata per il pop-up di conferma per l utente.
 * Viene richiamata dalle ListView e serve per chiedere all untente se davvero ha intenzione di eliminare 
 * l elemnto passato per il paramentro nome
 * 
 */
function elimina(e, link, nome){
	 
	 var ButtonDelete = document.getElementById("delete");
	 var conf = window.confirm("Eliminare "+nome+" ?");
	 
	 if(conf)
		ButtonDelete.href = link;
	 else
		e.preventDefault();	 
}