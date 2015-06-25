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

public class Parser1 {
	

	public static int duration;
	public static int division;
	public static int beat;
	public static int length;
	
	public static void parse(File file) throws ParserConfigurationException, SAXException, IOException {
	
		List<String> listOfNotes = new ArrayList<String>();
		List<String> worksList = new ArrayList<String>();
		List<String> chordList = new ArrayList<String>();
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		
		NodeList measureList = doc.getElementsByTagName("measure");
		
		String attributesString = "";
		String divisionString = ""; 
		String beatsString = "";
		String durationString = "";
		
		for (int i = 0; i < measureList.getLength(); i++) {
			Node measureNode = measureList.item(i);
			Element measureElement = (Element) measureNode;
			
			String taktNumber = measureElement.getAttribute("number");
//			System.out.println("Takt " + taktNumber);
			
			NodeList noteList = measureElement.getElementsByTagName("note");
			NodeList beatsList = measureElement.getElementsByTagName("beats");
			NodeList divisionsList = measureElement.getElementsByTagName("divisions");
			NodeList chordsList = measureElement.getElementsByTagName("chord");
			
			Node beatsNode, divisionNode, chordNode, noteNode, nextNoteNode;
			Element beatsElement = null, divisionElement = null, chordElement = null, nextNoteElement = null;
			
			if(measureElement.getElementsByTagName("attributes").getLength() > 0){
				beatsNode = beatsList.item(0);
				beatsElement = (Element) beatsNode;
				
				divisionNode = divisionsList.item(0);
				divisionElement = (Element) divisionNode;
			}
			
			
			for (int j = 0; j < noteList.getLength(); j++) {
				
				// Anfang Taktart bestimmen 
				if(measureElement.getElementsByTagName("beats").getLength() > 0){
					beatsString = beatsElement.getTextContent();
				}
				if(measureElement.getElementsByTagName("divisions").getLength() > 0){
					divisionString = divisionElement.getTextContent();
				}
				beat = Integer.parseInt(beatsString);
				division = Integer.parseInt(divisionString);
				// Ende Taktart bestimmen
				
				noteNode = noteList.item(j);
				Element noteElement = (Element) noteNode;
				
				// Chord und nextChord
				chordNode = chordsList.item(j);
				chordElement = (Element) chordNode;
				nextNoteNode = noteList.item(j + 1);
				nextNoteElement = (Element) nextNoteNode;
				
				NodeList stepList = noteElement.getElementsByTagName("step");
				String stepString = "";
				NodeList durationList = noteElement.getElementsByTagName("duration");

				Node stepNode = stepList.item(0);
				Element stepElement = (Element) stepNode;

				Node durationNode = durationList.item(0);
				Element durationElement = (Element) durationNode;
				
				durationString = durationElement.getTextContent();
				duration = Integer.parseInt(durationString);
				
				length = division * beat / duration;
				
				if (noteElement.getElementsByTagName("step").getLength() > 0) {
					stepString = stepElement.getTextContent();
				}
				if (noteElement.getElementsByTagName("rest").getLength() > 0) {
					stepString = "pause";
				}
				stepString = stepString + "_" + length;

				// TODO: chord Abfrage
				if (j < noteList.getLength() -1) {
					
					// 1. ich bin kein chord, mein nächster auch nicht
					if((noteElement.getElementsByTagName("chord").getLength() == 0) & nextNoteElement.getElementsByTagName("chord").getLength() == 0){
//						listOfNotes.add(stepString);
						worksList.add(stepString);
//						listOfNotes.clear();
					}
					
					// 2. ich bin kein chord, mein nächster ist einer
					if((noteElement.getElementsByTagName("chord").getLength() == 0) & nextNoteElement.getElementsByTagName("chord").getLength() > 0){
						listOfNotes.add(stepString);
					}
					
					// 3. ich bin ein chord, mein nächster ist auch einer
					if((noteElement.getElementsByTagName("chord").getLength() > 0) & nextNoteElement.getElementsByTagName("chord").getLength() > 0){
						listOfNotes.add(stepString);
					}
					
					// 4. ich bin ein chord, mein nächster ist keiner
					if((noteElement.getElementsByTagName("chord").getLength() > 0) & nextNoteElement.getElementsByTagName("chord").getLength() == 0){
						listOfNotes.add(stepString);
						worksList.addAll(listOfNotes);
						listOfNotes.clear();
					}
					
				}
				// NullPointerException abfangen
				else{
					listOfNotes.add(stepString);
					worksList.addAll(listOfNotes);
					listOfNotes.clear();
				}
				
				/*
				 * Zu unterscheidende Fälle: 
				 * 1. ich bin kein chord, mein nächster auch nicht
				 * 
				 * 2. ich bin kein chord, mein nächster ist einer
				 * 
				 * 3. ich bin ein chord, mein nächster ist auch einer
				 * 
				 * 4. ich bin ein chord, mein nächster ist keiner
				 */

				// TODO: Hände <staff> unterscheiden


//				System.out.println(stepString + "-" + length + " ");
//				if(chordElement != null){
//					System.out.println("chord");
//				}

//				System.out.println(listOfNotes);
			}
		}
		System.out.println(worksList);
	}
}
