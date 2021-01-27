package page.keyword;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.locator.homePageLocator;

public class HomePage extends ParentPage {

	private homePageLocator locator;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	

	public ArrayList getAllGameBoxesEelements() {
		return (ArrayList) findElements(locator.gameBox, "xpath");
	}

	public void scrollIntoViewOfWebElement(int i) {

		String addedWeeklyTextXpath = "//p[text()='More Added Weekly']";
		String locator_for_scorll = "(//li[@class='games-item']//a)[" + (i - 6) + "]";
		WebElement availableGameElement = (i <= 25) ? findElement(addedWeeklyTextXpath, "xpath")
				: findElement(locator_for_scorll, "xpath");
		jsexecutor.executeScript("arguments[0].scrollIntoView();", availableGameElement); // scroll

	}

	/**
	 * click on the game box in case of failure it will click using action class
	 * 
	 * @param gameNumber
	 */
	public void clickOnTheGameBox(int gameNumber) {
		String availableGameXpath = "(//li[@class='games-item']//a)[" + gameNumber + "]";

		try {
			wait.until(ExpectedConditions.elementToBeClickable(getByObject(availableGameXpath, "xpath")));
			click(findElement(availableGameXpath, "xpath"));
		} catch (ElementClickInterceptedException err) {
			pause(1);
			wait.until(ExpectedConditions.elementToBeClickable(getByObject(availableGameXpath, "xpath")));
			actions.moveToElement(findElement(availableGameXpath, "xpath")).click().build().perform();
		}

	}

	public String getTournaMentCount() {
		return findElement("//div//span[@class='count-tournaments']", "xpath").getText();

	}

	public String getNameOfGame() {
		return findElement("//div//h1[@class='heading']", "xpath").getText();
	}

	public void hoverGameBoxElement(int gameNumber) {
		String availableGameXpath = "(//li[@class='games-item']//a)[" + gameNumber + "]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(availableGameXpath)));
		hoveToElement(availableGameXpath, "xpath");
	}

}
