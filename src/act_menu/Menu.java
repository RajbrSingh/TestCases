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

	public static boolean mainMenu(WebDriver driver, Logger logger, String mainMenu, String page) {
		String menu = "";
		Boolean flag = false;
		WebElement ulElement = driver.findElement(By.cssSelector("div.region-header nav.contextual-region > ul"));
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

		if (mainMenu.equals(menu)) {
			logger.info("Main menu for " + page + ": PASSED " + "\n");
			flag = true;
		} else {
			logger.info("Main menu for " + page + ": FAILED " + "\n");
		}
		return flag;

	}

}
