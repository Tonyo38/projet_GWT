package com.trollcustom.shared;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class qui permet d'utiliser le fichier XML
 * @author Tonyo
 *
 */
public class XMLtools {

	public XMLtools() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Fonction qui écrit une liste de troll dans un fichier XML
	 * @param listTroll
	 */
	public static void ecrire(ArrayList<Troll> listTroll, String urlSource){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Liste_Troll");
			doc.appendChild(rootElement);
			for(int i=0;i<listTroll.size();i++){
				// Troll elements
				Element trollElement = doc.createElement("Troll");
				rootElement.appendChild(trollElement);
				
				// set attribute to troll elemens
				
				Attr attr = doc.createAttribute("Id");
				attr.setValue("" +listTroll.get(i).getId());
				trollElement.setAttributeNode(attr);
				
				Element elem = doc.createElement("Nom");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getNom()));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Race");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getRace()));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Attaque");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getAttaque()+""));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Degats");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getDegats()+""));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Regeneration");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getRegeneration()+""));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Esquive");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getEsquive()+""));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Vie");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getVie()+""));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Competence1");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getComptence1()+""));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Competence2");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getComptence2()+""));
				trollElement.appendChild(elem);
				
				elem = doc.createElement("Url");
				elem.appendChild(doc.createTextNode(listTroll.get(i).getURL()));
				trollElement.appendChild(elem);
		 
					
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DOMSource source = new DOMSource(doc);		
			
			StreamResult result = new StreamResult(new File(urlSource));
			// Output to console for testing
			//StreamResult result = new StreamResult(System.out);
	 
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Fonction qui lit dans un fichier XML une liste de troll et la renvoi
	 * @return
	 */
	public static ArrayList<Troll> lire(String urlSource){

			ArrayList<Troll> list = new ArrayList<Troll>();
			File file = new File(urlSource);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = null;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document doc = null;
			try {
				doc = dBuilder.parse(file);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
		 
			
			NodeList nList = doc.getElementsByTagName("Troll");
		 
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				
				Node nNode = nList.item(temp);
		
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					// On récupère nos éléments
					Element eElement = (Element) nNode;
					int att = Integer.parseInt(eElement.getElementsByTagName("Attaque").item(0).getTextContent());
					int deg = Integer.parseInt(eElement.getElementsByTagName("Degats").item(0).getTextContent());
					int esq = Integer.parseInt(eElement.getElementsByTagName("Esquive").item(0).getTextContent());
					int reg = Integer.parseInt(eElement.getElementsByTagName("Regeneration").item(0).getTextContent());
					int vie = Integer.parseInt(eElement.getElementsByTagName("Vie").item(0).getTextContent());
					int comp1 = Integer.parseInt(eElement.getElementsByTagName("Competence1").item(0).getTextContent());
					int comp2 = Integer.parseInt(eElement.getElementsByTagName("Competence2").item(0).getTextContent());
					String nom = eElement.getElementsByTagName("Nom").item(0).getTextContent();
					String race = eElement.getElementsByTagName("Race").item(0).getTextContent();
					String url =eElement.getElementsByTagName("Url").item(0).getTextContent();
					int id = Integer.parseInt(eElement.getAttribute("Id"));
					
					// On prépare notre liste
					list.add(new Troll(att,deg,esq,reg,vie,comp1,comp2,nom,race,url,id));
					
				}
				}

		return list;
	}
	
	/**
	 * Fonction qui écrit l'url de l'image uploaded dans un XML
	 * @param url
	 */
	public static void ecrireImg(String url,String urlFichier){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("ImageUploaded");
			doc.appendChild(rootElement);
		
			Element elemImg = doc.createElement("Image");
			rootElement.appendChild(elemImg);
			
			Element elem = doc.createElement("Url");
			elem.appendChild(doc.createTextNode(url));
			elemImg.appendChild(elem);
		 
					
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transformerFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DOMSource source = new DOMSource(doc);		
			
			StreamResult result = new StreamResult(new File(urlFichier));
			// Output to console for testing
			//StreamResult result = new StreamResult(System.out);
	 
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Fonction qui va lire l'url de l'image dans le fichier XML
	 * @return
	 */
	public static String lireImg(String url){

		File file = new File(url);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
	 
		
		NodeList nList = doc.getElementsByTagName("Image");
	 
	 
		for (int temp = 0; temp < nList.getLength(); temp++) {
			
			Node nNode = nList.item(temp);
	
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				// On récupère nos éléments
				Element eElement = (Element) nNode;
				
				String urlImg = eElement.getElementsByTagName("Url").item(0).getTextContent();
				return urlImg;
			}
			}

	return null;
}
}
