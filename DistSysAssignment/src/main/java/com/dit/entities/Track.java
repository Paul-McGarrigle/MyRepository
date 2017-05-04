package com.dit.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Track")
public class Track implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)// Used for Auto-Increment
	@Column(name="id")private int id;
	
	@Column(name="name")private String name;
	@Column(name="artist")private String artist;
	@Column(name="album")private String album;
	@Column(name="trackId")private String trackId;
	
	// No argument constructor
	public Track(){}

	public Track(String name, String artist, String album, String trackId) {
		super();
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.trackId = trackId;
	}
	public Track(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	
	
	
	

}
