package com.dit.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dit.entities.PlayList;
import com.dit.entities.Track;

@Stateless
@Local
public class TrackDAOImplementation implements TrackDAO{

    @PersistenceContext
    private EntityManager em;
    
	public Collection<Track> getAllTracks() {
		Query query = em.createQuery("from Track");
        return (List<Track>)query.getResultList();
	}

	public void parse(Collection<Track> tracks) {
		System.out.println("DAO");
		for(Track t: tracks){
			System.out.println("DAO Loop");
			em.persist(t);
		}
		
	}
	
	public void parsePlayList(Collection<PlayList> playLists) {
		System.out.println("DAO");
		for(PlayList p: playLists){
			System.out.println("DAO Loop");
			em.persist(p);
		}
		
	}

}
