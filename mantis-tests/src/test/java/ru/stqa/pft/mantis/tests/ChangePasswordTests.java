package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

public class ChangePasswordTests extends TestBase {

   // @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangePassword(){
        Users users = app.db().users();
        UserData user = users.iterator().next();
        System.out.println(user);
    }

 //   @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
