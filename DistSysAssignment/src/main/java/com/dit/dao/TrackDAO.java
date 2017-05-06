package com.dit.dao;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Local;

import com.dit.entities.PlayList;
import com.dit.entities.Track;

@Local
public interface TrackDAO {
	public Collection<Track> getAllTracks();
	public void parse(Set<Track> tracks);
	public void parsePlayList(Set<PlayList> playLists);
	public void parseJoin(Set<PlayList> playLists);
}
