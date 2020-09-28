package testcases;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testalert {


	public static String browser = "Chrome";
	public static WebDriver driver;

	public static void main(String[] args) throws AWTException {
		
		WebDriverManager.firefoxdriver().setup();
//		FirefoxDriver driver = new FirefoxDriver();
//		driver.get("https://www.hilton.com");
		
		WebDriverManager.chromedriver().setup();
//		ChromeDriver driver = new ChromeDriver();
//		driver.get("https://www.hilton.com");
		
		
		if(browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if(browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("Safari")){
			driver = new SafariDriver();
		}
		
		//WebDriver driver = new SafariDriver();
		driver.get("https://www.hilton.com/en/search/hilton-honors/?query=Dallas&placeId=&arrivalDate=2019-09-05&departureDate=2020-09-06");
		driver.manage().window().maximize();
		
		driver.switchTo().frame("iframe");
		
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		
		System.out.println(frames.size());
		
		for(WebElement frame: frames) {
			System.out.println(frame.getText());
		}
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		//Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
}
}
