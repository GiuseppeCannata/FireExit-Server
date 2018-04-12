/**
 * Presenta le funzioni javaScript utilizzate
 */

function confirmActionForm(e){
	    	 
   var form = document.getElementById('form');
   var conf = window.confirm("Si e' sicuri di continuare?");
   
   if( conf )
	  form.submit();
   else
	  e.preventDefault() ;  
}
	     
function back(e,link){
	 
	 var ButtonIndietro = document.getElementById("indietro");
	 var conf = window.confirm("Tornando indietro perderai tutti i dati non salvati. Continuare?");
	 
	 if(conf)
		ButtonIndietro.href = link;
	 else
		e.preventDefault();	 
}

function elimina(e, link, nome){
	 
	 var ButtonDelete = document.getElementById("delete");
	 var conf = window.confirm("Eliminare "+nome+" ?");
	 
	 if(conf)
		ButtonDelete.href = link;
	 else
		e.preventDefault();	 
}