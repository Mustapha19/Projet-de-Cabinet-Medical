package application.DataBaseLogin;

public class UserLogin {
	private String userName;
	private String password;
	
	public UserLogin() {
		
	}
	
	public UserLogin(String userName, String password) {
		setUserName(userName);
		setPassword(password);
	}


	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
