package com.udacity.jwdnd.course1.cloudstorage.pojo;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {
    @FindBy(xpath = "//button[@onclick=\"showNoteModal()\"]")
    WebElement btnAddNewNote;

    @FindBy(id = "note-title")
    WebElement noteTitle;

    @FindBy(id = "note-description")
    WebElement noteDescription;

    @FindBy(xpath = "//button[@onclick=\"$('#noteSubmit').click();\"]")
    WebElement btnSaveChanges;

    @FindBy(id = "btnEditNote")
    private WebElement btnEditNote;


    @FindBy(id = "btnDeleteNote")
    private WebElement btnDeleteNote;

    @FindBy(id = "tableNoteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement tableNoteDescription;


    private final WebDriverWait wait;
    private final WebDriver driver;

    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 500);
    }

    public void createNote(String title, String description){
        btnAddNewNote.click();
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(description);
        btnSaveChanges.click();
    }

    public void updateNote(String newTitle, String newDescription) {
        btnEditNote.click();
        noteTitle.clear();
        noteDescription.clear();

        noteTitle.sendKeys(newTitle);
        noteDescription.sendKeys(newDescription);
        btnSaveChanges.click();
    }

    public void deleteNote(){
        btnDeleteNote.click();
    }

    public Note getDisplayNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
//        String title = tableNoteTitle.getText();
        String description = tableNoteDescription.getText();

        return new Note(null, title, description, null);
    }

    public boolean verifyNoteExists() {
        try {
            driver.findElement(By.id("tableNoteTitle"));
            return true;
        }catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}

