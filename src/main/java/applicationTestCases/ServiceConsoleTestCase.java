package applicationTestCases;

import java.awt.AWTException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationPages.LoginPage;
import baseCode.BaseMethod;

public class ServiceConsoleTestCase extends BaseMethod {
	
	@BeforeTest
	public void setSheetName() {
			fileName = "ServiceConsoleTestData";
			sheetName = "Sheet1";
	}

	@Test(dataProvider="fetchData")
	public void verifyServiceConsoleFunctionality(String publicLink,String download,
			String Share,String viewFileDetails,String delete,
			String searchUser,String errMsg,String userMessage,
			String uploadedFileName,String uploadedFileExt,
			String successDeleteMsg ) throws InterruptedException, AWTException {
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.setUserName()
		.setPassword()
		.clickLoginButton()
		.clickToggleButton()
		.clickViewAllOption()
		.clickOnSerachedApp()
		.clickOnFilesDropDownIcon()
		.selectFilesFromDropDownMenu()
		.clickOnLastModifiedDate()
		.clickOnDropDownAndSelectMenuOption(publicLink)
		.verifyCreateLinkDisabled()
		.clickOnDropDownAndSelectMenuOption(download)
		.verifyFileDownloadedSuccessfully()
		.clickOnDropDownAndSelectMenuOption(Share)
		.verifyShareErrorMsgForInappropriateSearchUserSelected(searchUser, errMsg)
		.selectAnotherSearchUserAndShare(userMessage)
		.verifyIfShareWasSuccessful()
		.uploadFileFromLocal()
		.clickOnDropDownAndSelectMenuOption(viewFileDetails)
		.verifyTheUploadedFileNameAndExtension(uploadedFileName,uploadedFileExt)
		.clickOnDropDownAndSelectMenuOption(delete)
		.confirmDeletion()
		.verifyDeleteSuccessful(successDeleteMsg);	
		
	}
}
