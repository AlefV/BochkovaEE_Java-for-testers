package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

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
        gotoHomePage();
    }

    public void modify(UserData user, int index) {
        selectUser(index);
        initUserModification();
        fillUserForm(user);
        submitUserModification();
    }

    public void delete(int index) {
        selectUser(index);
        initUserDeletion();
        acceptDeletion();
    }

    public boolean isThereAUser() {
        return  isElementPresent(By.name(("selected[]")));
    }

    public List<UserData> list() {

        List<UserData> contacts = new ArrayList<UserData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            contacts.add(new UserData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;

    }

}
