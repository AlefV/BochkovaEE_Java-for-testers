package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

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
        type(By.name("home"), userData.getHomePhone());
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

    public void initUserModification(int id) {

        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();;
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
        initUserModification(contact.getId());
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
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allAddresses = element.findElement(By.xpath(".//td[4]")).getText();
            contactCache.add(new UserData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAllAddresses(allAddresses));
        }
        return new Contacts(contactCache);

    }
    public UserData infoFromEditForm(UserData contact) {
        initUserModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("work")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String address2 = wd.findElement(By.name("address2")).getAttribute("value");
        wd.navigate().back();
        return new UserData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address).withAddress2(address2);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

}
