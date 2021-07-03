package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseCode.BaseMethod;

public class HomePage extends BaseMethod {
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage clickToggleButton() {
		WebElement eleToggleButton = driver.findElement(By.xpath(wOProp.getProperty("HomePage.eleToggleButton.Xpath")));
		eleToggleButton.click();
		return this;
	}

	public HomePage clickViewAllOption() {
		WebElement eleViewAll = driver.findElement(By.xpath(wOProp.getProperty("HomePage.eleViewAll.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleViewAll));
		eleViewAll.click();
		return this;
	}
	
	public ServiceConsolePage clickOnSerachedApp() {
		WebElement eleApp = driver.findElement(By.xpath(wOProp.getProperty("HomePage.eleApp.Xpath")));
		wait.until(ExpectedConditions.elementToBeClickable(eleApp));
		eleApp.click();
		return new ServiceConsolePage(driver);
	}
	
}
