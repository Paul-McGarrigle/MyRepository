package com.dit.dao;

import java.util.Collection;
import java.util.Set;

import javax.ejb.Local;

import com.dit.entities.PlayList;
import com.dit.entities.Track;
import com.dit.entities.User;

@Local
public interface DAO {
	public Collection<Track> getAllTracks();
	public void parse(Set<Track> tracks);
	public void parsePlayList(PlayList playList);
	public void addUser(User user);
	public User findUser(String username, String password);
	public Collection<PlayList> getPlayList(String libraryPersistenceId);
}
