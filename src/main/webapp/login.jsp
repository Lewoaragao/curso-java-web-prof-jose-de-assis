<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.UsuarioBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@ SuppressWarnings ("unchecked")
ArrayList<UsuarioBeans> lista = (ArrayList<UsuarioBeans>) request.getAttribute("usuarios");

/* for(int i = 0; i < lista.size(); i++) {
		out.println(lista.get(i).getIdUsuario());
		out.println(lista.get(i).getNomeUsuario());
		out.println(lista.get(i).getSenhaUsuario());
		out.println(lista.get(i).getEmailUsuario()); 
} */
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="container">
		<form name="frmLogin" action="logar">
			
			<label>Nome</label>
			<input type="text" name="nome" class="caixa1">
			<br>
			<label>Senha</label>
			<input type="text" name="senha" class="caixa1">
			<br>
			<input type="button" value="Entrar" class="botao1"
				onclick="validarLoginUsuario()">
		</form>
		
		<%-- <h1>Lista de usuários</h1><br>
		<table id="tabela">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>Email</th>
					<th>Opções</th>
				</tr>
			</thead>

			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<td><%=lista.get(i).getIdUsuario()%></td>
					<td><%=lista.get(i).getNomeUsuario()%></td>
					<td><%=lista.get(i).getSenhaUsuario()%></td>
					<td><%=lista.get(i).getEmailUsuario()%></td>
					<td>
					<a href="select?id_usuario=<%=lista.get(i).getIdUsuario()%>"
						class="botao1">Editar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getIdUsuario()%>)"
						class="botao2">Excluir</a>
						</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table> --%>
	</div>
	
	<!-- <script src="scripts/confirmador.js"></script> -->
	<script type="text/javascript" src="scripts/validador.js"></script>
</body>
</html>