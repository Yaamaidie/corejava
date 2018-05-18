package volumn2.ch2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by HP-PC on 2018/5/16.
 */
public class XMLParserTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String fineName = XMLParserTest.class.getResource("f.xml").getFile();
        File file = new File(fineName);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        Element root = doc.getDocumentElement();
        System.out.println(root.getTagName());

        NodeList children = root.getChildNodes();
        System.out.println(children.getLength());
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                Element childElement = (Element) child;
                //文本
                Text textNode = (Text) childElement.getFirstChild();
                String text = textNode.getData().trim();
                System.out.println(text);
                //属性
                NamedNodeMap attributes = childElement.getAttributes();
                for (int j = 0; j < attributes.getLength(); j++) {
                    Node attr = attributes.item(j);
                    String name = attr.getNodeName();
                    String value = attr.getNodeValue();
                    System.out.println("[" + name + "=" + value + "]");
                }
            }
        }

    }
}
