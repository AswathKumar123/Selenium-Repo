package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sliderexample {
	public static WebDriver driver;
	public static String browser="Chrome";

	public static void main(String[] args) {
		
		if(browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		} else if(browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browser.equals("Safari")) {
			driver = new SafariDriver();
	}
		driver.get("https://jqueryui.com/slider/");
		driver.manage().window().maximize();
		
		WebElement slider = driver.findElement(By.id("slider"));
		int width = slider.getSize().width/2;
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, width, 0).perform();

}
}