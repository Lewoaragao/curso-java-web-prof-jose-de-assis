/**
 * Confirmação de exclusão de um contato 
 * @author Leonardo Aragao
 * @param idcon
 */
 
function confirmar(idcon) {
	let resposta = confirm("Confirma a exclusão deste contato?")
	if(resposta === true) {
		// alert(idcon)
		window.location.href = "delete?idcon=" + idcon
	}
}

function confirmar(id_usuario) {
	let resposta = confirm("Confirma a exclusão deste contato?")
	if(resposta === true) {
		// alert(id_usuario)
		window.location.href = "delete?id_usuario=" + id_usuario
	}
}