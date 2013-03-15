package net.wgen.selenium;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	
	public static String[] nullallowed = {"email","startdate", "sectionname",
											"description", "unitname", "unitoverview",
											"imagelink", "school"};
	
	public static Collection<Object[]> parse(String type, String[] labels) {
		try {	
			File stocks = new File("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();
			
		    ArrayList<Object[]> data = new ArrayList<Object[]>();

			System.out.println("Creating Types: " + type);
			NodeList nodes = doc.getElementsByTagName(type);
			System.out.println(nodes.getLength());
			System.out.println("==========================");

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					ArrayList<String> unit = new ArrayList<String>();
					for (int k = 0; k < labels.length; k++) {
						NodeList nodelist = element.getElementsByTagName(labels[k]);
						System.out.println(labels[k]);
						try {
							unit.add( getValue( nodelist,nodelist.getLength()));
						} catch (NullPointerException e) {
							if (Arrays.asList(nullallowed).contains(labels[k])) {
								unit.add("");
							} else {
								System.out.println( "ERROR: " + labels[k] +" CANNOT be Empty!");
								System.out.println("Input your " + labels[k] + " for the " + (k+1) + " "+type + ":");
								String value = ActionTools.readFromSystemInput();
								unit.add(value);
							}
						}
					}
					data.add(unit.toArray());
				}
			}
			return data;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	
	private static String getValue(NodeList nodes, int length) throws Exception{ 
		String data = nodes.item(0).getFirstChild().getNodeValue();
		for (int i = 1 ; i < length; i++) {
			String value = nodes.item(i).getFirstChild().getNodeValue();
			data += ("," + value);
		}
		System.out.println(data);
		return data;

	}
	
//	public static void main(String[] args) {
//		String[] labels = {"name", "uid", "email","password","section"};
////		String[] labels = {"name","code"};
//		XMLParser.parse("teacher",labels);
//	}
}
