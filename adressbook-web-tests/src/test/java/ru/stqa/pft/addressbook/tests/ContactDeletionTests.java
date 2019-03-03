package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().gotoHomePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new UserData()
                    .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withPhoneHome("123456789").withEmail("test@test.com"));
        }
    }
    @Test
    public void testContactDeletion() throws InterruptedException {
        Set<UserData> before = app.contact().all();
        UserData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Thread.sleep(5000);
        app.contact().gotoHomePage();
        Set<UserData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
