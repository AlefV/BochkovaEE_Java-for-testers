package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTest extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    app.getContactHelper().createAUser(new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com"));
   }


}
