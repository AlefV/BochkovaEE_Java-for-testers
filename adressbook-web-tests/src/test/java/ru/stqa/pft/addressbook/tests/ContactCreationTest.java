package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
      app.contact().gotoHomePage();
      Contacts before = app.contact().all();
      UserData user = new UserData()
              .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withPhoneHome("123456789").withEmail("test@test.com");
      app.contact().create(user);
      assertThat(app.contact().count(), equalTo(before.size()+1));
      Contacts after = app.contact().all();
      assertThat(after, equalTo(
              before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));

  }


}
