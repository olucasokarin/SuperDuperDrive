package com.udacity.jwdnd.course1.cloudstorage.pojo;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {
    @FindBy(xpath = "//button[@onclick=\"showCredentialModal()\"]")
    private WebElement btnAddNewCredential;

    @FindBy(id = "credential-url")
    WebElement credentialUrl;

    @FindBy(id = "credential-username")
    WebElement credentialUsername;

    @FindBy(id = "credential-password")
    WebElement credentialPassword;

    @FindBy(id = "btnEditCredential")
    private WebElement btnEditCredential;

    @FindBy(id = "btnDeleteCredential")
    private WebElement btnDeleteCredential;

    @FindBy(xpath = "//button[@onclick=\"$('#credentialSubmit').click();\"]")
    private WebElement btnCredentialSaveChanges;

    @FindBy(id = "tableCredentialUrl")
    private WebElement tableCredentialUrl;

    @FindBy(id = "tableCredentialUsername")
    private WebElement tableCredentialUsername;

    @FindBy(id = "tableCredentialPassword")
    private WebElement tableCredentialPassword;

    private final WebDriverWait wait;
    private final WebDriver driver;

    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 500);
    }

    public void createCredential(String url, String username, String password) {
        btnAddNewCredential.click();
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(username);
        this.credentialPassword.sendKeys(password);
        btnCredentialSaveChanges.click();
    }

    public void updateCredential(String newUrl, String newUsername, String newPassword) {
        btnEditCredential.click();
        credentialUrl.clear();
        credentialUsername.clear();
        credentialPassword.clear();

        credentialUrl.sendKeys(newUrl);
        credentialUsername.sendKeys(newUsername);
        credentialPassword.sendKeys(newPassword);
        btnCredentialSaveChanges.click();
    }

    public void deleteCredential() {
        btnDeleteCredential.click();
    }

    public Credential getDisplayCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tableCredentialUrl)).getText();
        String username = tableCredentialUsername.getText();
        String password = tableCredentialPassword.getText();

        return new Credential(null, username, url, null, password, null);
    }

    public boolean verifyCredentialExists() {
        try {
            driver.findElement(By.id("tableCredentialUrl"));
            return true;
        }catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
