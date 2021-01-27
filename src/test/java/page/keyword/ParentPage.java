package page.keyword;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;



public class ParentPage {
	public WebDriver driver = null;
	public JavascriptExecutor jsexecutor=null;
	public WebDriverWait wait = null;
	public Actions actions =null;
	
	
	

	public ParentPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		this.jsexecutor=(JavascriptExecutor) driver;
		this.actions=new Actions(driver);
	
	}

	public static void log(String... message) {
		String Final_message = "";
		for (String msg : message) {
			Final_message += msg;
		}
		System.out.println(Final_message);
	}

	public void launchUrl(String url) {
		this.driver.get(url);
	}
	
	/**
	 * Pause the exectuion for few second
	 * @param second
	 */
	
	public void pause(int second) {
		log("waiting for "+second);
		try {
		   Thread.sleep(second*1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * wait until dom is not loaded completely
	 *
	 */
	public void waitUntilDomIsCompletelyLoaded() {
		
		String is_loaded=(String) this.jsexecutor.executeScript("return document.readyState");
		
		while(!(is_loaded.equals("complete"))) {
			log("dom is not loaded completely waiting for some time",is_loaded);
			pause(1);
			is_loaded=(String) this.jsexecutor.executeScript("return document.readyState");
		}
	}

	public void naviagateBack() {
		driver.navigate().back();
		log("Navigating back to the page", this.driver.getCurrentUrl());
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
    /**
     * Click on the element
     * 
     * @param ele
     */
	public void click(WebElement ele) {
		ele.click();
		log("Clicked on the element");
	}

	public By getByObject(String locator, String type) {

		if (type.equals("xpath"))
			return By.xpath(locator);

		else if (type.equals("css"))
			return By.cssSelector(locator);

		else if (type.equals("id"))
			return By.id(locator);

		else if (type.equals("linktext"))
			return By.linkText(locator);

		else if (type.equals("plinktext"))
			return By.partialLinkText(locator);

		else if (type.equals("class"))
			return By.className(locator);
		else
			return null;

	}
	
	
	
	
	
	public WebElement findElement(String locator, String type) {
		By by_locator = getByObject(locator, type);

		return this.driver.findElement(by_locator);

	}

	public List findElements(String locator, String type) {
		By by_locator = getByObject(locator, type);
		return this.driver.findElements(by_locator);
	}

	public int getCountOfElements(List elements) {
		return elements.size();
	}
	
	public void navigateBack() {
		driver.navigate().back();
		log("Navigated back to the page");
	}
	
	public void hoveToElement(String locator,String type) {
		actions.moveToElement(findElement(locator,type)).build().perform();
		log("hover over the element"+locator);
	}
	
	public String  getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}
}
