package act_users;

public class Users {

	private String username;
	private String password;
	private String loginType;
	private String country;
	private String language;
	private String top3news;
	private String userLanguage;
	private String languageTestPage;
	private String mainMenu;

	public String getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public String getLanguageTestPage() {
		return languageTestPage;
	}

	public void setLanguageTestPage(String languageTestPage) {
		this.languageTestPage = languageTestPage;
	}

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

	public String getTop3news() {
		return top3news;
	}

	public void setTop3news(String top3news) {
		this.top3news = top3news;
	}

	@Override
	public String toString() {
		return "Username:" + username + ",Password:" + password + ",LoginType:" + loginType;
	}
}
