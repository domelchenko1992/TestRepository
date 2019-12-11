package test.xml.creators;

import test.xml.ImplType;
import test.xml.creators.impl.DOMParserUserGroupXmlCreator;
import test.xml.creators.impl.JAXBUserGroupXmlCreator;

import java.util.HashMap;
import java.util.Map;

public class XmlCreatorFactory {

    private static Map<ImplType, UserGroupXmlCreator> USER_GROUP_CREATOR_MAP = new HashMap<ImplType, UserGroupXmlCreator>(){{
        put(ImplType.DOM_PARSER, new DOMParserUserGroupXmlCreator());
        put(ImplType.JAXB, new JAXBUserGroupXmlCreator());
    }};

    public static UserGroupXmlCreator getUserGroupXmlCreator(ImplType implType) {
        UserGroupXmlCreator userGroupXmlCreator = USER_GROUP_CREATOR_MAP.get(implType);

        if (userGroupXmlCreator == null)
            throw new IllegalArgumentException(String.format("ImplType [%s] not supported", implType));

        return userGroupXmlCreator;
    }
}
