package applicationPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import baseCode.BaseMethod;

public class ServiceConsolePage extends BaseMethod {
	public String fileinFolder;
	
	public ServiceConsolePage(WebDriver driver) {
		this.driver = driver;
	}

	public ServiceConsolePage clickOnFilesDropDownIcon() throws InterruptedException {
		WebElement eleFilesDD = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleFilesDD.Xpath")));
		wait.until(ExpectedConditions.elementToBeClickable(eleFilesDD));
		eleFilesDD.click();
		Thread.sleep(3000);
		return this;
	}

	public ServiceConsolePage selectFilesFromDropDownMenu() throws InterruptedException {
		WebElement eleFiles = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleFiles.Xpath")));
		wait.until(ExpectedConditions.elementToBeClickable(eleFiles));
		eleFiles.click();
		Thread.sleep(3000);
		return this;
	}

	public ServiceConsolePage clickOnLastModifiedDate() throws InterruptedException{
		Thread.sleep(3000);
		WebElement eleLMDate = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleLMDate.Xpath")));
		wait.until(ExpectedConditions.elementToBeClickable(eleLMDate));
		eleLMDate.click();
		return this;
	}


	public ServiceConsolePage clickOnDropDownAndSelectMenuOption(String option) throws InterruptedException{
		Thread.sleep(3000);
		WebElement eleLDD = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleLDD.Xpath")));
		eleLDD.click();
		WebElement clkLink = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='button' and @title='"+option+"']/.."))));
		clkLink.click();
		Thread.sleep(3000);
		return this;
	}


	public ServiceConsolePage verifyCreateLinkDisabled() throws InterruptedException{
		WebElement eleLinkDisabled = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleLinkDisabled.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleLinkDisabled));
		boolean linkDisabled = !(eleLinkDisabled.isEnabled());
		Assert.assertEquals(linkDisabled, true, "element is enabled");
		WebElement eleclose = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleclose.Xpath")));
		wait.until(ExpectedConditions.elementToBeClickable(eleclose));
		eleclose.click();
		return this;
	}

	public ServiceConsolePage verifyFileDownloadedSuccessfully() throws InterruptedException {
		Thread.sleep(3000);
		boolean flag = false;
		boolean expected = true;

		String dirPath =prop.getProperty("fileDownloadPath");
				/*"C:\\Users\\shyam\\Selenium BootCamp\\AssessmentPOM\\data"; */
		
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files.length == 0 || files == null) {
			System.out.println("The directory is empty");
			flag = false;
		} else {
			for (File listFile : files) {
				 fileinFolder = listFile.getName();
				System.out.println("File " + fileinFolder+ " is present");
				flag = true;
			}
		}
		
		Assert.assertEquals(flag, expected, "file not dowloaded");
		return this;
	}


	public ServiceConsolePage verifyShareErrorMsgForInappropriateSearchUserSelected(String searchUser, String errorMsg) throws InterruptedException{
		WebElement eleSearchPeople = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleSearchPeople.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleSearchPeople));
		eleSearchPeople.click();
		WebElement eleSelectUser = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleSelectUser.Xpath")));
		String actual = wait.until(ExpectedConditions.visibilityOf(eleSelectUser)).getText();
		wait.until(ExpectedConditions.elementToBeClickable(eleSelectUser));
		eleSelectUser.click();
		WebElement eleShareMsg = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleShareMsg.Xpath")));
		String actualMsg = wait.until(ExpectedConditions.visibilityOf(eleShareMsg)).getText();
		Assert.assertEquals(actual, searchUser, "Wrong User Selected");
		Assert.assertEquals(actualMsg, errorMsg, "Share allowed for the User Selected");
		Thread.sleep(3000);

		return this;
	}

	public ServiceConsolePage selectAnotherSearchUserAndShare(String userMessage) throws InterruptedException{
		WebElement eledeleteIcon = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eledeleteIcon.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eledeleteIcon));
		eledeleteIcon.click();
		WebElement eleSelectNewUser = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleSelectNewUser.Xpath")));
		wait.until(ExpectedConditions.elementToBeClickable(eleSelectNewUser));
		eleSelectNewUser.click();		
		WebElement eleMessage = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleMessage.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleMessage));
		eleMessage.sendKeys(userMessage);
		WebElement eleShareButton = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleShareButton.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleShareButton));
		eleShareButton.click();
		Thread.sleep(3000);

		return this;
	}

	public ServiceConsolePage verifyIfShareWasSuccessful() throws InterruptedException{
		String shareMessageBegin = "You shared ";
				String shareMessageEnd = " with Integration User";
				String fileShared = fileinFolder.substring(0, fileinFolder.lastIndexOf('.'));
				String expectedShareMessgae = shareMessageBegin+fileShared+shareMessageEnd;
		WebElement eleMessageBanner = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleMessageBanner.Xpath")));
		String actualMsg = wait.until(ExpectedConditions.visibilityOf(eleMessageBanner)).getText();	
		Assert.assertEquals(actualMsg, expectedShareMessgae, "Share not successfull for the User Selected");
		WebElement eleCloseShare = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleCloseShare.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleCloseShare));
		eleCloseShare.click();
		Thread.sleep(3000);

		return this;
	}

	public ServiceConsolePage uploadFileFromLocal() throws InterruptedException, AWTException{
		WebElement eleUpload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(wOProp.getProperty("ServiceConsolePage.eleUpload.Xpath"))));
		eleUpload.click();
		Thread.sleep(3000);

		Robot rb = new Robot();
		StringSelection up = new StringSelection(prop.getProperty("uploadFile"));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(up, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		WebElement eleUploadDone= driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleUploadDone.Xpath")));
		eleUploadDone.click(); 

		return this;
	}

	public ServiceConsolePage verifyTheUploadedFileNameAndExtension(String uploadedFileName, String uploadedFileExt) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement eleFileName = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleFileName.Xpath")));
		String actualFileName = wait.until(ExpectedConditions.visibilityOf(eleFileName)).getText();	
		WebElement eleFileExt = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleFileExt.Xpath")));
		String actualFileExt = wait.until(ExpectedConditions.visibilityOf(eleFileExt)).getText();

		Assert.assertEquals(actualFileName, uploadedFileName, "File dosen't match");
		Assert.assertEquals(actualFileExt, uploadedFileExt, "File Verified is Different; hence extension not matched");

		WebElement eleCloseTab = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleCloseTab.Xpath")));
		js.executeScript("arguments[0].click();", eleCloseTab);

		Thread.sleep(3000);
		return this;
	}

	public ServiceConsolePage confirmDeletion() throws InterruptedException {
		WebElement eleDelete = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleDelete.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleDelete));
		eleDelete.click();
		return this;
	}

	public ServiceConsolePage verifyDeleteSuccessful(String successDeleteMsg) throws InterruptedException {
		WebElement eleDeleteMsg = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleDeleteMsg.Xpath")));
		String actualDeleteMsg =wait.until(ExpectedConditions.visibilityOf(eleDeleteMsg)).getText();
		System.out.println(actualDeleteMsg);
		Assert.assertEquals(actualDeleteMsg, successDeleteMsg, "File is not deleted successfully");
		WebElement eleCloseDelete = driver.findElement(By.xpath(wOProp.getProperty("ServiceConsolePage.eleCloseDelete.Xpath")));
		wait.until(ExpectedConditions.visibilityOf(eleCloseDelete));
		eleCloseDelete.click();
		return this;
	}















}