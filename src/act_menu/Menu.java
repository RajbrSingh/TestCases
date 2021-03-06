package act_menu;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import act_login.UserLogin;
import act_users.ReadCSV;

public class Menu {

	public static void menu(WebDriver driver, Logger logger, String mainMenu, String footerMenu, String sidebarMenu,
			String baseURL, String languageTestPage) {
		driver.findElement(By.cssSelector("div.region-header .site-logo___image")).click();
		boolean flag1=false;
		boolean flag3 =false;
		boolean flag4 =false;
		boolean flag5 = false;
		try {
			 flag1 = mainMenu(driver, logger, mainMenu, baseURL);
		}catch(Exception e) {
			logger.info("Main menu could not be found.");
		}
		
		boolean flag2 = sidebarMenu(driver, logger, sidebarMenu, baseURL);
		try {
		 flag3 = footerMenu(driver, logger, footerMenu, baseURL);
		}catch(Exception e) {
			logger.info("Footer menu could not be found.");
		}
		driver.get(baseURL + "/" + languageTestPage);
		try {
		 flag4 = mainMenu(driver, logger, mainMenu, baseURL + "/" + languageTestPage);
		}catch(Exception e) {
			logger.info("Main menu could not be found.");
		}
		try {
		 flag5 = footerMenu(driver, logger, footerMenu, baseURL + "/" + languageTestPage);
		}catch(Exception e) {
			logger.info("Footer menu could not be found.");
		}

		if (flag1 && flag2 && flag3 && flag4 && flag5) {
			logger.info("Menu test: PASSED" + "\n");
		} else {
			logger.info("Menu test: FAILED" + "\n");
		}
	}

	public static boolean mainMenu(WebDriver driver, Logger logger, String mainMenu, String page) {
		String menu = "";
		Boolean flag = false;
	//	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement ulElement = driver.findElement(By.cssSelector("div.region-header ul.nav--main"));
		List<WebElement> links = ulElement.findElements(By.tagName("li"));
		for (int i = 0; i < links.size(); i++) {
			String menuVal = links.get(i).getText();
			if (menuVal.contains(",")) {
				menuVal = menuVal.replaceAll(",", "@c");
			}

			if (i == links.size() - 1) {
				menu = menu + menuVal;
			} else {
				menu = menu + menuVal + "/";
			}
		}
         System.out.println("Main menu : "+menu);
		if (mainMenu.equals(menu)) {
			logger.info("Main menu for " + page + ": PASSED " + "\n");
			flag = true;
		} else {
			logger.info("Main menu for " + page + ": FAILED " + "\n");
		}

		return flag;

	}

	public static boolean sidebarMenu(WebDriver driver, Logger logger, String sidebarMenu, String page) {
		String menu = "";
		Boolean flag = false;

		WebElement ulElement = driver.findElement(By.cssSelector("div.view--dashboard-links-view"));
		List<WebElement> links = ulElement.findElements(By.tagName("a"));
		for (int i = 0; i < links.size(); i++) {
			String menuVal = links.get(i).getText();
			if (menuVal.contains(",")) {
				menuVal = menuVal.replaceAll(",", "@c");
			}

			if (i == links.size() - 1) {
				menu = menu + menuVal;
			} else {
				menu = menu + menuVal + "/";
			}
		}
    System.out.println("Sidebar menu : "+menu);
		if (sidebarMenu.equals(menu)) {
			logger.info("Sidebar menu for " + page + ": PASSED " + "\n");
			flag = true;
		} else {
			logger.info("Sidebar menu for " + page + ": FAILED " + "\n");
		}

		return flag;

	}

	public static boolean footerMenu(WebDriver driver, Logger logger, String footerMenu, String page) {
		String menu = "";
		Boolean flag = false;

		WebElement ulElement = driver
				.findElement(By.cssSelector("div.region-footer ul.nav--main"));
		List<WebElement> links = ulElement.findElements(By.tagName("a"));
		for (int i = 0; i < links.size(); i++) {
			String menuVal = links.get(i).getText();
			if (menuVal.contains(",")) {
				menuVal = menuVal.replaceAll(",", "@c");
			}

			if (i == links.size() - 1) {
				menu = menu + menuVal;
			} else {
				menu = menu + menuVal + "/";
			}
		}
		 System.out.println("Footer menu : "+menu);
		if (footerMenu.equals(menu)) {
			logger.info("Footer menu for " + page + ": PASSED " + "\n");
			flag = true;
		} else {
			logger.info("Footer menu for " + page + ": FAILED " + "\n");
		}

		return flag;

	}

	public static boolean adminMenu(WebDriver driver, Logger logger, String adminMenu) {
		String menu = "";
		boolean flag = false;

		WebElement ulElement = driver.findElement(By.cssSelector("div.toolbar-menu-administration > ul"));
		List<WebElement> links = ulElement.findElements(By.tagName("li"));
		for (int i = 0; i < links.size(); i++) {
			String menuVal = links.get(i).getText();
			if (menuVal.contains(",")) {
				menuVal = menuVal.replaceAll(",", "@c");
			}

			if (i == links.size() - 1) {
				menu = menu + menuVal;
			} else {
				menu = menu + menuVal + "/";
			}
		}
		System.out.println("Admin menu : "+menu);
		if (adminMenu.equals(menu)) {
			logger.info("Admin menu : PASSED " + "\n");
			flag = true;
		} else {
			logger.info("Admin menu : FAILED " + "\n");
		}

		return flag;

	}

	public static boolean availReports(WebDriver driver, Logger logger, String reports, String baseURL) {
		driver.get(baseURL + "/admin/reports");
		String list = "";
		boolean flag = false;

		WebElement ulElement = driver.findElement(By.cssSelector("ul.admin-list"));
		List<WebElement> links = ulElement.findElements(By.tagName("span"));
		for (int i = 0; i < links.size(); i++) {
			String report = links.get(i).getText();
			if (report.contains(",")) {
				report = report.replaceAll(",", "@c");
			}

			if (i == links.size() - 1) {
				list = list + report;
			} else {
				list = list + report + "/";
			}
		}
		System.out.println("Reports available : "+list);
		if (reports.equalsIgnoreCase(list)) {
			logger.info("Publisher has correct available reports. \n");
			flag = true;
		} else {
			logger.info("Publisher don't have correct available reports. \n");
		}
		return flag;

	}

}
