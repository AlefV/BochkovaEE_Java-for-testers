package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

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

    public void selectUser() {
        click(By.name("selected[]"));
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

    public void createAUser(UserData userData) {
        initUserCreation();
        fillUserForm(userData);
        submitUserCreation();
        gotoHomePage();
    }

    public boolean isThereAUser() {
        return  isElementPresent(By.name(("selected[]")));
    }
}
