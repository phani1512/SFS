package validTestcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import constaints.Constaints;

public class ValidLogin extends Constaints {

	@Parameters({ "stateId", "userName", "password", "expectedUserName" })
	@Test()
	public void validCredentials(String stateId, String userName, String password, String expectedUserName) throws IOException {
		test = extent.createTest("ValidLogin").assignAuthor("Phaneendra").assignDevice("Chrome 97");
		test.info("ValidLogin");
		try {
			Thread.sleep(2000);
			
			WebElement stateID = driver.findElement(By.id("stateId"));
			stateID.clear();
			stateID.sendKeys(stateId);
			
			WebElement username = driver.findElement(By.id("uName"));
			username.clear();
			username.sendKeys(userName);
			WebElement Password = driver.findElement(By.id("pWord"));
			Password.clear();
			Password.sendKeys(password);
			WebElement click = driver.findElement(By.id("btnLogin"));
			click.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			String veriyUserName = driver.findElement(By.xpath("(//span[@class='loggedInAsName'])")).getText()
					.trim();
			expectedUserName.trim();
			Assert.assertEquals(veriyUserName, expectedUserName);
			System.out.println("Login Successfull & UserName verified");
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

//	@Test(priority = 4, enabled = false)
//	public void OnBoarding() throws IOException {
//		test = extent.createTest("OnBoarding");
//		try {
//		WebElement expandHumburger = driver.findElement(By.id("nav-toggle"));
//		expandHumburger.click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		WebElement onBoarding = driver.findElement(By.xpath("//span[text()='Onboarding']"));
//		onBoarding.click();
//		WebElement sendInvitation = driver.findElement(By.xpath("//span[text()='Send Invitation']"));
//		sendInvitation.click();
//		
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-controlname='SendingInvitationTo']")));
//		driver.findElement(By.xpath("//span[@data-controlname='SendingInvitationTo']")).click();
//		
//		WebElement selectingNewAgency = driver.findElement(By.id("0cae6219-d0d2-4468-a149-64fa69833d81"));
//		selectingNewAgency.click();
//		
//		WebElement clickingBussinessType = driver.findElement(By.id("0cae6219-d0d2-4468-a149-64fa69833d81"));
//		clickingBussinessType.click();
//		
//		WebElement selectingBussinessType = driver.findElement(By.id("86e3a60a-3bdd-4fbb-9ba1-69a32b529561"));
//		selectingBussinessType.click();
//		
//		WebElement redirectingPaymentNO = driver.findElement(By.id("invitationStep1UDF_5_2_17"));
//		redirectingPaymentNO.click();
//		
//		driver.findElement(By.xpath("//a[@onclick='AddNewContractNameRow()']")).click();
//		
//		WebElement clickingContractedUsing = driver.findElement(By.xpath("(//td[@role='gridcell']/span)[1]"));
//		clickingContractedUsing.click();
//		
//		driver.findElement(By.xpath("//li[@id=\"38ec2495-27af-49e4-b8ce-1f0feccff46d\"]")).click();
//		
//		WebElement clickingContractedAddendums = driver.findElement(By.xpath("(//td[@role='gridcell']/span)[2]"));
//		clickingContractedAddendums.click();
//		driver.findElement(By.name("ALL")).click();
//		
//		WebElement save = driver.findElement(By.xpath("(//a[@role='button'])[1]"));
//		save.click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("InvitationStep1Save")).click();
//		Thread.sleep(10000);
//		driver.findElement(By.xpath("(//input[@value='Confirm'])")).click();
//		
//		}catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		
//		
//	}
//
//	@Test(priority = 5, enabled = false)
//	public void dropdownSelection() throws IOException, InterruptedException {
//		test = extent.createTest("dropdownSelection");
//		WebElement Language = driver.findElement(By.id("ddlLanguage"));
//		Select select = new Select(Language);
//		select.getOptions();
//		select.selectByIndex(0);
//		Thread.sleep(3000);
//		String selectedLanguage = driver.findElement(By.id("lblWebsitesHeading")).getText().trim();
//		selectedLanguage.trim();
//		String expectedLanguage = "المراقبة";
//		Assert.assertEquals(selectedLanguage, expectedLanguage);
//	}
//
//	@Test(priority = 6, enabled = false)
//	public void logOut() throws IOException {
//		test = extent.createTest("logOut");
//		WebElement logOut = driver.findElement(By.id("span_logout"));
//		logOut.click();
//		String Title = driver.getTitle().trim();
//		System.out.println("The page title is : " + Title);
//		String Expected_title = "Welcome to OSINTMon";
//		Assert.assertEquals(Title, Expected_title);
//		System.out.println("Logout is Sucessfull & Home Page Title is verified");
//
//	}
//}
