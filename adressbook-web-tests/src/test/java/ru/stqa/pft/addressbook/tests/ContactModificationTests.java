package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createAUser(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        }
        List<UserData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectUser(before.size()-1);
        app.getContactHelper().initUserModification();
        app.getContactHelper().fillUserForm(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        app.getContactHelper().submitUserModification();
        List<UserData> after = app.getContactHelper().getContactList();
    }
}
