package com.dit.parser;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dit.dao.TrackDAO;
import com.dit.entities.PlayList;
import com.dit.entities.Track;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class MyParser implements Parser{
	
	@EJB
	private TrackDAO dao;
	
	int count = 0;
	private String trackId = null, name = null, artist = null, album = null;
	private String ppid = null, playListName = null, playListId = null, playListTrackId = null;
	private Set<Track> tracks = new HashSet<Track>();
	private Set<PlayList> playLists = new HashSet<PlayList>();
	Track track = new Track();
	PlayList playlist = new PlayList();

	public void parse() {
	      try {	
	    	  // Specify file containing xml to be parsed, must be in wildfly bin folder
	          File inputFile = new File("iTunes Music Library1.xml");
	          
	          // Create Document Builder, Document is essentially the DOM tree
	          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	          dbFactory.setIgnoringElementContentWhitespace(true);//For number 2
	          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	          Document doc = dBuilder.parse(inputFile);
	          
	          // Get Root Element, i.e. plist
	          doc.getDocumentElement().normalize();
	          System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	          Element root = doc.getDocumentElement();
	          
	          // The first dictinary tags in the tree, FirstLayer
	          Node firstDictionary = root.getElementsByTagName("dict").item(0);
	          NodeList firstDictionaryList = firstDictionary.getChildNodes();
	          
	          // The first array tags in the tree, FirstLayer
	          Node firstArray = root.getElementsByTagName("array").item(0);
	          NodeList firstArrayList = firstArray.getChildNodes();
	          
	          // Check if it is a text node for number 2 to work, i.e. it has an additional space not
	          // found in 1 & 3
	          System.out.println("----------------------------");
	          for (int i = 0; i < firstDictionaryList.getLength(); i++) {
	             Node nNode = firstDictionaryList.item(i);
	             if(nNode.getNodeName().equals("dict")){
	            	 Element iElement = (Element) nNode;
	            	 NodeList secondDictionaryList = iElement.getChildNodes();
	            	 for (int j = 0; j < secondDictionaryList.getLength(); j++) {
	            		 Node nNode2 = secondDictionaryList.item(j);
	            		 if(nNode2.getNodeName().equals("dict")){
	            			 Element jElement = (Element) nNode2;
            				 NodeList thirdDictionaryList = jElement.getChildNodes();
            				 for (int k = 0; k < thirdDictionaryList.getLength(); k++) {
            					 Node nNode3 = thirdDictionaryList.item(k);
            					 if(nNode3.getTextContent().equals("Track ID")){
            						 Element kElement = (Element) nNode3;
            						 trackId = kElement.getNextSibling().getTextContent();
    	            			 }
            					 if(nNode3.getTextContent().equals("Name")){
            						 Element kElement = (Element) nNode3;
            						 name = kElement.getNextSibling().getTextContent();
    	            			 }
            					 if(nNode3.getTextContent().equals("Artist")){
            						 Element kElement = (Element) nNode3;
            						 artist = kElement.getNextSibling().getTextContent();
    	            			 }
            					 if(nNode3.getTextContent().equals("Album")){
            						 Element kElement = (Element) nNode3;
            						 album = kElement.getNextSibling().getTextContent();
    	            			 }
            				 }
            				 Track t = new Track(name, artist, album, trackId);
        					 tracks.add(t);
	            		 }
	            	 }
	             }
	          }
	          
	          // Playlists
	          for(int i = 0; i < firstArrayList.getLength(); i++) {
		             Node nNode = firstArrayList.item(i);
		             if(nNode.getNodeName().equals("dict")){
		            	 Element iElement = (Element) nNode;
		            	 NodeList secondArrayList = iElement.getChildNodes();
		            	 for (int j = 0; j < secondArrayList.getLength(); j++) {
		            		 Node nNode2 = secondArrayList.item(j);
		            		 // Second level in
		            		 if(nNode2.getTextContent().equals("Playlist Persistent ID")){
        						 Element kElement = (Element) nNode2;
        						 ppid = kElement.getNextSibling().getTextContent();
	            			 }
        					 if(nNode2.getTextContent().equals("Name")){
        						 Element kElement = (Element) nNode2;
        						 playListName = kElement.getNextSibling().getTextContent();
	            			 }
        					 if(nNode2.getTextContent().equals("Playlist ID")){
        						 Element kElement = (Element) nNode2;
        						 playListId = kElement.getNextSibling().getTextContent();
	            			 }
		            		 if(nNode2.getNodeName().equals("array")){
		            			 Element jElement = (Element) nNode2;
	            				 NodeList thirdArrayList = jElement.getChildNodes();
	            				 for(int k = 0; k < thirdArrayList.getLength(); k++) {
	            					 Node nNode3 = thirdArrayList.item(k);
	            					 // Third level in
	            					 if(nNode3.getNodeName().equals("dict")){
	            						 Element kElement = (Element) nNode3;
	            						 NodeList forthArrayList = kElement.getChildNodes();
	            						 for(int l = 0;l < forthArrayList.getLength(); l++){
	            							 Node nNode4 = forthArrayList.item(l);
	            							 if(nNode4.getTextContent().equals("Track ID")){
	            								 Element lElement = (Element) nNode4;
	            								 playListTrackId = lElement.getNextSibling().getTextContent();
	            							 }
	            						 }
	    	            			 }
	            				 }
		            		 }
		            	 }
		            	 PlayList p = new PlayList(playListName, ppid, playListId, playListTrackId,tracks);
    					 playLists.add(p);
    					 track.setPlaylisttrack(playLists);
		             }
		          }
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	      dao.parse(tracks);
	      dao.parsePlayList(playLists);
	}

}
