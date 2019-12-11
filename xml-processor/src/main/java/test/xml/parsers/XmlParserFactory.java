package test.xml.parsers;

import test.xml.ImplType;
import test.xml.parsers.impl.DOMParserBreakfastMenuXmlParserImpl;
import test.xml.parsers.impl.JAXBBreakfastMenuXmlParserImpl;

import java.util.HashMap;
import java.util.Map;

public class XmlParserFactory {

    private static Map<ImplType, BreakfastMenuXmlParser> BREAKFAST_MENU_PARSER_MAP = new HashMap<ImplType, BreakfastMenuXmlParser>(){{
        put(ImplType.DOM_PARSER, new DOMParserBreakfastMenuXmlParserImpl());
        put(ImplType.JAXB, new JAXBBreakfastMenuXmlParserImpl());
    }};

    public static BreakfastMenuXmlParser getBreakfastMenuXmlParser(ImplType implType) {
        BreakfastMenuXmlParser breakfastMenuXmlParser = BREAKFAST_MENU_PARSER_MAP.get(implType);

        if (breakfastMenuXmlParser == null)
            throw new IllegalArgumentException(String.format("ImplType [%s] not supported", implType));

        return breakfastMenuXmlParser;
    }
}
