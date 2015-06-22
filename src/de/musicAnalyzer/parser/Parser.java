package de.musicAnalyzer.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	
	public static List<String> listOfNotes = new ArrayList<String>();
	public static List<String> worksList = new ArrayList<String>();

	public static int duration;
	public static int division;
	public static int beat;
	
	
	public static void parse(File file) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		
		NodeList measureList = doc.getElementsByTagName("measure");
		
		String attributesString = "";
		for (int i = 0; i < measureList.getLength(); i++) {
			Node measureNode = measureList.item(i);
			Element measureElement = (Element) measureNode;
			
			String taktNumber = measureElement.getAttribute("number");
			System.out.println("Takt " + taktNumber);
			
			NodeList stepList = measureElement.getElementsByTagName("step");
			NodeList noteList = measureElement.getElementsByTagName("note");
			NodeList divisionList = measureElement.getElementsByTagName("divisions");
			NodeList beatsList = measureElement.getElementsByTagName("beats");
			NodeList durationList = measureElement.getElementsByTagName("duration");
			
			String divisionString = ""; 
			String beatsString = "";
			
			for (int j = 0; j < noteList.getLength(); j++) {
				Node noteNode = noteList.item(j);
				Element noteElement = (Element) noteNode;

				// get duration
				Node durationNode = durationList.item(j);
				Element durationElement = (Element) durationNode;
				String durationString = durationElement.getTextContent();
				
				// get pitch/step
				Node stepNode = stepList.item(j);
				Element stepElement = (Element) stepNode;
				String stepString = "";
				
				// get first attribute
				Node divisionNode = divisionList.item(0);
				Element divisionElement = (Element) divisionNode;
				
				Node beatsNode = beatsList.item(0);
				Element beatsElement = (Element) beatsNode;
				
				if(measureElement.getElementsByTagName("attributes").getLength() > 0){
					divisionString = divisionElement.getTextContent();
					beatsString = beatsElement.getTextContent();
					beat = Integer.parseInt(beatsString);
					division = Integer.parseInt(divisionString);
				}
				
//				String beatsString = beatsElement.getTextContent();
				
//				if(measureElement.getElementsByTagName("attributes").getLength() > 0){
//					divisionString = attributesElement.getTextContent();
//				}
				
				if(noteElement.getElementsByTagName("pitch").getLength() > 0){
					stepString = stepElement.getTextContent(); 
				}
				else {
					stepString = "pause ";
				}
				duration = Integer.parseInt(durationString);
			
				
				System.out.println(stepString + " " + division * beat / duration);
			}
			
			
		}
	}
}
