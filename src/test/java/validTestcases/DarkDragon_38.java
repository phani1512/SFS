package validTestcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import constaints.Constaints;

// Verify Check All check box is enabled in Current Events.
public class DarkDragon_38 extends Constaints {

	@Test(priority = 3)
	public void DarkDragon_38() throws InterruptedException {
		test = extent.createTest("DarkDragon_38").assignAuthor("Phaneendra").assignDevice("Chrome 97");
		test.info("DarkDragon_38");
		// identify element with link text then apply click()
		WebElement producerLicencing = driver.findElement(By.linkText("Producer Licensing"));
		producerLicencing.click();
		Thread.sleep(2000);
		WebElement regExam = driver.findElement(By.id("moduleREG_EXAMId"));
		regExam.click();
		WebElement submit = driver.findElement(By.xpath("//input[@value='Submit']"));
		submit.click();
		Thread.sleep(5000);
		Select drpStatus = new Select(driver.findElement(By.name("searchRegExamStatus")));
		drpStatus.selectByValue("OP");
		WebElement search = driver.findElement(By.name("buttons.search.name"));
		search.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.linkText("7"));
		js.executeScript("arguments[0].scrollIntoView();", element);

		WebElement selectingRecord = driver.findElement(By.linkText("7"));
		selectingRecord.click();
		Thread.sleep(5000);
		WebElement optionalData = driver.findElement(By.xpath("(//a[@id='a1'])[2]"));
		optionalData.click();
		WebElement events = driver.findElement(By.linkText("Events"));
		events.click();

		WebElement checkAll = driver.findElement(By.id("deleteCheckAll"));
		checkAll.isEnabled();
		// String beforeCheckclassValue = checkAll.getAttribute("class");
		checkAll.click();
		String afterCheckclassValue = checkAll.getAttribute("class");
		try {

			afterCheckclassValue.equals("checkboxChecked");
			System.out.println("check box is enabled");

		} catch (Exception e) {
			System.out.println("unable to click Check All check box as it is disabled");
		}
	}

	@Test(priority = 4)
	public void DarkDragon95() throws InterruptedException {
		test = extent.createTest("DarkDragon95").assignAuthor("Phaneendra").assignDevice("Chrome 97");
		test.info("DarkDragon95");

		WebElement switchModule = driver.findElement(By.xpath("//img[@alt='Switch Module']"));
		switchModule.click();
		Thread.sleep(2000);
		WebElement Enforcementcases = driver.findElement((By.xpath("//input[@value='ECASE']")));

		Enforcementcases.click();
		WebElement goModule = driver.findElement(By.xpath("//input[@value='Go to Module']"));
		goModule.click();
//		WebElement submit = driver.findElement(By.xpath("//input[@value='Submit']"));
//		submit.click();
		Thread.sleep(5000);
		WebElement EcaseID = driver.findElement(By.name("ecaseId"));
		EcaseID.sendKeys("100");
		WebElement Search = driver.findElement(By.name("buttons.search.name"));
		Search.click();
		Thread.sleep(5000);
		WebElement respondents = driver.findElement(By.linkText("Respondents"));
		respondents.click();
		Thread.sleep(5000);

	}

	@Test(priority = 5)
	public void DarkDragon169() throws InterruptedException {
		test = extent.createTest("DarkDragon169").assignAuthor("Phaneendra").assignDevice("Chrome 97");
		test.info("DarkDragon169");

		WebElement switchModule = driver.findElement(By.xpath("//img[@alt='Switch Module']"));
		switchModule.click();
		Thread.sleep(2000);
		WebElement consumerComplaints = driver.findElement((By.xpath("//input[@value='CNSMR_CMPLN']")));
		consumerComplaints.click();
		WebElement goModule = driver.findElement(By.xpath("//input[@value='Go to Module']"));
		goModule.click();
//		WebElement submit = driver.findElement(By.xpath("//input[@value='Submit']"));
//		submit.click();
		Thread.sleep(10000);
		WebElement search = driver.findElement(By.id("side-rail-search"));
		search.click();
		WebElement coverage = driver.findElement(By.id("search-coverage-toggle"));
		WebElement involvedParty = driver.findElement(By.id("search-independent-review-toggle"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", involvedParty);
		Thread.sleep(10000);
		coverage.click();

		WebElement dropdown = driver.findElement(By.id("search-coverage-level"));
		Thread.sleep(5000);
		Select select = new Select(dropdown);

		List<WebElement> options = select.getOptions();

		System.out.println(options.size());
		int totalOptions = options.size() - 1;
		System.out.println("Total Options: " + totalOptions);
		for (WebElement options2 : options) {
			String dropDownOptionValue = options2.getText();
			System.out.println("All Options: " + options2.getText());
			if (dropDownOptionValue.equals("Credit Life")) {
				System.out.println("Credit Life");
			}
		}

	}
}
