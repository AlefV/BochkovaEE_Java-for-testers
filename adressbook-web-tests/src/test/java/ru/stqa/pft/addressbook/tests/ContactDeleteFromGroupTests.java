package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.db().groups().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
        app.contact().gotoHomePage();
        if (app.db().contacts().size() == 0){
            app.contact().create(new UserData()
                    .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withHomePhone("123456789").withEmail("test@test.com"));
        }
    }

    @Test
    public void testContactDeleteFromGroup(){
        Contacts contacts = app.db().contacts();
        UserData contactToDeleteGroup = contacts.iterator().next();
        Groups before = contactToDeleteGroup.getGroups();
        if(before.size()==0){
            Groups allGroups = app.db().groups();
            app.contact().addContactToGroup(contactToDeleteGroup, allGroups.iterator().next());
            contactToDeleteGroup = app.db().contact(contactToDeleteGroup.getId());
            before = contactToDeleteGroup.getGroups();
        }
        GroupData groupToDelete = before.iterator().next();
        app.contact().deleteContactFromGroup(contactToDeleteGroup,groupToDelete);
        contactToDeleteGroup = app.db().contact(contactToDeleteGroup.getId());
        Groups after = contactToDeleteGroup.getGroups();
        assertThat(after, equalTo(before.without(groupToDelete)));
    }
}
