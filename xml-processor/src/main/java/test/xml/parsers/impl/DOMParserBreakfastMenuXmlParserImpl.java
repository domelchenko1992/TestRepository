package test.xml.parsers.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import test.xml.entities.BreakfastMenu;
import test.xml.entities.Food;
import test.xml.parsers.BreakfastMenuXmlParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DOMParserBreakfastMenuXmlParserImpl implements BreakfastMenuXmlParser {

    @Override
    public BreakfastMenu parse(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            Document document = documentBuilder.parse(inputStream);
            document.getDocumentElement().normalize();

            return parseBreakfastMenuElement(document);
        }
    }

    private BreakfastMenu parseBreakfastMenuElement(Document document) {
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        List<Food> foodList = new ArrayList<>();

        NodeList foodNodeList = document.getElementsByTagName("food");

        for (int i = 0; i < foodNodeList.getLength(); i++) {
            Node foodNode = foodNodeList.item(i);

            if (foodNode.getNodeType() == Node.ELEMENT_NODE) {
                Element foodElement = (Element) foodNode;

                foodList.add(parseFoodElement(foodElement));
            }
        }

        breakfastMenu.setFoodList(foodList);

        return breakfastMenu;
    }

    private Food parseFoodElement(Element foodElement) {
        Food food = new Food();
        food.setName(foodElement.getElementsByTagName("name").item(0).getTextContent());
        food.setPrice(foodElement.getElementsByTagName("price").item(0).getTextContent());
        food.setDescription(foodElement.getElementsByTagName("description").item(0).getTextContent());
        food.setCalories(Integer.parseInt(foodElement.getElementsByTagName("calories").item(0).getTextContent()));

        return food;
    }
}
