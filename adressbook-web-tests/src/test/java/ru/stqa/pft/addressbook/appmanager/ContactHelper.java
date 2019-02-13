package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        click(By.id("1"));
    }

    public void initUserModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitUserModification() {
        click(By.name("update"));
    }
}
