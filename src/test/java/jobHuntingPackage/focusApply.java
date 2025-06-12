package jobHuntingPackage;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class focusApply {
	ChromeDriver driver = new ChromeDriver();
	String url="https://www.naukri.com/mnjuser/homepage";
	
	@Test
	public void invokeBrowser() {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String currentUrl  = driver.getCurrentUrl();
		System.out.println(currentUrl);
	}
	@Test(dependsOnMethods ="invokeBrowser")
	public void login() throws Exception {
		Thread.sleep(2000);
		WebElement userName = driver.findElement(By.xpath("//input[@id='usernameField']"));
		userName.sendKeys("abhinavdwivedi0807@gmail.com");
		Thread.sleep(1000);
		WebElement userPass = driver.findElement(By.xpath("//input[@id='passwordField']"));
		userPass.sendKeys("--------");

		driver.findElement(By.xpath("//div[@class='login-wrapper col s6 card']//button[@class='waves-effect waves-light btn-large btn-block btn-bold blue-btn textTransform']")).click();
	}

	@Test(dependsOnMethods ="login")
	public void updateResume() throws Exception {
		Thread.sleep(3000);
		WebElement profile = driver.findElement(By.xpath("//div[@class='nI-gNb-drawer']"));
		profile.click();
		Thread.sleep(2000);
		WebElement openProfile = driver.findElement(By.xpath("//a[@class='nI-gNb-info__sub-link']"));
		openProfile.click();
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(2000);
		

         WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
         
		File uploadfile = new File("C:\\Users\\abhin\\eclipse-workspace\\jobHunting\\src\\test\\resources\\Abhinav-QA.pdf");
		fileInput.sendKeys(uploadfile.getAbsolutePath());
		Thread.sleep(5000);
		
	}
	@Test(dependsOnMethods="updateResume")
	public void searchKeyword() throws Exception{
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(2000);
		
		WebElement searchTab  = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/span"));
		searchTab.click();
		Thread.sleep(1000);
		WebElement searchInput  = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div/div[2]/div/div/div/input"));
		searchInput.sendKeys("selenium testing, Automation testing, SDET, QA automation, QA Testing, API Testing,");
		Thread.sleep(2000);
		WebElement selectInput  = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div/div[2]/div/div/div[2]/div[1]/ul/li[1]/div"));
		selectInput.click();
		Thread.sleep(1000);
		WebElement searchButton  = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/button"));
		searchButton.click();
	}
	
	
	@Test(dependsOnMethods = "searchKeyword")
	public void applyjob() throws Exception {
	    Thread.sleep(3000); // Initial sleep
	    for (int i = 1; i <= 5; i++) {
	        WebElement jobTitle = driver.findElement(By.xpath("(//div[@class='cust-job-tuple layout-wrapper lay-2 sjw__tuple '])[" + i + "]"));
	        String jobName = jobTitle.getText();
	        System.out.println(jobName);
	        System.out.println("----x-------x------x----");

	        jobTitle.click();
	        Thread.sleep(3000); 

	        String mainWindow = driver.getWindowHandle();

	       Set<String> windowHandles = driver.getWindowHandles();
	        
	        for (String handle : windowHandles) {
	            if (!handle.equals(mainWindow)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	        }
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        List<WebElement> list = driver.findElements(By.xpath("(//button[@id='apply-button'])[1]"));
	        if (list.size() > 0) {
	            WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(list.get(0)));
	            applyButton.click();
	            Thread.sleep(5000); 
	            driver.close();
	        } else {
	            System.out.println("Apply button not found on this tab.");
	            driver.close(); 

	        driver.switchTo().window(mainWindow);
	    }	
		
	}

}
	
	

