package act_language;

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
import act_menu.Menu;
import act_users.ReadCSV;
import act_content.Content;

public class Language {

	public static void language(WebDriver driver, Logger logger, String userLanguage, String baseURL,
			String languageTestPage, String contentOnPage, String mainMenu) {
		driver.findElement(By.cssSelector("div.region-header .site-logo___image")).click();

		String url = driver.getCurrentUrl();
		boolean flag = false, flag1 = false, flag2 = false, flag3 = false;

		// check if url has language same as user language
		System.out.println("Home page url : "+url);
		if ((baseURL + "/" + userLanguage).equalsIgnoreCase(url)) {
			logger.info("Home page URL has same language as user language. " + "\n");

			flag = Menu.mainMenu(driver, logger, mainMenu, url);

			// go to link in first main menu item and check the language in URL
			driver.findElement(By.cssSelector("div.region-header nav > ul > li ")).click();
			url = driver.getCurrentUrl();
			System.out.println("First menu page url : "+url);
		
			if (url.contains(baseURL + "/" + userLanguage.toLowerCase())) {
				logger.info("First menu item page URL has same language as user language. " + "\n");
				flag1 = true;
			} else {
				logger.info("First menu item page URL don't have same language as user language. " + "\n");
			}

			// go to node with language different than user language
			driver.get(baseURL + "/" + languageTestPage);
			flag2 = Menu.mainMenu(driver, logger, mainMenu, baseURL + "/" + languageTestPage);
			flag3 = Content.contentView(driver, logger, baseURL, "", languageTestPage, contentOnPage);
			if (flag && flag1 && flag2 && flag3) {
				logger.info("Language Test: PASSED " + "\n");
			} else {
				logger.info("Language Test: FAILED " + "\n");
			}

		} else {

			logger.info("Home page URL don't have same language as user language. " + "\n");
			logger.info("Language Test: FAILED " + "\n");
		}

	}

}
