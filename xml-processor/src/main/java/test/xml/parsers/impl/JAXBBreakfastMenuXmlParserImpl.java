package test.xml.parsers.impl;

import test.xml.entities.BreakfastMenu;
import test.xml.parsers.BreakfastMenuXmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JAXBBreakfastMenuXmlParserImpl implements BreakfastMenuXmlParser {

    @Override
    public BreakfastMenu parse(String fileName) throws JAXBException, IOException {
        JAXBContext jaxbcontext = JAXBContext.newInstance(BreakfastMenu.class);
        Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            return (BreakfastMenu) unmarshaller.unmarshal(inputStream);
        }
    }
}
