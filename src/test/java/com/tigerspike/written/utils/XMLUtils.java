package com.tigerspike.written.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {

	public static Object[][] getDataArray(String filePath) throws Exception
	{
		String[][] dataArray = null;
		try {
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("order");

			int totalRows = 5;

			int totalCols = 2;

			dataArray = new String[totalRows][totalCols];

			for (int temp = 0; temp < nList.getLength(); temp++) {

				int j = 0;

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					dataArray[temp][j] = eElement.getElementsByTagName("item").item(0).getTextContent();

					dataArray[temp][j + 1] = eElement.getElementsByTagName("quantity").item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataArray;
	}
}