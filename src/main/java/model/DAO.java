package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** Módulo de conexão *. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";

	/** The password. */
	private String password = "Root@leleo01";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Teste de conexão
	/*
	 * public void testConexao() { try { Connection con = conectar();
	 * System.out.println(con); con.close(); } catch (Exception e) {
	 * System.out.println(e); } }
	 */

	/**
	 * CRUD CREATE *.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato, UsuarioBeans usuario) {
		String create = "insert into contatos(nome, fone, email, id_usuario) values(?, ?, ?, ?)";

		try {
			// Abrir a conexão
			Connection con = conectar();

			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);

			// Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, usuario.getIdUsuario());

			// Executar a query
			pst.executeUpdate();

			// Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from contatos order by nome";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				int idUsuario = rs.getInt(5);

				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email, idUsuario));
			}

			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * CRUD UPDATE *.
	 *
	 * @param contato the contato
	 */
	// Selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read = "select * from contatos where idcon = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				// Setar as variáveis JavaBeans
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				contato.setIdUsuario(rs.getInt(5));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	// Editar o contato
	public void alterarContato(JavaBeans contato) {
		String update = "update contatos set nome = ?, fone = ?, email = ? where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);

			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());

			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD DELETE *.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD CREATE *.
	 *
	 * @param usuario the usuario
	 */
	public void inserirUsuario(UsuarioBeans usuario) {
		String create = "insert into usuarios(nome_usuario, senha_usuario, email_usuario) values(?, ?, ?)";

		try {
			// Abrir a conexão
			Connection con = conectar();

			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);

			// Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, usuario.getNomeUsuario());
			pst.setString(2, usuario.getSenhaUsuario());
			pst.setString(3, usuario.getEmailUsuario());

			// Executar a query
			pst.executeUpdate();

			// Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<UsuarioBeans> listarUsuarios() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<UsuarioBeans> usuarios = new ArrayList<>();

		String read = "select * from usuarios order by nome_usuario";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			// O laço abaixo será executado enquanto houver usuarios
			while (rs.next()) {
				String idUsuario = rs.getString(1);
				String nomeUsuario = rs.getString(2);
				String senhaUsuario = rs.getString(3);
				String emailUsuario = rs.getString(4);

				// Populando o ArrayList
				usuarios.add(new UsuarioBeans(idUsuario, nomeUsuario, senhaUsuario, emailUsuario));
			}

			con.close();
			return usuarios;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Boolean logarUsuario(UsuarioBeans usuario) {

		String read = "select * from usuarios where nome_usuario = ? and senha_usuario = ?";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);

			pst.setString(1, usuario.getNomeUsuario());
			pst.setString(2, usuario.getSenhaUsuario());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				usuario.setIdUsuario(rs.getString(1));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public ArrayList<JavaBeans> listarContatosUsuario(UsuarioBeans usuario) {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "select * from contatos where id_usuario = ? order by nome";

		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);

			pst.setString(1, usuario.getIdUsuario());

			ResultSet rs = pst.executeQuery();

			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				int idUsuario = rs.getInt(5);

				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email, idUsuario));
			}

			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void inserirContatoUsuario(UsuarioBeans usuario) {
		String create = "insert into usuarios(nome_usuario, senha_usuario, email_usuario, id_usuario) values(?, ?, ?, ?)";

		try {
			// Abrir a conexão
			Connection con = conectar();

			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);

			// Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, usuario.getNomeUsuario());
			pst.setString(2, usuario.getSenhaUsuario());
			pst.setString(3, usuario.getEmailUsuario());
			pst.setString(4, usuario.getIdUsuario());

			// Executar a query
			pst.executeUpdate();

			// Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}