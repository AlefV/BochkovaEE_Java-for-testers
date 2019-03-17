package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().gotoHomePage();
        if (app.db().contacts().size() == 0){
            app.contact().create(new UserData()
                    .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withHomePhone("123456789").withEmail("test@test.com"));
        }
    }
    @Test
    public void testContactDeletion() throws InterruptedException {
        Contacts before = app.db().contacts();
        UserData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Thread.sleep(5000);
        app.contact().gotoHomePage();
        assertEquals(app.contact().count(),before.size()-1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
