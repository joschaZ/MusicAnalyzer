package de.musicAnalyzer.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.musicAnalyzer.parser.Parser1;

public class MainApplication {

	public static void main(String[] args) {
		
		String test = "src/sheets/xmlTest.xml";
		String reunion = "src/sheets/Reunion.xml";
		String revStud= "src/sheets/Revolutionary Study.xml";
		
		File file = new File(revStud);
		try {
			
			
			Parser1.parse(file);
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
