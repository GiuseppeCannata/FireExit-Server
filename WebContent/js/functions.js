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
	 
	 var ButtonDelete = document.getElementById("delete"+nome);
	 var conf = window.confirm("Eliminare "+nome+" ?");
	 
	 if(conf)
		ButtonDelete.href = link;
	 else
		e.preventDefault();	 
}

function richiestaNumPesi(e,link){
	 
	var ButtonInserisci = document.getElementById("inserisciArco");
	 
	var nPesi = prompt ("Prima di inserire i dati relativi all arco la preghiamo di dirci quanti pesi presenta");
     
    if(nPesi !=null & nPesi != "")
    	ButtonInserisci.href = link+"&nPesi="+nPesi; 
    else
		if(nPesi == null)
	        e.preventDefault();
		else
			//if (nPesi == "") 
		        if(window.confirm("Dati errati. Riprovare?"))
		           richiestaNumPesi(e,link);
		        else
		           e.preventDefault();
		        	

    	
}

