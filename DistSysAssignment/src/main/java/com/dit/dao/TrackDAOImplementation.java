package com.dit.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

	public void parse(Set<Track> tracks) {
		for(Track t: tracks){
			em.persist(t);
		}
		
	}
	
	public void parsePlayList(Set<PlayList> playLists) {
		for(PlayList p: playLists){
			em.persist(p);
		}
		
	}

	public void parseJoin(Set<PlayList> playLists) {
		for(PlayList p: playLists){
			System.out.println("HEREWERTYUIOPOIUYTREWERTYUIOIUYTRERTYUIOIUYTREWERTYUIOIUYTREWERTYUIOIUYTREWRTYUIUYTREWERTYU");
			em.persist(p);
		}
	}

}
