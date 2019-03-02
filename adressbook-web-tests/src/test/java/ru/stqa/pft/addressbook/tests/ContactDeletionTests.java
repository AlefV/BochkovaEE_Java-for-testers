package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().gotoHomePage();
        if (! app.contact().isThereAUser()){
            app.contact().create(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        }
    }
    @Test
    public void testContactDeletion() throws InterruptedException {
        List<UserData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().delete(index);
        Thread.sleep(5000);
        app.contact().gotoHomePage();
        List<UserData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(index);
      //  before.sort(Comparator.comparing(m->m.getLastName()));
      //  after.sort(Comparator.comparing(m->m.getLastName()));
        Assert.assertEquals(before, after);
    }

}
