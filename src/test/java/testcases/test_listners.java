package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.internal.EventFiringMouse;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test_listners {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
	
		WebDriver webdriver = new ChromeDriver();
		
		EventFiringWebDriver driver = new EventFiringWebDriver(webdriver);
		
		weblistners listeners = new weblistners();
		
		driver.register(listeners);
		
	
//		
//		driver.findElement(By.linkText("Site Usage Agreement")).click();
//		
//		Thread.sleep(2000L);
//		
//		driver.navigate().back();
		
		EventFiringMouse mouse = new EventFiringMouse(driver, listeners);
		
		driver.navigate().to("https://www.hilton.com");
		 JavascriptExecutor js = (JavascriptExecutor) driver;

	      
	        driver.manage().window().maximize();

//	        // This  will scroll down the page by  1000 pixel vertical		
	        js.executeScript("window.scrollBy(0,1000)");
	        
	        Thread.sleep(3000L);
		
		Locatable hoverItem = (Locatable) driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/main/div[2]/div[3]/div/div[2]/div/div[1]/div/button"));
		
		Thread.sleep(3000L);
		
		Locatable hoverItem2 = (Locatable) driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/main/div[2]/div[3]/div/div[2]/div/div[2]/div/button"));
		Coordinates cord = hoverItem.getCoordinates();
		
		Coordinates cord2 = hoverItem2.getCoordinates();
		
		try {
			mouse.mouseMove(cord);
		} catch(Exception e){
			
		}
		
		Thread.sleep(3000L);
		
		mouse.mouseMove(cord2);
		
		Thread.sleep(3000L);
		
		driver.quit();
		
		
	
		
//		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/header/div[1]/div/div/nav/ul/li[2]/a")).click();
//		
//		
//		Thread.sleep(2000L);
//		
//		driver.navigate().back();
//		
//		Thread.sleep(2000L);
//		
//		driver.navigate().forward();

	}

}
