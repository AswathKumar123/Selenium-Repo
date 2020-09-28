package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class resizableEx {public static WebDriver driver;
public static String browser="Firefox";

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
	driver.get("https://jqueryui.com/resizable/");
	driver.manage().window().maximize();
	Dimension d = new Dimension(800,800);
	driver.manage().window().setSize(d);
	
	WebDriverWait wait = new WebDriverWait(driver,10);
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']")));
	
	WebElement resizer = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
	
	Actions action = new Actions(driver);
	action.dragAndDropBy(resizer, 400, 4000).perform();

}

}
