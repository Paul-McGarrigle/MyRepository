package com.dit.services;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.dit.dao.TrackDAO;
import com.dit.entities.Track;

@Stateless
@Local
@TransactionAttribute (TransactionAttributeType.REQUIRED)// Ensures Transactions are used for ACID purposes
public class TrackServiceImplementation implements TrackService{
	
	@EJB
	TrackDAO dao;
	
	public Collection<Track> getAllTracks() {
		return dao.getAllTracks();
	}

}
