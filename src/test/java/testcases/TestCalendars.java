package testcases;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCalendars {
	public static WebDriver driver;
	
	static int targetDay =0,
			targetMonth = 0,
			targetYear =0;
	
	static int currentDay =0,
			currentMonth =0,
			currentYear =0;
	
	static int jumpMonthsBy = 0;
	
	static boolean increment = true;
	
	public static void getCurrentDayMonthAndYear() {
		
		Calendar cal = Calendar.getInstance();
		
		 currentDay = cal.get(Calendar.DAY_OF_MONTH);
		 currentMonth = cal.get(Calendar.MONTH)+1;
		 currentYear = cal.get(Calendar.YEAR);
	}

	public static void getTargerDayMonthAndYear(String dateString) {
		
		int firstIndex = dateString.indexOf("/");
		int lastIndex = dateString.lastIndexOf("/");
		
		String month = dateString.substring(0, firstIndex);
		targetMonth = Integer.parseInt(month);
		
		String day = dateString.substring(firstIndex+1, lastIndex);
		targetDay = Integer.parseInt(day);
		
		String year = dateString.substring(lastIndex+1, dateString.length());
		targetYear = Integer.parseInt(year);
	}
	
	public static void CalculateHowmanyMonthstoJump() {
		if((targetMonth-currentMonth)>0) {
			jumpMonthsBy = targetMonth-currentMonth;
		} else {
			jumpMonthsBy = currentMonth - targetMonth;
			increment = false;
		}
	}
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.hilton.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//button[@data-testid='check-in-button']")).click();
		
		
		
		String dateToSet = "11/14/2020";
		
		getCurrentDayMonthAndYear();
		
		System.out.println(currentMonth+ " " + currentDay+ " " + currentYear);
		
		getTargerDayMonthAndYear(dateToSet);
		
		System.out.println(targetMonth+ " " + targetDay+ " " + targetYear);
		
		CalculateHowmanyMonthstoJump();
		System.out.println(jumpMonthsBy);
		System.out.println(increment);
		
for(int i=0; i<jumpMonthsBy-1;i++) {
			
			if(increment) {
				
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div/div[3]/div[3]/div/div[2]/a[2]")).click();;
			} else {
				
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div/div[3]/div[3]/div/div[2]/a[1]")).click();;
			}
		}
		
		driver.findElement(By.partialLinkText(Integer.toString(targetDay))).click();
		

	}
	


}
