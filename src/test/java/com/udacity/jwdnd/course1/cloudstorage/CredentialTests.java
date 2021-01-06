package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.pojo.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.pojo.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class CredentialTests extends CloudStorageApplicationTests {

    @Test
    public void testCredentialCreationAndDisplay() {
        String url = "example.com";
        String username = "jdoe";
        String password = "mypassword";

        signUpAndLogin();

        driver.get("http://localhost:" + port + "/home");
        WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();

        createCredential(url, username, password);
        driver.get("http://localhost:" + port + "/home");
        credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();

        CredentialPage credentialPage = new CredentialPage(driver);
        Credential credential = credentialPage.getDisplayCredential();

        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(username, credential.getUsername());
        Assertions.assertNotEquals(password, credential.getPassword());
    }

    @Test
    public void testCredentialUpdateAndDisplay() {
        String url = "example.com";
        String username = "jdoe";
        String password = "mypassword";

        signUpAndLogin();

        driver.get("http://localhost:" + port + "/home");
        WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();
        createCredential(url, username, password);


        String newUrl = "http://examples.com";
        String newUsername = "doejohn";
        String newPassword = "strongpassword";

        driver.get("http://localhost:" + port + "/home");
        credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();
        updateCredential(newUrl, newUsername, newPassword);

        driver.get("http://localhost:" + port + "/home");
        credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();

        CredentialPage credentialPage = new CredentialPage(driver);
        Credential credential = credentialPage.getDisplayCredential();

        Assertions.assertEquals(newUrl, credential.getUrl());
        Assertions.assertEquals(newUsername, credential.getUsername());
        Assertions.assertNotEquals(password, credential.getPassword());
    }


    @Test
    public void testDeleteCredentialAndDisplay() {
        String url = "example.com";
        String username = "jdoe";
        String password = "mypassword";

        signUpAndLogin();

        driver.get("http://localhost:" + port + "/home");
        WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();
        createCredential(url, username, password);


        String newUrl = "http://examples.com";
        String newUsername = "doejohn";
        String newPassword = "strongpassword";

        driver.get("http://localhost:" + port + "/home");
        credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();

        createCredential(newUrl, newUsername, newPassword);

        CredentialPage credentialPage = new CredentialPage(driver);

        driver.get("http://localhost:" + port + "/home");
        credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();
        credentialPage.deleteCredential();

        driver.get("http://localhost:" + port + "/home");
        credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();
        credentialPage.deleteCredential();

        driver.get("http://localhost:" + port + "/home");
        credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialTab.click();

        Assertions.assertFalse(credentialPage.verifyCredentialExists());
    }



    private void createCredential(String url, String username, String password) {
        ResultPage resultPage = new ResultPage(driver);
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.createCredential(url, username, password);
        resultPage.clickOk();
    }

    private void updateCredential(String newUrl, String newUsername, String newPassword) {
        ResultPage resultPage = new ResultPage(driver);
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.updateCredential(newUrl, newUsername, newPassword);
        resultPage.clickOk();
    }
}
