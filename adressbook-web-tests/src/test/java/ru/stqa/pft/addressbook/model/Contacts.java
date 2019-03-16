package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Contacts(Collection<UserData> contacts) {
        this.delegate = new HashSet<UserData>(contacts);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<UserData>(contacts.delegate);

    }

    public Contacts() {
        this.delegate = new HashSet<UserData>();
    }

    public Contacts withAdded(UserData contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(UserData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

}

