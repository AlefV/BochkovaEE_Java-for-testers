package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.contact().gotoHomePage();
        if (app.db().contacts().size() == 0){
            app.contact().create(new UserData()
                    .withFirstName("Petr").withLastName("Ivanov").withAddress("address").withHomePhone("123456789").withEmail("test@test.com"));
        }
    }
    @Test
    public void testContactAddGroup(){
        Contacts contacts = app.db().contacts();
        UserData contactToAddGroup = contacts.iterator().next();
        Groups before = contactToAddGroup.getGroups();
        Groups groupsToAdd = checkGroupToAdd(contactToAddGroup);
        GroupData groupToAdd = groupsToAdd.iterator().next();
        app.contact().addContactToGroup(contactToAddGroup, groupToAdd);
        contactToAddGroup = app.db().contact(contactToAddGroup.getId());
        Groups after = contactToAddGroup.getGroups();
        assertThat(after, equalTo(
                before.withAdded(groupToAdd)));

    }

    private Groups checkGroupToAdd(UserData contactToAddGroup) {
        Groups contactGroups = contactToAddGroup.getGroups();
        Groups groupsToAdd = app.db().groups();
        groupsToAdd.removeAll(contactGroups);
        if(groupsToAdd.size() == 0){
            GroupData newGroup = new GroupData().withName("testAdd");
            app.goTo().groupPage();
            app.group().create(newGroup);
            groupsToAdd = app.db().groups();
            groupsToAdd.removeAll(contactGroups);
        }
        System.out.println(groupsToAdd);
        return groupsToAdd;
    }
}
