package com.dit.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PlayList")
public class PlayList implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)// Used for Auto-Increment
	@Column(name="id")private int id;
	
	@Column(name="playListName")private String playListName;
	@Column(name="ppid")private String ppid;
	@Column(name="playListId")private String playListId;
	@Column(name="playListTrackId")private String playListTrackId;
	
	public PlayList(){}

	public PlayList(String playListName, String ppid, String playListId, String playListTrackId) {
		super();
		this.playListName = playListName;
		this.ppid = ppid;
		this.playListId = playListId;
		this.playListTrackId = playListTrackId;
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

	public String getPlayListTrackId() {
		return playListTrackId;
	}

	public void setPlayListTrackId(String playListTrackId) {
		this.playListTrackId = playListTrackId;
	}
	
	

}
