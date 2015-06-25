package de.musicAnalyzer.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Parser {

	public static void parser(File file) throws ParserConfigurationException, SAXException, IOException{
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	dbf.setValidating(false);
	DocumentBuilder db = dbf.newDocumentBuilder();
	Document doc = db.parse(file);
	
	
	
	}
}
