package act_logout;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import act_login.UserLogin;
import act_users.ReadCSV;

public class Logout {

	   public static void logout(WebDriver driver,Logger logger, String loginType,String country, String language, String baseURL) {
		   try{
			   
			// logout
				driver.findElement(By.cssSelector("li.userlogout")).click();

				// check if user is logged out of Drupal and/or Okta
				String titleText = "";
				if (loginType.equalsIgnoreCase("Drupal")) {
					try {
						titleText = driver.findElement(By.cssSelector("div#middle_part h2")).getText()
								.toString();
					} catch (Exception e) {
					}
					try {
						titleText = driver.findElement(By.cssSelector("div#na-login-card h2")).getText()
								.toString();
					} catch (Exception e) {

					}

					if (titleText.equalsIgnoreCase("Global Intranet")
							|| titleText.equalsIgnoreCase("Intranet global")) {
						logger.info("Drupal logout: PASSED" + "\n");
					} else {
						logger.info("Drupal logout: FAILED " + "\n");
					}
				} else if (loginType.equalsIgnoreCase("Okta")) {
					try {
						titleText = driver.findElement(By.cssSelector("div#middle_part h2")).getText()
								.toString();
					} catch (Exception e) {
					}
					try {
						titleText = driver.findElement(By.cssSelector("div#na-login-card h2")).getText()
								.toString();
					} catch (Exception e) {

					}

					if (titleText.equalsIgnoreCase("Global Intranet")
							|| titleText.equalsIgnoreCase("Intranet global")) {
						logger.info("Drupal logout: PASSED " + "\n");
						// implies user has logged out of Drupal
						// then check for Okta logout
						if (country.equalsIgnoreCase("CA") || country.equalsIgnoreCase("NA")) {
							String url = "";
							if (language.toLowerCase().equals("en")) {
								url = baseURL + "/login-na.php";
							} else {
								url = baseURL + "/fr-login-na.php";
							}
							driver.get(url);
							driver.findElement(By.cssSelector("button#okta-button")).click();
							try {
								assertEquals(driver.findElement(By.cssSelector("input#okta-signin-username"))
										.getText().toString(), "");
								logger.info("Okta logout: PASSED " + "\n");
							} catch (Exception e) {
								logger.info("Okta logout: FAILED " + "\n");
							}
						}
					} else {
						logger.info("Drupal logout: PASSED " + "\n");
					}
				}   
		   } catch (Exception e) {
				logger.warning("\n**Could not find logout button**\n\n");
			}
	   }

}
