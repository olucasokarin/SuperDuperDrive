package com.udacity.jwdnd.course1.cloudstorage.pojo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

//    @FindBy(xpath = "//div[@class= 'alert alert-success fill-parent']/span/a")
    @FindBy(xpath = "//div/span/a")
//    @FindBy(id = "aSuccess")
    private WebElement aResultSuccess;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOk() {
         aResultSuccess.click();
    }
}
