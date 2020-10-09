package xmlfile_worker;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import history.history;
import mainclass.myGUI;
import word_list.listwords;
import word_list.word;
public class xmlfile {
public static void readxmlfile(String filename, listwords words,int step) {
	try {
		File fi = new File(filename);
		DocumentBuilder builder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(fi);
		Element directionElement = doc.getDocumentElement();
		NodeList wordsList = directionElement.getElementsByTagName("record");
		for(int i= 0;i<wordsList.getLength();i=i+step) {
			Node node = wordsList.item(i);
			if(node.getNodeType() == node.ELEMENT_NODE) {
				Element sElement  =  (Element) node;
				words.listwords.add(new word(
						sElement.getElementsByTagName("word").item(0).getTextContent(),
						sElement.getElementsByTagName("meaning").item(0).getTextContent()));
			}
		}
	} catch (Exception e) {
		System.out.print("loi doc file xml");
	}
}
public static void writexmlfile(String filename, listwords words) throws TransformerException {
	DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
	try {
		DocumentBuilder builder = fac.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		//root
		Element root = doc.createElement("dictionary");
		doc.appendChild(root);
		//
		Element recordElement;
		Element wordElement;
		Element meanElement;
		for (word wordi : words.listwords) {
			recordElement = doc.createElement("record");
			root.appendChild(recordElement);
			wordElement = doc.createElement("word");
			wordElement.appendChild(doc.createTextNode(wordi.word));
			meanElement = doc.createElement("meaning");
			meanElement.appendChild(doc.createTextNode(wordi.meaning));
			recordElement.appendChild(wordElement);
			recordElement.appendChild(meanElement);
			
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(filename));
        transformer.transform(domSource, streamResult);
        System.out.println("Done creating XML File");
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void writexmlfile_history(String filename, List<history> history) throws TransformerException {
	DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
	try {
		DocumentBuilder builder = fac.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		//root
		Element root = doc.createElement("dictionary");
		doc.appendChild(root);
		//
		Element recordElement;
		Element dayElement;
		Element wordElement;
		for (history dayi : history) {
			recordElement = doc.createElement("record");
			root.appendChild(recordElement);
			dayElement = doc.createElement("date");
			dayElement.appendChild(doc.createTextNode(dayi.date));
			recordElement.appendChild(dayElement);
			for (String  stringi : dayi.wordslist) {
				wordElement = doc.createElement("word");
				wordElement.appendChild(doc.createTextNode(stringi));
				recordElement.appendChild(wordElement);
			}	
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(filename));
        transformer.transform(domSource, streamResult);
        System.out.println("Done creating XML File");
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
public static void readxml_history(String filename, List<history> list) {	
	try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		try {
			Document document = builder.parse(new File(filename));
			Element historyElement = document.getDocumentElement();
			NodeList recordList = historyElement.getElementsByTagName("record");
			for(int i=0;i<recordList.getLength();i++) {
				Node node = recordList.item(i);
				if(node.getNodeType() == node.ELEMENT_NODE) {
					// lay ngay
					Element recordElement  =  (Element) node;
					String dateString="";
					dateString=recordElement.getElementsByTagName("date").item(0).getTextContent();
					NodeList words = recordElement.getElementsByTagName("word");
					List<String> readedwords = new ArrayList<String>(); 
					//lay tu
					for(int j=0;j<words.getLength();j++) {
						Element temp =(Element) words.item(j);
						readedwords.add(temp.getTextContent());
					}			
				history aHistory = new history();
				aHistory.date=dateString;
				aHistory.wordslist=readedwords;
				list.add(aHistory);
			}
		
		
		
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
}
	public static void main(String[] args) {
		myGUI.createAndShowGUI();
	}
}
