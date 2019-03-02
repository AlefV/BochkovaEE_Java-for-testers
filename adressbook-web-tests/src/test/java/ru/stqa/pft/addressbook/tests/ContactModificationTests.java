package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().gotoHomePage();
        if (! app.contact().isThereAUser()){
            app.contact().create(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        List<UserData> before = app.contact().list();
        UserData user = new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com");
        int index = before.size()-1;
        app.contact().modify(user, index);
        Thread.sleep(1000);
        app.contact().gotoHomePage();
        List<UserData> after = app.contact().list();
        //Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(user);
        Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        // before.sort(Comparator.comparing(m->m.getLastName()));
       // after.sort(Comparator.comparing(m->m.getLastName()));
        Assert.assertEquals(before, after);
    }

}
