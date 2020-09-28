package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBrowsers {
	
	public static String browser = "Chrome";
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
	
		//System.setProperty("webdriver.gecko.driver", "/Users/aswathkumarkulasekarapandian/Desktop/Frameworkjars/geckodriver");
		
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
		
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.withTimeout(30, TimeUnit.SECONDS)
	       .pollingEvery(5,TimeUnit.SECONDS)
	       .ignoring(ElementNotInteractableException.class)
	       .ignoring(NoSuchElementException.class);
		
		
	
		
		String title = driver.getTitle();
		System.out.println(title);
		System.out.println(title.length());
	
		// Search box
		WebElement findbox = driver.findElement(By.id("search-input-box"));
		findbox.sendKeys("Dallas");
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div/div/button")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		//Zoom 
		WebElement Zoom = driver.findElement(By.xpath("//*[@title=\"Zoom in\"]"));
		for(int i=0;i<=3; i++) {
		Zoom.click();
		}
		
	//Sort drop down	
		WebElement sort = driver.findElement(By.id("sortBy"));
		sort.sendKeys("Price: low-high");
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("DFWMCHT-marker-popup"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title=\"Zoom out\"]"))).click();
		
		WebElement search = driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/div/div[2]/div[3]/input"));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/header/div[3]/div/div/div[2]/div[3]/input"))).clear();
		
		
		search.click();
		search.clear();
		search.sendKeys("Dallas");
		
		for(int i=0; i<=4;i++) {
		
		System.out.println(driver.findElement(By.id("PlacesAutocomplete__suggestion-"+i+"")).getText());
		}
		
		for(int i=1;i<=12;i++) {		
		System.out.println("autocomplete xpath texts: " + driver.findElement(By.xpath("//div[starts-with(@id,'PlacesAutocomplete__suggestion')]["+i+"]")).getText());
		}
		
		WebElement auto = driver.findElement(By.xpath("//div[@class='action-panelstyles__PanelContainer-sc-16nmty4-0 fFonuD panel__forced-flyout action-panel-enter-done']"));
		
		List<WebElement> autofill = driver.findElements(By.tagName("img"));
		System.out.println("The autocomplete lenght is: " + autofill.size());
//		
//		for(WebElement autoc: autofill) {
//			System.out.println(autoc.getText());
//		}
//		
		
		autofill.get(3).click();
		
		WebElement learnlink = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[2]/div/div/p[2]/a"));
	
		//Drag and drop element	
		Actions action = new Actions(driver);
		action.dragAndDrop(learnlink, search).perform();
		action.contextClick(learnlink).sendKeys(Keys.ARROW_DOWN).build().perform();
		
	
		
		
//		List<WebElement> autocomplete = driver.findElements(By.tagName("role"));
//		System.out.println(autocomplete.size());
		
		//Sort drop down
		List<WebElement> sortbox = driver.findElements(By.tagName("option"));
		System.out.println(sortbox.size());
		System.out.println(sortbox.get(2).getText());
		
		for(int i=0; i<sortbox.size(); i++) {
			sortbox.get(i).click();
			System.out.println(sortbox.get(i).getText());
			System.out.println(sortbox.get(i).getAttribute("data-e2e"));
		}
		
		Select select = new Select(sort);
		for(int i=0; i<=2;i++) {
		select.selectByIndex(i);
		}
		
		
		
//		List<WebElement> links = driver.findElements(By.tagName("a"));
//		System.out.println("The total links " + links.size());
//	for(WebElement link: links) {
//		System.out.println("The list of texts " + link.getText());
//		System.out.println("The list of links " + link.getAttribute("href"));
//	}
		
	WebElement header = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[1]/div/div"));
	System.out.println("Header is displayed or not " + header.isDisplayed());
	System.out.println("Header is enabled or not " + header.isEnabled());
	System.out.println("Header is selected or not " + header.isSelected());
	List<WebElement> links = header.findElements(By.tagName("a"));
	System.out.println("The total links " + links.size());
for(WebElement link: links) {
	System.out.println("The list of texts " + link.getText());
	System.out.println("The list of links " + link.getAttribute("href"));
	
	
	//Filter button Brands
	driver.findElement(By.id("filters-button-with-icon")).click();
	driver.findElement(By.id("Brands")).click();
	
	WebElement filtersbox = driver.findElement(By.xpath("/html/body/reach-portal/div[3]/div/div/div/div[4]/div"));
	List<WebElement> brandOptions = filtersbox.findElements(By.tagName("button"));
	int brandsize = brandOptions.size();
	
//	for (int j=0; j<brandsize; j++) {
//		brandOptions.get(j).click();
//	}
//	
	
	
	for(WebElement brand: brandOptions) {
		System.out.println(brand.getText());
		brand.click();
	}
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	driver.findElement(By.id("apply-filters-button")).click();
	
	//Filter button Amenities
		driver.findElement(By.id("filters-button-with-icon")).click();
		driver.findElement(By.id("Amenities")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement amenities = driver.findElement(By.xpath("/html/body/reach-portal/div[3]/div/div/div/div[4]"));
		List<WebElement> amenitiesfilter = amenities.findElements(By.tagName("label"));
		System.out.println(amenitiesfilter.size());
	
		for(WebElement amenity: amenitiesfilter) {
			amenity.click();
			System.out.println(amenity.getText());
		}
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("reset-active-filters-button")).click();
		driver.findElement(By.id("apply-filters-button")).click();
		
	
	
	WebElement map = driver.findElement(By.xpath("//*[@id=\"map_canvas\"]/div/div/div[1]/div[3]"));
	
	List<WebElement> mappins = map.findElements(By.tagName("button"));
	System.out.println("The number of mappins: "+ mappins.size());
	
	for(WebElement mapmarker: mappins) {
		System.out.println("The name of map pins: " + mapmarker.getAttribute("aria-labelledby"));
	}
	
	
	
	
	mappins.get(5).click();
	
	WebElement PDP = driver.findElement(By.xpath("/html/body/reach-portal/div[3]/div/div/div"));
	
	List<WebElement> pdpclosebtn = PDP.findElements(By.tagName("button"));
	pdpclosebtn.get(0).click();
	
	
	
	
	//Mouse Actions
	WebElement propertyname = driver.findElement(By.xpath("//h3[@data-e2e='listViewPropertyName']"));
	//Actions action = new Actions(driver);
	action.moveToElement(propertyname).perform();
	
	WebElement searchbox = driver.findElement(By.id("search-input-box"));
	//WebElement text = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[2]/div/div/p[2]"));

	
	
	//Javascript executor by capturing page object function
	
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", header );
	
	WebElement mappin = driver.findElement(By.xpath("//button[@aria-labelledby='DALMKDT-marker-popup']"));
	
	WebElement mapmarker = driver.findElement(By.xpath("//button[@data-testid='map-pin']"));
	
	action.clickAndHold(mappin).dragAndDrop(mappin, sort).build().perform();

	Thread.sleep(5000);
	
	
	
	WebElement hotellist = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div[2]/div[1]/ul"));
	
	List<WebElement> Viewrates = hotellist.findElements(By.tagName("a"));
	System.out.println(Viewrates.size());
	
	for(WebElement button: Viewrates) {
		System.out.println(button.getText());
		
	}
	
	
	Viewrates.get(2).click();
}
		
		
		//driver.close();
		
driver.quit();
		
	}
	

}
