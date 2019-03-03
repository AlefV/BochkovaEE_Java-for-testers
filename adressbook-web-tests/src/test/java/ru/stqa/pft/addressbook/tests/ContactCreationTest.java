package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
      app.contact().gotoHomePage();
      Set<UserData> before = app.contact().all();
      UserData user = new UserData()
              .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withPhoneHome("123456789").withEmail("test@test.com");
      app.contact().create(user);
      Set<UserData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size()+1);

      user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt());
      before.add(user);
      Assert.assertEquals(before, after);

  }


}
