package com.dit.parser;

import java.io.File;
import java.util.Collection;
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

import com.dit.dao.DAO;
import com.dit.entities.PlayList;
import com.dit.entities.Track;
import com.dit.entities.User;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class MyParser implements Parser{
	
	@EJB
	private DAO dao;
	
	int count = 0;
	private String trackId = null, name = null, artist = null, album = null;
	private String ppid = null, playListName = null, playListId = null, playListTrackId = null, libraryPersistenceId = null;
	private Set<Track> tracks = new HashSet<Track>();
	private Set<PlayList> playLists = new HashSet<PlayList>();

	public void parse() {
	      try {	
	    	  // Specify file containing xml to be parsed, must be in wildfly bin folder
	          File inputFile = new File("iTunes Music Library3.xml");
	          
	          // Create Document Builder, Document is essentially the DOM tree
	          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	          dbFactory.setIgnoringElementContentWhitespace(true);//For number 2
	          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	          Document doc = dBuilder.parse(inputFile);
	          
	          // Get Root Element, i.e. plist
	          doc.getDocumentElement().normalize();
	          Element root = doc.getDocumentElement();
	          
	          // The first dictionary tags in the tree, FirstLayer
	          Node firstDictionary = root.getElementsByTagName("dict").item(0);
	          NodeList firstDictionaryList = firstDictionary.getChildNodes();
	          
	          // The first array tags in the tree, FirstLayer
	          Node firstArray = root.getElementsByTagName("array").item(0);
	          NodeList firstArrayList = firstArray.getChildNodes();
	          
	          // Return Library Persistent ID
	          for (int i = 0; i < firstDictionaryList.getLength(); i++) {
	             Node nNode = firstDictionaryList.item(i);
	             if(nNode.getTextContent().equals("Library Persistent ID")){
	            	 libraryPersistenceId=nNode.getNextSibling().getTextContent();
		         }
	             // Navigate to desired fields
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
            				 // Add Track to tracks
            				 Track t = new Track(name, artist, album, trackId);
            				 trackId = null;
            				 name = null;
            				 artist = null;
            				 album = null;
        					 tracks.add(t);
	            		 }
	            	 }
	             }
	          }
	          
	          // Playlists
	          for(int i = 0; i < firstArrayList.getLength(); i++) {
	        	  Set<Track> playlistTrack = new HashSet<Track>();
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
	            							 // Check for relationship between tracks and playlists
	            			            	 for(Track t: tracks){
	            			            		if(t.getTrackId().equals(playListTrackId)){
	            			            			playlistTrack.add(t);
	            			            		} 
	            			            	 }
	            						 }
	    	            			 }
	            				 }
		            		 }
		            	 }
		            	 // Playlist object will have collection of tracks
		            	 PlayList p = new PlayList(playListName, ppid, playListId, libraryPersistenceId, playlistTrack);
		            	 playListName = null;
		            	 ppid = null;
		            	 playListId = null;
        				 
		            	 // Parse Playlist
    					 playLists.add(p);
    					 dao.parsePlayList(p);
    					
		             }
		          }
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	      // Parse Tracks
	      dao.parse(tracks);
	      
	}
	
	// Methods explained in DAO implementation Class
	public void addUser(User user){
		dao.addUser(user);
	}

	public User findUser(String username, String password) {
		return dao.findUser(username,password);
	}
	public Collection<PlayList> getPlayList(String libraryPersistenceId){
		return dao.getPlayList(libraryPersistenceId);
	}

}
