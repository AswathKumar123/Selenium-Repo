package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class weblistners extends AbstractWebDriverEventListener{

	public void afterClickOn(WebElement element, WebDriver driver) {
		
		System.out.println("Object Clicked");
	}
	
	public void afterNavigateForward(WebDriver driver) {
		
		System.out.println("Moving forward");
	}
	
}
