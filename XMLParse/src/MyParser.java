import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MyParser {

	public static void main(String[] args) {
		int count = 0;
	      try {	
	    	 /* StringBuilder xmlStringBuilder = new StringBuilder();
	    	  xmlStringBuilder.append("<?xml version="1.0"?> <class> </class>");
	    	  ByteArrayInputStream input =  new ByteArrayInputStream(
	    	     xmlStringBuilder.toString().getBytes("UTF-8"));
	    	  
	    	  Element root = document.getDocumentElement();
	    	  
	    	  //returns specific attribute
	    	  getAttribute("attributeName"); 
	    	  //returns a Map (table) of names/values
	    	  getAttributes(); 
	    	  
	    	  //returns a list of subelements of specified name
	    	  getElementsByTagName("subelementName"); 
	    	  //returns a list of all child nodes
	    	  getChildNodes();*/
	    	  
	    	  // Specify file containing xml to be parsed
	          File inputFile = new File("iTunes Music Library1.xml");
	          
	          // Create Document Builder, Document is essentially the DOM tree
	          DocumentBuilderFactory dbFactory 
	             = DocumentBuilderFactory.newInstance();
	          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	          Document doc = dBuilder.parse(inputFile);
	          
	          // Get Root Element, i.e. plist
	          doc.getDocumentElement().normalize();
	          System.out.println("Root element :" 
	             + doc.getDocumentElement().getNodeName());
	          
	          // The first dict tags in the tree
	          NodeList nList = doc.getElementsByTagName("dict");
	          System.out.println("----------------------------");
	          for (int temp = 0; temp < nList.getLength(); temp++) {
	        	 count++;
	             Node nNode = nList.item(temp);
	             System.out.println("\nCurrent Element :" 
	                + nNode.getNodeName());
	             
	             // Track ID
	             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                System.out.println("Track ID : "+
	 	               eElement
	 	                .getElementsByTagName("integer")
	 	                .item(0)
	 	                .getTextContent());
	                
	                //eElement.getNextSibling();
	                System.out.println("Name : "+
	 	 	               eElement
	 	 	                .getElementsByTagName("string")
	 	 	                .item(0)
	 	 	                .getTextContent());
	                
	                //eElement.getElementsByTagName("string");
	                //if(eElement.getElementsByTagName("string").item(0).getTextContent().equals("Artist")){
	                System.out.println("Artist : "+
	 	 	               eElement
	 	 	                .getElementsByTagName("string")
	 	 	                .item(0)
	 	 	                .getTextContent());
	                //}
	                /*//System.out.println("Student roll no : " 
	                   //+ eElement.getAttribute("rollno"));
	                System.out.println("First Name : " 
	                   + eElement
	                   .getElementsByTagName("key")
	                   .item(0)
	                   .getTextContent());
	                System.out.println("Last Name : " 
	                + eElement
	                   .getElementsByTagName("integer")
	                   .item(0)
	                   .getTextContent());
	                System.out.println("Nick Name : " 
	                + eElement
	                   .getElementsByTagName("dict")
	                   .item(0)
	                   .getTextContent());
	                System.out.println("Marks : " 
	                + eElement
	                   .getElementsByTagName("marks")
	                   .item(0)
	                   .getTextContent());*/
	             }
	          }
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	      System.out.println(count);
	}

}
