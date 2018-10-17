package act_master;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import act_default_bu.DefaultBU;
import act_language.Language;
import act_login.UserLogin;
import act_logout.Logout;
import act_menu.Menu;
import act_news.News;
import act_roles.Roles;
import act_users.ReadCSV;

public class Master {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//
		// Creating new file for Logs
		//
		FileHandler handler = new FileHandler("Automated-tests.log");
		SimpleFormatter formatterTxt = new SimpleFormatter();
		handler.setFormatter(formatterTxt);
		Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
		logger.addHandler(handler);
		logger.info("Begining Test Script\n");

		//
		// Initilizing Chromedriver
		//
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		WebDriver driver = new ChromeDriver();
		try {
			//
			// Creating Variables for Username and Password then retriving the values from
			// the CSV
			//
			String username = "";
			String password = "";
			String loginType = "";
			String country = "";
			String language = "";
			String role = "";
			String contentTypes = "";
			String reports = "";
			String top3news = "";
			String userLanguage = "";
			String languageTestPage = "";
			String mainMenu = "";
			String footerMenu = "";
			String sidebarMenu = "";
			String adminMenu = "";
			String env = "stg";
			String baseURL = "";
			String defaultBU = "";
			String BUNewsWithDefault = "";
			String changeBU = "";
			String expectedBUNews = "";
			String contentOutOfScope = "";
			String contentWithDiffRole = "";

			if (env.equals("dev")) {
				baseURL = "https://innercircleglobal-dev.circlek.com";
			} else if (env.equals("stg")) {
				baseURL = "https://innercircleglobal-stg.circlek.com";
			} else if (env.equals("prod")) {
				baseURL = "https://innercircleglobal.circlek.com";
			}

			ReadCSV readCSV = new ReadCSV();
			String[][] userValues = readCSV.readCSV();
			UserLogin userLogin = new UserLogin();
			boolean loginStatus = false;

			for (int i = 0; i < userValues.length; i++) {
				username = userValues[i][0];
				password = userValues[i][1];
				loginType = userValues[i][2];
				country = userValues[i][3];
				language = userValues[i][4];
				role = userValues[i][5];
				contentTypes = userValues[i][6];
				reports = userValues[i][7];
				top3news = userValues[i][8];
				userLanguage = userValues[i][9];
				languageTestPage = userValues[i][10];
				mainMenu = userValues[i][11];
				footerMenu = userValues[i][12];
				sidebarMenu = userValues[i][13];
				adminMenu = userValues[i][14];
				defaultBU = userValues[i][15];
				BUNewsWithDefault = userValues[i][16];
				changeBU = userValues[i][17];
				expectedBUNews = userValues[i][18];
				contentOutOfScope = userValues[i][19];
				contentWithDiffRole = userValues[i][20];
				logger.info("-----------------------------------\n");
				logger.info("------------" + username + "-------------\n");

				// login
				logger.info("*****LOGIN TEST***** " + "\n");
				loginStatus = UserLogin.userLogin(driver, logger, username, password, loginType, country, language,
						baseURL);
				// for successful login
				if (loginStatus) {
					// Top 3 dashboard news
					logger.info("*****TOP 3 NEWS TEST***** " + "\n");
					News.top3news(driver, logger, top3news);

					// Language test
					logger.info("*****LANGUAGE TEST***** " + "\n");
					Language.language(driver, logger, userLanguage, baseURL, languageTestPage, mainMenu);

					// Menu test
					logger.info("*****MENU TEST***** " + "\n");
					Menu.menu(driver, logger, mainMenu, footerMenu, sidebarMenu, baseURL, languageTestPage);

					// Default BU
					logger.info("*****BU NEWS TEST***** " + "\n");
					DefaultBU.defaultBU(driver, logger, defaultBU, BUNewsWithDefault, changeBU, expectedBUNews);

					// Roles verification
					logger.info("*****Roles TEST***** " + "\n");
					logger.info("<<<<<" + role + ">>>>> " + "\n");
					Roles.role(driver, logger, baseURL, role, contentTypes, reports, mainMenu, adminMenu,
							contentOutOfScope, contentWithDiffRole);

					// logout
					logger.info("*****LOGOUT TEST***** " + "\n");
					Logout.logout(driver, logger, loginType, country, language, baseURL);
				}
			}
		} finally {
			driver.quit();
		}
	}

}
