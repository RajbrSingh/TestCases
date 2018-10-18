package act_users;

public class Users {

	private String username;
	private String password;
	private String loginType;
	private String country;
	private String language;
	private String role;
	private String contentTypes;
	private String reports;
	private String top3news;
	private String userLanguage;
	private String languageTestPage;
	private String contentOnPage;
	private String mainMenu;
	private String footerMenu;
	private String sidebarMenu;
	private String adminMenu;
	private String defaultBU;
	private String BUNewsWithDefault;
	private String changeBU;
	private String expectedBUNews;
	private String contentOutOfScope;
	private String contentWithDiffRole;

	public String getContentOutOfScope() {
		return contentOutOfScope;
	}

	public void setContentOutOfScope(String contentOutOfScope) {
		this.contentOutOfScope = contentOutOfScope;
	}

	public String getContentOnPage() {
		return contentOnPage;
	}

	public void setContentOnPage(String contentOnPage) {
		this.contentOnPage = contentOnPage;
	}

	public String getFooterMenu() {
		return footerMenu;
	}

	public void setFooterMenu(String footerMenu) {
		this.footerMenu = footerMenu;
	}

	public String getSidebarMenu() {
		return sidebarMenu;
	}

	public void setSidebarMenu(String sidebarMenu) {
		this.sidebarMenu = sidebarMenu;
	}

	public String getContentWithDiffRole() {
		return contentWithDiffRole;
	}

	public void setContentWithDiffRole(String contentWithDiffRole) {
		this.contentWithDiffRole = contentWithDiffRole;
	}

	public String getReports() {
		return reports;
	}

	public void setReports(String reports) {
		this.reports = reports;
	}

	public String getContentTypes() {
		return contentTypes;
	}

	public void setContentTypes(String contentTypes) {
		this.contentTypes = contentTypes;
	}

	public String getAdminMenu() {
		return adminMenu;
	}

	public void setAdminMenu(String adminMenu) {
		this.adminMenu = adminMenu;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDefaultBU() {
		return defaultBU;
	}

	public void setDefaultBU(String defaultBU) {
		this.defaultBU = defaultBU;
	}

	public String getBUNewsWithDefault() {
		return BUNewsWithDefault;
	}

	public void setBUNewsWithDefault(String bUNewsWithDefault) {
		BUNewsWithDefault = bUNewsWithDefault;
	}

	public String getChangeBU() {
		return changeBU;
	}

	public void setChangeBU(String changeBU) {
		this.changeBU = changeBU;
	}

	public String getExpectedBUNews() {
		return expectedBUNews;
	}

	public void setExpectedBUNews(String expectedBUNews) {
		this.expectedBUNews = expectedBUNews;
	}

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
