package entities;

public class Utente 
{
	private String email;
	private String nickname;
	private String password;
	private int eta;
	private String sesso;
	public Utente(String email, String nickname, String password, int eta, String sesso) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.eta = eta;
		this.sesso = sesso;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	
}
