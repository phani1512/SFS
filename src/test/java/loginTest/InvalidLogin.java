package loginTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import constaints.Constaints;

public class InvalidLogin extends Constaints {

	@Test(priority = 1)
	public void NoCredentials() {
		test = extent.createTest("NoCredentials").assignAuthor("Phaneendra").assignDevice("Chrome 97");
		test.info("NoCredentials");
		try {
			Thread.sleep(6000);
			driver.findElement(By.id("stateId")).clear();
			driver.findElement(By.id("uName")).clear();
			driver.findElement(By.id("pWord")).clear();
			driver.findElement(By.id("btnLogin")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String ActualUsernameError = driver.findElement(By.xpath("(//li[@class='error'])[1]")).getText().trim();
			String ExpectedUsernameError = "User Name must be entered.";
			Assert.assertEquals(ActualUsernameError, ExpectedUsernameError);

			String ActualPasswordError = driver.findElement(By.xpath("(//li[@class='error'])[2]")).getText().trim();
			String ExpectedPasswordError = "Password must be entere.";
			Assert.assertEquals(ActualPasswordError, ExpectedPasswordError);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void InvalidCredentials() throws IOException {
		test = extent.createTest("InvalidCredentials").assignAuthor("vamsi").assignDevice("Chrome 97");
		test.info("InvalidCredentials");
		try {
			Thread.sleep(2000);
			WebElement username = driver.findElement(By.id("uName"));
			username.clear();
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\valaboph\\eclipse-workspace\\SFS\\configs\\Configurations.Properties");
			prop.load(fis);
			username.sendKeys(prop.getProperty("username"));
			WebElement password = driver.findElement(By.id("pWord"));
			password.clear();
			password.sendKeys(prop.getProperty("password"));
			driver.findElement(By.id("btnLogin")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String Actual = driver.findElement(By.xpath("(//li[@class='error'])[1]")).getText().trim();
			String Expected = "The login information you entered is incorrec.";
			Assert.assertEquals(Actual, Expected);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
