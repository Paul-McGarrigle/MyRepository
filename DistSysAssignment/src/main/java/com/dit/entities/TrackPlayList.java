/*package com.dit.entities;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="playlist_tracks")
public class TrackPlayList {
	
    @ManyToOne
    @JoinColumn(name="trackId")
    private String trackId;
    
    @ManyToOne
    @JoinColumn(name="playListId")
	private String playListId;
	
	public TrackPlayList(){}

	public TrackPlayList(String trackId, String playListId) {
		super();
		this.trackId = trackId;
		this.playListId = playListId;
	}


	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getPlayListId() {
		return playListId;
	}

	public void setPlayListId(String playListId) {
		this.playListId = playListId;
	}
	
	

}
*/