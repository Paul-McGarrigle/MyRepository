package com.dit.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="playlist")
public class PlayList implements Serializable {
	
	@Id// Primary Key
	@GeneratedValue(strategy=GenerationType.IDENTITY)// Used for Auto-Increment
	@Column(name="p_id")private int id;
	
	// Columns variables will be mapped to
	@Column(name="playListName")private String playListName;
	@Column(name="ppid")private String ppid;
	@Column(name="playListId")private String playListId;
	@Column(name="libraryPersistenceId")private String libraryPersistenceId;
	
	// Relationship with Join Table
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER )  
    @JoinTable(name="playlist_tracks", joinColumns=@JoinColumn(name="playListId", referencedColumnName="p_id"), inverseJoinColumns=@JoinColumn(name="trackId", referencedColumnName="t_id"))
	private Set<Track> tracksplaylists;
	
	// No argument constructor
	public PlayList(){}

	public PlayList(String playListName, String ppid, String playListId, String libraryPersistenceId, Set<Track> tracksplaylists) {
		super();
		this.playListName = playListName;
		this.ppid = ppid;
		this.playListId = playListId;
		this.libraryPersistenceId = libraryPersistenceId;
		this.tracksplaylists = tracksplaylists;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlayListName() {
		return playListName;
	}

	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}

	public String getPpid() {
		return ppid;
	}

	public void setPpid(String ppid) {
		this.ppid = ppid;
	}

	public String getPlayListId() {
		return playListId;
	}

	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}

	public Set<Track> getTracksplaylists() {
		return tracksplaylists;
	}

	public void setTracksplaylists(Set<Track> tracks) {
		this.tracksplaylists = tracks;
	}
	
	

}
