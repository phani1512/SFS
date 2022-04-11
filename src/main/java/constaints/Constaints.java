package constaints;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import emailReport.EmailReport;

public class Constaints {

	public static WebDriver driver;
//	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public ExtentTest test;

	@BeforeSuite
	public void SetUrl() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\valaboph\\eclipse-workspace\\SFS\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\valaboph\\eclipse-workspace\\SFS\\configs\\Configurations.Properties");
		prop.load(fis);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		Thread.sleep(2000);
		try {
//			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/xtentReport.html");
//			// Create an object of Extent Reports
//			htmlReporter.config().setDocumentTitle("Automation Report ");
//			// Name of the report
//			htmlReporter.config().setReportName("Functional Report ");
//			// Dark Theme
//			htmlReporter.config().setTheme(Theme.DARK);
//			extent = new ExtentReports();
//			extent.attachReporter(htmlReporter);
//			extent.setSystemInfo("Host Name", "SFS");
//			extent.setSystemInfo("OS", "Windows10");
//			extent.setSystemInfo("Environment", "Production");
//			extent.setSystemInfo("User Name", "Phaneendra");
//			extent.setSystemInfo("Browser", "Chrome");

			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(
					System.getProperty("user.dir") + "/test-output/xtentReport.html");
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("SFS");
			extent.attachReporter(spark);

		} catch (Exception ex) {

		}
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			// To capture screenshot path and store the path of the screenshot in the string
			// "screenshotPath"
			// We do pass the path captured by this method in to the extent reports using
			// "logger.addScreenCapture" method.
			String screenshotPath = getScreenShot(driver, result.getName());
			// To add it in the extent report

			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
	}

	@AfterTest
	public void endReport() {
		extent.flush();

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		EmailReport.emailreport();

	}

	// This method is to capture the screenshot and return the path of the
	// screenshot.
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
