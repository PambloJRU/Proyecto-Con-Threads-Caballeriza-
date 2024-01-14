package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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


public class FileXML {

	public FileXML() {}	
	
	public void createXML(String objectName,String address,String fileName) {
		
		File file = new File(address+fileName+".xml");
		
		if(!file.exists()){
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				
				Document doc = dBuilder.newDocument();
				
				Element rootElement = doc.createElement(objectName);
				doc.appendChild(rootElement);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				
				DOMSource source = new DOMSource(doc);
				
				StreamResult result = new StreamResult(file);
				transformer.transform(source,result);
				
				System.out.println("Archivo creado");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void writeXML(String FileName,String elementType,String[] dataName, String[] data) {
		
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(new File(FileName));
			doc.getDocumentElement().normalize();
			
			Element rootElement = doc.getDocumentElement();
			
			Element ele = doc.createElement(elementType);
			rootElement.appendChild(ele);
			
			Attr attr = doc.createAttribute(dataName[0]);
			attr.setValue(data[0]);
			ele.setAttributeNode(attr);
			
			for(int i=0;i<data.length;i++) {
				
				Element dato = doc.createElement(dataName[i]);
				
				dato.appendChild(doc.createTextNode(data[i]));
				
				ele.appendChild(dato);
				
			}
			//escribimos el contenido en un archvio xml
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			
			StreamResult result = new StreamResult(new File(FileName));
			transformer.transform(source, result);
			
			System.out.println("Registro Guardado");
			
		}catch(ParserConfigurationException pce) {
			
			pce.printStackTrace();
			
		}catch(SAXException e) {
			
			e.printStackTrace();
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}catch(TransformerConfigurationException e) {
			
			e.printStackTrace();
			
		}catch(TransformerException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
public void readXML(String address, String elementType) {
		
		try {
			File inputFile = new File(address);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Raíz de los elementos: "+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName(elementType);
			System.out.println("-------------");
			
			for(int i=0;i <nList.getLength(); i++) {
				Node nNode = nList.item(i);
				System.out.println("\nDatos de las facturas: " + nNode.getNodeName());
				
				if(nNode.getNodeType()==Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Id: " + eElement.getAttribute("id"));
					System.out.println("Cedula Cliente: "+ eElement.getElementsByTagName("idCliente").
							item(0).getTextContent());
					System.out.println("Total: " + eElement.getElementsByTagName("total").
							item(0).getTextContent());
					System.out.println("Tipo de pago: " + eElement.getElementsByTagName("paymentType").
							item(0).getTextContent());
					System.out.println("Total: " + eElement.getElementsByTagName("date").
							item(0).getTextContent());
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

public int CountLinesXML(String address, String elementType) {
		int lines=0;
	try {
		File inputFile = new File(address);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		
		System.out.println("Raíz de los elementos: "+ doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName(elementType);
		System.out.println("-------------");
		
		for(int i=0;i <nList.getLength(); i++) {
			lines++;
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return lines;
}
	
}
