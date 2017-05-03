package com.dit.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrackList implements Serializable{
	private Collection<Track> trackList;

	public Collection<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(Collection<Track> trackList) {
		this.trackList = trackList;
	}

}
