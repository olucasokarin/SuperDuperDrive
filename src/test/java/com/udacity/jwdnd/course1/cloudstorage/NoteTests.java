package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.pojo.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.pojo.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTests extends CloudStorageApplicationTests {


    @Test
    public void testCreateAndDisplay() {
        String noteTitle = "title note";
        String noteDescription = "content note.";

        signUpAndLogin();

        driver.get("http://localhost:" + port + "/home");
        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        createNote(noteTitle, noteDescription);
        driver.get("http://localhost:" + port + "/home");
        noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        NotePage notePage = new NotePage(driver);
        Note note = notePage.getDisplayNote();

        Assertions.assertEquals(noteTitle, note.getNoteTitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());
    }



    @Test
    public void testDeleteNoteAndDisplay() {
        String noteTitle = "title note";
        String noteDescription = "content note.";
        signUpAndLogin();

        driver.get("http://localhost:" + port + "/home");
        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        createNote(noteTitle, noteDescription);
        driver.get("http://localhost:" + port + "/home");
        noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        NotePage notePage = new NotePage(driver);
        notePage.deleteNote();

        Assertions.assertFalse(notePage.verifyNoteExists());
    }



    @Test
    public void testUpdateNoteAndDisplay() {
        String noteTitle = "title note";
        String noteDescription =  "conent note.";
        signUpAndLogin();

        driver.get("http://localhost:" + port + "/home");
        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        createNote(noteTitle, noteDescription);
        driver.get("http://localhost:" + port + "/home");
        noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();

        String newTitle = "new title";
        String newDescription = "description update";
        updateNote(newTitle, newDescription);

        driver.get("http://localhost:" + port + "/home");
        noteTab = driver.findElement(By.id("nav-notes-tab"));
        noteTab.click();


        NotePage notePage = new NotePage(driver);
        Note note = notePage.getDisplayNote();

        Assertions.assertEquals(newTitle, note.getNoteTitle());
        Assertions.assertEquals(newDescription, note.getNoteDescription());
    }

    private void createNote(String noteTitle, String noteDescription) {
        ResultPage resultPage = new ResultPage(driver);
        NotePage notePage = new NotePage(driver);
        notePage.createNote(noteTitle, noteDescription);
        resultPage.clickOk();
    }

    private void updateNote(String newTitle, String newDescription) {
        ResultPage resultPage = new ResultPage(driver);
        NotePage notePage = new NotePage(driver);
        notePage.updateNote(newTitle, newDescription);
        resultPage.clickOk();
    }

}
