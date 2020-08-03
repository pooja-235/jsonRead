package testcase;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAccount {
	WebDriver driver;
	JSONParser parser = new JSONParser();
	String url, username, email;

	@BeforeSuite
	public void setUp() throws IOException, ParseException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		FileReader rf = new FileReader(".\\src\\test\\resources\\json\\cred.json");
		Object obj = parser.parse(rf);
		JSONObject jsonobj = (JSONObject) obj;
		url = (String) jsonobj.get("url");
		username = (String) jsonobj.get("username");
		email = (String) jsonobj.get("email");

	}

	@Test
	public void createaccount() {
		driver.get(url);
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='user_mailinglist']")).click();
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
	}

	@AfterSuite
	public void quit() {
		driver.quit();
	}
}
