package com.dit.dao;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Local;

import com.dit.entities.PlayList;
import com.dit.entities.Track;
import com.dit.entities.User;

@Local
public interface TrackDAO {
	public Collection<Track> getAllTracks();
	public void parse(Set<Track> tracks);
	public void parsePlayList(PlayList playList);
	public void parseJoin(Set<PlayList> playLists);
	public void addUser(User user);
	public void parse1(Set<PlayList> playlist);
}
