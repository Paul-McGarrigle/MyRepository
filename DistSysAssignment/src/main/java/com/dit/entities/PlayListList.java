package com.dit.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayListList implements Serializable{
	private Collection<PlayList> playListList;
	
	public PlayListList(){}
	
	public Collection<PlayList> getPlayListList() {
		return playListList;
	}

	public void setPlayListList(Collection<PlayList> playListList) {
		this.playListList = playListList;
	}
	
}
