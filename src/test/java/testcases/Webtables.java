package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Webtables {

	
		public static String browser = "Chrome";
		public static WebDriver driver;

		public static void main(String[] args) throws InterruptedException {
		
			//System.setProperty("webdriver.gecko.driver", "/Users/aswathkumarkulasekarapandian/Desktop/Frameworkjars/geckodriver");
			
		WebDriverManager.firefoxdriver().setup();
//			FirefoxDriver driver = new FirefoxDriver();
//			driver.get("https://www.hilton.com");
			
			WebDriverManager.chromedriver().setup();
//			ChromeDriver driver = new ChromeDriver();
//			driver.get("https://www.hilton.com");
			
			
			if(browser.equals("Chrome")) {
				driver = new ChromeDriver();
			} else if(browser.equals("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equals("Safari")){
				driver = new SafariDriver();
			}
			
			//WebDriver driver = new SafariDriver();
			driver.get("https://www.loc.gov/rr/print/list/057_chron.html");
			driver.manage().window().maximize();
			
			List<WebElement> rowNum = driver.findElements(By.xpath("//table[@border='1']/tbody/tr"));
			
			System.out.println("Total rows " + rowNum.size());
			
			List<WebElement> colNum = driver.findElements(By.xpath("//table[@border='1']/tbody/tr[2]/td"));
			System.out.println("Total cols " + colNum.size());
			
			for(int i =2; i<=rowNum.size();i++) {
				for(int j=1; j<=colNum.size();j++) {
					System.out.print(driver.findElement(By.xpath("//table[@border='1']/tbody/tr["+i+"]/td["+j+"]")).getText() + "    ");
				}
				System.out.println();
			}
			
			driver.quit();
			
	}

}
