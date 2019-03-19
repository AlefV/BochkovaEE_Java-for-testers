package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.db().groups().size() ==0){
            app.group().create(new GroupData().withName("test1"));
        }
    }


    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(UserData.class);
            List<UserData> contacts = (List<UserData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(UserData contact) throws Exception {
      Groups groups = app.db().groups();
      app.contact().gotoHomePage();
      Contacts before = app.db().contacts();
      contact.inGroup(groups.iterator().next());
      app.contact().create(contact);
      assertThat(app.contact().count(), equalTo(before.size()+1));
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));

  }


}
