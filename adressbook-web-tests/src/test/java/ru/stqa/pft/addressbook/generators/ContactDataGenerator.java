package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<UserData> contacts = generateContacts(count);
        if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format "+format);
        }
    }

    private void saveAsXml(List<UserData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(UserData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<UserData> generateContacts(int count) {
        List<UserData> groups = new ArrayList<UserData>();
        for (int i = 0; i < count; i++){
            groups.add(new UserData().withFirstName(String.format("name %s", i))
                    .withLastName(String.format("Lastname %s", i)).withHomePhone(String.format("%s", i))
                    .withMobilePhone(String.format("%s", i)).withWorkPhone(String.format("%s", i))
                    .withAddress(String.format("address %s", i)).withAddress2(String.format("address2 %s", i))
                    .withEmail(String.format("email %s", i)).withEmail2(String.format("email2 %s", i))
                    .withEmail3(String.format("email3 %s", i)));
        }
        return groups;
    }

}
