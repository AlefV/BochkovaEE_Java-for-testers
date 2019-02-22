package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createAUser(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        }
        app.getContactHelper().selectUser();
        app.getContactHelper().initUserModification();
        app.getContactHelper().fillUserForm(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        app.getContactHelper().submitUserModification();
    }
}
