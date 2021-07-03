package baseCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseMethod {

	public WebDriver driver;
	public static WebDriverWait wait; 
	public static Properties prop,wOProp; 
	public String fileDownloadPath;
	public String fileName;
	public String sheetName;

	@BeforeMethod
	public void startApp() throws IOException  {
		FileInputStream configFile = new FileInputStream("./repository/config.properties");
		prop = new Properties();
		prop.load(configFile);
		FileInputStream wOfile = new FileInputStream("./repository/webObjectRepository.properties");
		wOProp = new Properties();
		wOProp.load(wOfile);

		WebDriverManager.chromedriver().setup();	
		fileDownloadPath = prop.getProperty("fileDownloadPath");
		Map<String, Object> prefsMap = new HashMap<String, Object>();
		prefsMap.put("profile.default_content_settings.popups", 0);
		prefsMap.put("download.default_directory", fileDownloadPath);
		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("prefs", prefsMap);
		options.addArguments("--test-type");
		options.addArguments("--disable-extensions");
		options.addArguments("disable-notifications");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);		

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));	

		wait = new WebDriverWait(driver,Duration.ofSeconds(60));

	}

	@AfterMethod
	public void closeApp() {
		File path = new File(prop.getProperty("fileDownloadPath"));
		File[] files = path.listFiles();
		for (File file : files) {
			System.out.println("Deleted filename :"+ file.getName());
			file.delete();
		}

		driver.close();		
	}

	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {
		return utils.ReadExcel.readExcelData(fileName,sheetName);
	}
}
