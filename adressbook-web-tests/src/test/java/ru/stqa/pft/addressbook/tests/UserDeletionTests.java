package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

    @Test
    public void testUserDeletion(){
        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createAUser(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
        }
        app.getContactHelper().selectUser();
        app.getContactHelper().initUserDeletion();
        app.getContactHelper().acceptDeletion();
    }
}
