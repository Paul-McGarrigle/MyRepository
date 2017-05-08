package com.dit.services;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.dit.dao.DAO;
import com.dit.entities.Track;

// Used for testing all tracks can be returned
@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)
public class TrackServiceImplementation implements TrackService{
	
	@EJB
	private DAO dao;
	
	public Collection<Track> getAllTracks() {
		return dao.getAllTracks();
	}

}
