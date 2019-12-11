package test.xml.creators.impl;

import test.xml.creators.UserGroupXmlCreator;
import test.xml.entities.UserGroup;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JAXBUserGroupXmlCreator implements UserGroupXmlCreator {

    @Override
    public void create(String fileName, UserGroup userGroup) throws JAXBException, IOException {
        JAXBContext jaxbcontext = JAXBContext.newInstance(UserGroup.class);

        Marshaller marshaller = jaxbcontext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            marshaller.marshal(userGroup, outputStream);
        }
    }
}
