package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().gotoHomePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new UserData()
                    .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withPhoneHome("123456789").withEmail("test@test.com"));
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        Set<UserData> before = app.contact().all();
        UserData modifiedContact = before.iterator().next();
        UserData user = new UserData()
                .withId(modifiedContact.getId()).withFirstName("Petr").withLastName("Ivanov").withAddress("address").withPhoneHome("123456789").withEmail("test@test.com");
        app.contact().modify(user);
        Thread.sleep(1000);
        app.contact().gotoHomePage();
        Set<UserData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(user);
        Assert.assertEquals(before, after);
    }

}
