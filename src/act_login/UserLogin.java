package act_login;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLogin {

	// function called from Master class with all the user parameters
	public static boolean userLogin(WebDriver driver, Logger logger, String username, String password, String loginType,
			String country, String language, String baseURL) {

		String url = baseURL;
		boolean loginStatus = false;

		// select login screen url as per the user location
		if (country.equalsIgnoreCase("EU")) {
			if (loginType.equalsIgnoreCase("Drupal")) {
				logger.info("-----Login type: Drupal-----\n");
				url = baseURL + "/user/login?visitor=EU&destination=/en/node/86";
				loginStatus = drupalLogin(logger, driver, username, password, url);
			}
		} else if (country.equalsIgnoreCase("CA")) {
			if (loginType.equalsIgnoreCase("Okta")) {
				logger.info("-----Login type: Okta-----\n");
				url = baseURL + "/login-na-select.html";
				loginStatus = oktaLogin_CA(logger, driver, username, password, url, language);
			}
		} else if (country.equalsIgnoreCase("NA")) {
			if (loginType.equalsIgnoreCase("Okta")) {
				logger.info("-----Login type: Okta-----\n");
				url = baseURL + "/login-na.php";
			}
			loginStatus = oktaLogin_NA(logger, driver, username, password, url);
		}
		return loginStatus;

	}

	// Drupal login - EU
	public static boolean drupalLogin(Logger logger, WebDriver driver, String username, String password, String url) {
		boolean returnStatus = false;
		driver.get(url);
		driver.findElement(By.cssSelector("input#edit-name")).sendKeys(username);
		driver.findElement(By.cssSelector("input#edit-pass")).sendKeys(password);
		driver.findElement(By.cssSelector("input#edit-submit")).click();
		try {
			assertEquals(driver.findElement(By.cssSelector("div.messages--error div h2")).getText().toString(),
					"Error message");
			logger.info("****Error from Drupal on Login:  "
					+ driver.findElement(By.cssSelector("div.messages--error div")).getText() + "****\n"
					+ "User Drupal login: FAILED " + "\n");
			returnStatus = false;
		} catch (Exception e) {
			logger.info("User login: PASSED " + "\n");
			returnStatus = true;
		}
		return returnStatus;
	}

	// Okta login CA
	public static boolean oktaLogin_CA(Logger logger, WebDriver driver, String username, String password, String url,
			String language) {
		boolean returnStatus = false;
		driver.get(url);
		driver.findElement(By.cssSelector("button#button-" + language.toLowerCase())).click();
		driver.findElement(By.cssSelector("button#okta-button")).click();
		driver.findElement(By.cssSelector("input#okta-signin-username")).sendKeys(username);
		driver.findElement(By.cssSelector("input#okta-signin-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#okta-signin-submit")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		boolean status = true;

		try {
			if (driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString()
					.equalsIgnoreCase("Sign in failed!")) {
				assertEquals(driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString(),
						"Sign in failed!");
				logger.info("****Error From Okta on Login: "
						+ driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText() + "****\n"
						+ "User OKta login: FAILED " + "\n");
				status = false;
				returnStatus = false;
			}
		} catch (Exception e) {
			logger.info("User OKta login: PASSED " + "\n");
			returnStatus = true;
		}
		if (status) {
			try {
				if (driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString().equalsIgnoreCase(
						"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.")) {
					assertEquals(driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString(),
							"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.");
					logger.info("****Error From Drupal on Login: "
							+ driver.findElement(By.cssSelector("div#errorMessage p")).getText() + "****\n"
							+ "User Drupal login: FAILED" + "\n");
					returnStatus = false;
				}
			} catch (Exception e) {
				logger.info("User Drupal login: PASSED" + "\n");
				returnStatus = true;
			}
		}
		return returnStatus;

	}

	// Okta login NA
	public static boolean oktaLogin_NA(Logger logger, WebDriver driver, String username, String password, String url) {
		driver.get(url);
		// driver.findElement(By.cssSelector("button#button-en")).click();
		driver.findElement(By.cssSelector("button#okta-button")).click();
		driver.findElement(By.cssSelector("input#okta-signin-username")).sendKeys(username);
		driver.findElement(By.cssSelector("input#okta-signin-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#okta-signin-submit")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		boolean status = true;
		boolean returnStatus = false;

		try {
			if (driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString()
					.equalsIgnoreCase("Sign in failed!")) {
				assertEquals(driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText().toString(),
						"Sign in failed!");
				logger.info("****Error From Okta on Login: "
						+ driver.findElement(By.cssSelector("div.okta-form-infobox-error p")).getText() + "****\n"
						+ "User Okta login: FAILED " + "\n");
				status = false;
				returnStatus = false;
			}
		} catch (Exception e) {
			logger.info("User Okta login: PASSED " + "\n");
			returnStatus = true;
		}
		if (status) {
			try {
				if (driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString().equalsIgnoreCase(
						"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.")) {
					assertEquals(driver.findElement(By.cssSelector("div#errorMessage p")).getText().toString(),
							"There is a problem with your Inner Circle user account. Contact the help desk for your area for assistance.");
					logger.info("****Error From Drupal on Login: "
							+ driver.findElement(By.cssSelector("div#errorMessage p")).getText() + "****\n"
							+ "User Drupal login: FAILED " + "\n");
					returnStatus = false;
				}
			} catch (Exception e) {
				logger.info("User Drupal login: PASSED " + "\n");
				returnStatus = true;
			}
		}
		return returnStatus;
	}

}