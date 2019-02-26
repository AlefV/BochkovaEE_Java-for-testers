package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws InterruptedException {
        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createAUser(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        }
        List<UserData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectUser(before.size()-1);
        app.getContactHelper().initUserDeletion();
        app.getContactHelper().acceptDeletion();
        Thread.sleep(5000);
        app.getContactHelper().gotoHomePage();
        List<UserData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(before.size()-1);
      //  before.sort(Comparator.comparing(m->m.getLastName()));
      //  after.sort(Comparator.comparing(m->m.getLastName()));
        Assert.assertEquals(before, after);
    }
}
