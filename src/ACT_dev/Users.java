package ACT_dev;


public class Users {

	private String username;
	private String password;
	private String loginType;
	private String country;
	private String language;
	
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String login_type) {
		this.loginType = login_type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString(){
		return "Username:"+username+",Password:"+password+",LoginType:"+loginType;
	}
}
