package Demo.automation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.utility.apiRequest;
import page.keyword.HomePage;

public class BlueStack {

	// Data
	String path_to_store_data = "Resources\\TestData\\demoResult.txt";
	String chromeDriverPath = "Resources/Driver/chromedriver.exe";
	String baseUrl = "https://www.game.tv/";

	

	WebDriver driver;

	String gameName, tournamentCount, pageUrl;
	int statusCode;

	FileReaderWriter filereaderwrite;
	HomePage homePage;

	boolean header_flag = true;

	@BeforeClass
	public void setup() {

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		driver = new ChromeDriver();

		filereaderwrite = new FileReaderWriter(path_to_store_data, true);
		homePage = new HomePage(driver);
	}

	@Test
	public void launch_Url() {
		homePage.launchUrl(baseUrl);
		homePage.maximizeWindow();
	}

	@Test(dependsOnMethods = "launch_Url")
	public void fetch_tounrament_data() throws InterruptedException, IOException {

		int gameCount = homePage.getAllGameBoxesEelements().size();

		for (int i = 1; i <= gameCount; i++) {

			homePage.waitUntilDomIsCompletelyLoaded();

			homePage.scrollIntoViewOfWebElement(i);

			homePage.waitUntilDomIsCompletelyLoaded();

			homePage.hoverGameBoxElement(i);

			homePage.clickOnTheGameBox(i);

			homePage.waitUntilDomIsCompletelyLoaded();

			// 1) Name of the game
			gameName = homePage.getNameOfGame();
			// 2) Game detail page url
			pageUrl = homePage.getCurrentUrl();
			// 3) Status code for each age url
			statusCode = apiRequest.getStatusCode(pageUrl);
			// 4) Tournament count
			tournamentCount = homePage.getTournaMentCount();

			System.out.println(i + "  " + gameName + "  " + pageUrl + "  " + statusCode + "  " + tournamentCount);

			homePage.navigateBack();

			if (header_flag) {
				filereaderwrite.writeResult("#",  "Game name", "Page URL",  "Page Status", "Tournament count");
				header_flag = false;
			}
			filereaderwrite.writeResult(i, gameName, pageUrl, statusCode, tournamentCount);

		}
	}

	@AfterClass
	public void stop_test_session() {
		driver.close();
	}

}
