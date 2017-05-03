package com.dit.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.dit.entities.Track;

@Local
public interface TrackDAO {
	public Collection<Track> getAllTracks();
}
