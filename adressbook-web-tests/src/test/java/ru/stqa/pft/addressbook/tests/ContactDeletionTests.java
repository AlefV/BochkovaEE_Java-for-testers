package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.contact().all();
        UserData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Thread.sleep(5000);
        app.contact().gotoHomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(),before.size()-1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
