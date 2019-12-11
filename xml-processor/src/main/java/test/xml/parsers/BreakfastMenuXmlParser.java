package test.xml.parsers;

import test.xml.entities.BreakfastMenu;

public interface BreakfastMenuXmlParser {

    BreakfastMenu parse(String fileName) throws Exception;
}
