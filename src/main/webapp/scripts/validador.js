/**
 * Validação de formulário
 * @author Leonardo Aragão
 */

function validar() {
	// alert('teste')

	let nome = frmContato.nome.value
	let fone = frmContato.fone.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmContato.nome.focus()
		return false
	} else if (fone === "") {
		alert('Preencha o campo Fone')
		frmContato.fone.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}

function validarLoginUsuario() {
	// alert('teste')

	let nome = frmLogin.nome.value
	let senha = frmLogin.senha.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmLogin.nome.focus()
		return false
	} else if (senha === "") {
		alert('Preencha o campo Senha')
		frmLogin.senha.focus()
		return false
	} else {
		document.forms["frmLogin"].submit()
	}
}

function validarCadastroUsuario() {
	// alert('teste')

	let nome = frmCadastro.nome.value
	let senha = frmCadastro.senha.value
	let email = frmCadastro.email.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmCadastro.nome.focus()
		return false
	} else if (senha === "") {
		alert('Preencha o campo Senha')
		frmCadastro.senha.focus()
		return false
	} else if (email === "") {
		alert('Preencha o campo Email')
		frmCadastro.email.focus()
		return false
	} else {
		document.forms["frmCadastro"].submit()
	}
}