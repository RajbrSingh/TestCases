package act_news;

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

public class News {

	 public static void top3news(WebDriver driver,Logger logger, String top3news) {
			// check for top 3 news on the Dashboard
			String top_news = "";
			try {
				top_news = top_news + driver
						.findElement(By.cssSelector(
								".view--dashboard-latest-3.view--display-block_1 .view__row  article  h3"))
						.getText().toString();
			} catch (Exception e) {

			}
			try {
				top_news = top_news + "/";
				top_news = top_news + driver.findElement(By.cssSelector(
						".view--dashboard-latest-3.view--display-block_2 .view__row:nth-child(1)  article  h3"))
						.getText().toString();
			} catch (Exception e) {

			}
			try {
				top_news = top_news + "/";
				top_news = top_news + driver.findElement(By.cssSelector(
						".view--dashboard-latest-3.view--display-block_2 .view__row:nth-child(2)  article  h3"))
						.getText().toString();
			} catch (Exception e) {

			}
   System.out.println(top_news);
   System.out.println(top3news);
			if (top_news.equals(top3news)) {
				logger.info("Top 3 dashboard news test: PASSED " + "\n");
			} else {
				logger.info("Top 3 dashboard news test: FAILED " + "\n");
			}
	   }
	   

}
