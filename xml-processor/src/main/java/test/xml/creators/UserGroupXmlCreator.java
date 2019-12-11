package test.xml.creators;

import test.xml.entities.UserGroup;

public interface UserGroupXmlCreator {

    void create(String fileName, UserGroup userGroup) throws Exception;
}
