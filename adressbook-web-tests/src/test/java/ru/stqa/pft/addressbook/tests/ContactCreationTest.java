package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
      List<UserData> before = app.getContactHelper().getContactList();
      UserData user = new UserData("Petr", "Ivanov", "address", "123456789", "test@test.com");
      app.getContactHelper().createAUser(user);
      List<UserData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size()+1);

      before.add(user);

      before.sort(Comparator.comparing(m->m.getLastName()));
      after.sort(Comparator.comparing(m->m.getLastName()));
      Assert.assertEquals(before, after);

  }


}
