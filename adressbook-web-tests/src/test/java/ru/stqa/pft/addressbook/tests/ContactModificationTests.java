package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() throws InterruptedException {
        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createAUser(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        }
        List<UserData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectUser(before.size()-1);
        app.getContactHelper().initUserModification();
        UserData user = new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com");
        app.getContactHelper().fillUserForm(user);
        app.getContactHelper().submitUserModification();
        Thread.sleep(1000);
        app.getContactHelper().gotoHomePage();
        List<UserData> after = app.getContactHelper().getContactList();
        //Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(user);
        before.sort(Comparator.comparing(m->m.getLastName()));
        after.sort(Comparator.comparing(m->m.getLastName()));
        Assert.assertEquals(before, after);
    }
}
