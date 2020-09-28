package testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class rightClick {
	
	public static String browser = "Chrome";
	public static WebDriver driver;
	
	public static void CaptureScreenshot() throws IOException {
		
		Date d = new Date();
		
		String filename = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(screenshot, new File (".//screenshot//"+filename));
	}
	
public static void CaptureElementScreenshot(WebElement ele) throws IOException {
		
		Date d = new Date();
		
		String filename = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		
		Point point = ele.getLocation();
		int elewidth = ele.getSize().getWidth();
		int eleheight = ele.getSize().getHeight();

		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elewidth, eleheight);
		ImageIO.write(eleScreenshot, "jpg", screenshot);
		
		FileUtils.copyFile(screenshot, new File (".//screenshot//"+filename));
	}

public static void CaptureScreenshotbyAshot() throws IOException {
	
	Date d = new Date();
	
	String filename = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
	
	
	
	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	
	ImageIO.write(screenshot.getImage(), "jpg", new File (".//screenshot//"+filename));
	
	
}

	public static void main(String[] args) throws AWTException, IOException {
		
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
		driver.get("https://www.hilton.com");
		driver.manage().window().maximize();
		
		WebElement findbox = driver.findElement(By.id("search-input-box"));
		findbox.sendKeys("Dallas");
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/div/div/div/div/button")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
//		WebElement learnlink = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[2]/div/div/p[2]/a"));
//		
//		//Drag and drop element	
//		Actions action = new Actions(driver);
//	
//		action.contextClick(learnlink).build().perform();
//		
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_DOWN);
//		robot.keyRelease(KeyEvent.VK_DOWN);
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);
//		
		WebElement searchbox = driver.findElement(By.id("search-input-box"));
	WebElement text = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[2]/div/div/p[2]"));
//	text.click();
//	text.sendKeys(Keys.chord(Keys.COMMAND + "a"));
//	text.sendKeys(Keys.chord(Keys.COMMAND + "c"));
//	searchbox.click();
//	searchbox.sendKeys(Keys.chord(Keys.COMMAND + "v"));
	
	
	//Javascript executor by capturing page object function
	searchbox.sendKeys("Memphis");
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", text);

//CaptureScreenshot();

//WebElement ele = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div/div/div/div/a[2]"));
//
//
//Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver,text);
//
//ImageIO.write(screenshot.getImage(), "jpg", new File (".//screenshot//filename.jpg"));



//CaptureElementScreenshot(ele);

//CaptureScreenshotbyAshot();


		Actions action = new Actions(driver);
//		action.sendKeys(Keys.chord(Keys.COMMAND,"a")).build().perform();
//		action.sendKeys(Keys.chord(Keys.COMMAND,"c")).build().perform();
//		
//		searchbox.click();
//		action.sendKeys(Keys.chord(Keys.COMMAND,"v")).build().perform();
		
		
		
		WebElement mappin = driver.findElement(By.xpath("//button[@aria-labelledby='DFWSLHF-marker-popup']"));
		
		WebElement mapmarker = driver.findElement(By.xpath("//button[@data-testid='map-pin']"));
		
		action.clickAndHold(mappin).dragAndDrop(mappin, mapmarker).build().perform();
		
		
		
	}

}
