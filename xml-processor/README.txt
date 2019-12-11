This project has two implementations of creating and parsing XML file with specified structure: DOM parser and JAXB.
JAXB implementation was added for comparing approaches to resolving task.

Entry point of project is "XmlProcessorTest" class with "xmlCreatorTest" and "xmlParserTest" methods.
"xmlCreatorTest" method creates "CreatedUserGroup.xml" file with XML representation of input Java object.
"xmlParserTest" method creates "ParsedBreakfastMenu.txt" file with string representation of output Java object.