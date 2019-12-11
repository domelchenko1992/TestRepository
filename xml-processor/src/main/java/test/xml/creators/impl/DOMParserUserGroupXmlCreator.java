package test.xml.creators.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import test.xml.creators.UserGroupXmlCreator;
import test.xml.entities.User;
import test.xml.entities.UserGroup;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DOMParserUserGroupXmlCreator implements UserGroupXmlCreator {

    @Override
    public void create(String fileName, UserGroup userGroup) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        document.appendChild(createUserGroupElement(document, userGroup));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource domSource = new DOMSource(document);

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            StreamResult streamResult = new StreamResult(outputStream);

            transformer.transform(domSource, streamResult);
        }
    }

    private static Element createUserGroupElement(Document document, UserGroup userGroup) {
        Element userGroupElement = document.createElement("group");

        for (User user: userGroup.getUserList()) {
            Element userElement = createUserElement(document, user);

            userGroupElement.appendChild(userElement);
        }

        return userGroupElement;
    }

    private static Element createUserElement(Document document, User user) {
        Element userElement = document.createElement("user");

        Element nameElement = document.createElement("name");
        nameElement.setTextContent(user.getName());

        Element surnameElement = document.createElement("surname");
        surnameElement.setTextContent(user.getSurname());

        Element phoneElement = document.createElement("phone");
        phoneElement.setTextContent(user.getPhone());

        userElement.appendChild(nameElement);
        userElement.appendChild(surnameElement);
        userElement.appendChild(phoneElement);

        return userElement;
    }
}
