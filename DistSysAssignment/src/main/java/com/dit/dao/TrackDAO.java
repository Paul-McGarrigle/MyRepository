package com.dit.dao;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Local;

import com.dit.entities.PlayList;
import com.dit.entities.Track;

@Local
public interface TrackDAO {
	public Collection<Track> getAllTracks();
	public void parse(Collection<Track> tracks);
	public void parsePlayList(Collection<PlayList> playLists);
}
