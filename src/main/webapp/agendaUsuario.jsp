<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@ SuppressWarnings ("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatosUsuario");
String idUsuario = (String) request.getAttribute("idUsuario");
String nomeUsuario = (String) request.getAttribute("nomeUsuario");
/* 
for(int i = 0; i < lista.size(); i++) {
		out.println(lista.get(i).getIdcon());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getFone());
		out.println(lista.get(i).getEmail()); 
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
		<h1>Agenda de contatos do (<%=idUsuario%>) <%=nomeUsuario %> </h1><br>
		<div><a href="login.jsp" class="botao2">Trocar usuário</a>
		<a href="novo.html" class="botao1">Novo contato</a></div>
		
		<table id="tabela">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>Email</th>
					<th>ID Usuário</th>
					<th>Opções</th>
				</tr>
			</thead>

			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><%=lista.get(i).getIdUsuario()%></td>
					<td>
					<a href="select?idcon=<%=lista.get(i).getIdcon()%>"
						class="botao1">Editar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"
						class="botao2">Excluir</a>
						</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
	
	<script src="scripts/confirmador.js"></script>
</body>
</html>