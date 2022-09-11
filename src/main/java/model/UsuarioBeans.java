package model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class UsuarioBeans {
	
	private String idUsuario;
	private String nomeUsuario;
	private String senhaUsuario;
	private String emailUsuario;
	
	public UsuarioBeans() {
		super();
	}

	public UsuarioBeans(String idUsuario, String nomeUsuario, String senhaUsuario, String emailUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.senhaUsuario = senhaUsuario;
		this.emailUsuario = emailUsuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	
}