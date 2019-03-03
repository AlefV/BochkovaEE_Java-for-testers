package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitUserCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillUserForm(UserData userData) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getPhoneHome());
        type(By.name("email"), userData.getEmail());

    }

    public void initUserCreation() {
        click(By.linkText("add new"));
    }

    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initUserModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitUserModification() {
        click(By.name("update"));
    }

    public void initUserDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptDeletion() {
        acceptAlert();
    }

    public void gotoHomePage(){
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void create(UserData userData) {
        initUserCreation();
        fillUserForm(userData);
        submitUserCreation();
        contactCache = null;
        gotoHomePage();
    }

    public void modify(UserData contact) {
        selectUserById(contact.getId());
        initUserModification();
        fillUserForm(contact);
        submitUserModification();
        contactCache = null;
    }

    public void delete(UserData contact) {
        selectUserById(contact.getId());
        initUserDeletion();
        contactCache = null;
        acceptDeletion();
    }

    public boolean isThereAUser() {
        return  isElementPresent(By.name(("selected[]")));
    }

    private Contacts contactCache = null;

    public Contacts all() {

        if (contactCache !=null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contactCache.add(new UserData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return new Contacts(contactCache);

    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
