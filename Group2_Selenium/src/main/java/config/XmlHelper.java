package config;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlHelper {

	public static String getVariable(String nameVariable) {

		try {
			File filesXML = new File(
					"../Group2_Selenium/src/main/resources/variables.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(filesXML);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("variable");

			String text = "";

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (eElement.getAttribute("name").equals(nameVariable)) {

						text = eElement.getElementsByTagName("value").item(0)
								.getTextContent();

					}

				}
			}

			return text;

		} catch (Exception e) {
			return e.getMessage();	
		}

	}

}
