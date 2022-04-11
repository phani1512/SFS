package verifyTitle;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import constaints.Constaints;

public class VerifyTitle extends Constaints {

	@Test(priority = 0)
	public void verifyPageTitle() throws InterruptedException {
		test = extent.createTest("verifyPageTitle").assignAuthor("Phaneendra").assignDevice("Chrome 97");
		test.info("Tiltle Verified Successfully");
		// Actual title
		String Title = driver.getTitle().trim();
		System.out.println("The page title is : " + Title);
		String Expected_title = "Sircon For States";
		Assert.assertEquals(Title, Expected_title);
		System.out.println("Title verified");

	}
}
