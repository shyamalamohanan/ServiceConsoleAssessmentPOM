package applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseCode.BaseMethod;

public class LoginPage extends BaseMethod{
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage setUserName() {
		WebElement eleUserName = driver.findElement(By.xpath(wOProp.getProperty("LoginPage.eleUserName.Xpath")));
		eleUserName.sendKeys(prop.getProperty("userName"));
		return this;
	}
	
	public LoginPage setPassword() {
		WebElement eleUserPassword = driver.findElement(By.xpath(wOProp.getProperty("LoginPage.elePassword.Xpath")));
		eleUserPassword.sendKeys(prop.getProperty("password"));
		return this;
	}
	
	public HomePage clickLoginButton() {
		WebElement eleLoginButton = driver.findElement(By.xpath(wOProp.getProperty("LoginPage.eleLoginButton.Xpath")));	
		eleLoginButton.click();
		return new HomePage(driver);
	}

}
