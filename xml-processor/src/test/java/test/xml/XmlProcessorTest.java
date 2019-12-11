package test.xml;

import org.junit.Test;
import test.xml.creators.UserGroupXmlCreator;
import test.xml.creators.XmlCreatorFactory;
import test.xml.entities.BreakfastMenu;
import test.xml.entities.Food;
import test.xml.entities.User;
import test.xml.entities.UserGroup;
import test.xml.parsers.BreakfastMenuXmlParser;
import test.xml.parsers.XmlParserFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class XmlProcessorTest {

    @Test
    public void xmlCreatorTest() throws Exception {
        UserGroupXmlCreator userGroupXmlCreator = XmlCreatorFactory.getUserGroupXmlCreator(ImplType.DOM_PARSER);
        UserGroup userGroup = prepareUserGroup();

        userGroupXmlCreator.create("CreatedUserGroup.xml", userGroup);
    }

    @Test
    public void xmlParserTest() throws Exception {
        BreakfastMenuXmlParser breakfastMenuXmlParser = XmlParserFactory.getBreakfastMenuXmlParser(ImplType.DOM_PARSER);

        BreakfastMenu breakfastMenu = breakfastMenuXmlParser.parse("BreakfastMenu.xml");

        saveParsedBreakfastMenuToFile(breakfastMenu);
    }

    private UserGroup prepareUserGroup() {
        return new UserGroup(new ArrayList<User>() {{
            add(new User("Sophia", "Merly", "+12879335587"));
            add(new User("Sophia", "Merly", "+12879335587"));
        }});
    }

    private void saveParsedBreakfastMenuToFile(BreakfastMenu breakfastMenu) throws IOException {
        try (Writer writer = new BufferedWriter(new FileWriter("ParsedBreakfastMenu.txt"))) {
            writer.write("Parsed BreakfastMenu:\n");

            for (Food food: breakfastMenu.getFoodList())
                writer.write(food.toString() + "\n");
        }
    }
}
