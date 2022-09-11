package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;
import model.UsuarioBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report",
		"/insertUsuario", "/login", "/logar", "/usuarios", "/novoContato", "/selectUsuario" })
public class Controller extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	DAO dao = new DAO();

	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/** The contato. */
	UsuarioBeans usuario = new UsuarioBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Teste de conexão
		// dao.testConexao();

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			adicionarContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else if (action.equals("/insertUsuario")) {
			adicionarUsuario(request, response);
		} else if (action.equals("/login")) {
			response.sendRedirect("login.html");
		} else if (action.equals("/usuarios")) {
			usuarios(request, response);
		} else if (action.equals("/logar")) {
			logar(request, response);
		}  else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contatos.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");

		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Teste de recebimento da lista
		/*
		 * for(int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getIdcon());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getFone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */

		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	// Novo contato
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulário
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("fone"));
		// System.out.println(request.getParameter("email"));

		// Setar as variáveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Invocar o método inserirContato passando o objeto contato
		dao.inserirContato(contato, usuario);

		// Redirecionar para o documento agenda.jsp
//		response.sendRedirect("agendaUsuario.jsp");
		contatosUsuario(request, response, usuario);
	}

	/**
	 * Listar contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado
		String idcon = request.getParameter("idcon");

		// Setar a variável JavaBeans
		contato.setIdcon(idcon);

		// Executar o método selecionar contato (DAO)
		dao.selecionarContato(contato);

		// Teste de recebimento
		/*
		 * System.out.println(contato.getIdcon());
		 * System.out.println(contato.getNome()); System.out.println(contato.getFone());
		 * System.out.println(contato.getEmail());
		 */

		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		request.setAttribute("id_usuario", contato.getIdUsuario());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento
		/*
		 * System.out.println(request.getParameter("idcon"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("email"));
		 */

		// Setar as variáveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Executar o método alterarContato
		dao.alterarContato(contato);

		// Redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	/**
	 * Remover contato.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	// Remover um contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato a ser excluido (validador.js)
		String idcon = request.getParameter("idcon");
		System.out.println(idcon);

		// Setar a variável idcon JavaBeans
		contato.setIdcon(idcon);

		// Executar o método deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);

		// Redirecionar para o documento agenda.jsp (atualizando as alterações)
		contatosUsuario(request, response, usuario);
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	// Gerar relatório em pdf
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();

		try {
			// Tipo de conteúdo
			response.setContentType("apllication/pdf");

			// Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");

			// Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());

			// Abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de contatos: "));
			documento.add(new Paragraph(" "));

			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);

			// Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			// Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}

			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

	// Novo usuário
	protected void adicionarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste de recebimento dos dados do formulário
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("fone"));
		// System.out.println(request.getParameter("email"));

		// Setar as variáveis JavaBeans
		usuario.setNomeUsuario(request.getParameter("nome"));
		usuario.setSenhaUsuario(request.getParameter("senha"));
		usuario.setEmailUsuario(request.getParameter("email"));

		// Invocar o método inserirUsuario passando o objeto contato
		dao.inserirUsuario(usuario);

		// Redirecionar para o documento login.jsp
		response.sendRedirect("login.jsp");
	}

	// Logar usuário
	protected void logar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		usuario.setNomeUsuario(request.getParameter("nome"));
		usuario.setSenhaUsuario(request.getParameter("senha"));

		// Invocar o método logarUsuario passando o objeto usuario
		Boolean result = dao.logarUsuario(usuario);

		if (result) {
			contatosUsuario(request, response, usuario);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos de um usuário
	protected void contatosUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioBeans usuario)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");

		ArrayList<JavaBeans> lista = dao.listarContatosUsuario(usuario);
		String idUsuario = usuario.getIdUsuario();
		String nomeUsuario = usuario.getNomeUsuario();

		// Teste de recebimento da lista
		/*
		 * for(int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getIdcon());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getFone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */

		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatosUsuario", lista);
		request.setAttribute("idUsuario", idUsuario);
		request.setAttribute("nomeUsuario", nomeUsuario);
		RequestDispatcher rd = request.getRequestDispatcher("agendaUsuario.jsp");
		rd.forward(request, response);
	}

	// Listar usuarios
	protected void usuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");

		ArrayList<UsuarioBeans> lista = dao.listarUsuarios();

		// Teste de recebimento da lista
		/*
		 * for(int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getIdcon());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getFone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */

		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("usuarios", lista);
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
}