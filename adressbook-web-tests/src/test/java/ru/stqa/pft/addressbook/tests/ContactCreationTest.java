package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
      app.contact().gotoHomePage();
      List<UserData> before = app.contact().list();
      UserData user = new UserData()
              .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withPhoneHome("123456789").withEmail("test@test.com");
      app.contact().create(user);
      List<UserData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size()+1);

      before.add(user);
      Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
  //    before.sort(Comparator.comparing(m->m.getLastName()));
   //   after.sort(Comparator.comparing(m->m.getLastName()));
      Assert.assertEquals(before, after);

  }


}
