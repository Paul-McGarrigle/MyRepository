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
import com.dit.entities.User;

@Stateless
@Local
public class DAOImplementation implements DAO{

    @PersistenceContext// Persist to DB
    private EntityManager em;
    
    // Return all tracks, tester method
	public Collection<Track> getAllTracks() {
		Query query = em.createQuery("from Track");
        return (List<Track>)query.getResultList();
	}

	// Persist Tracks
	public void parse(Set<Track> tracks) {
		for(Track t: tracks){
			em.persist(t);
		}
	}
	
	// Persist PlayList
	public void parsePlayList(PlayList playList) {
		em.persist(playList);
	}
	
	// Persist User
	public void addUser(User user) {
		em.persist(user);
	}
	
	// Check User exists
	public User findUser(String username, String password) {
        Query query = em.createQuery("select distinct c from User c where c.password = :password AND  c.username = :username ");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User)query.getSingleResult();
    }
	
	// Return User's PlayLists
	public Collection<PlayList> getPlayList(String libraryPersistenceId) {
		Query query = em.createQuery("from PlayList c where c.libraryPersistenceId = :libraryPersistenceId");
        query.setParameter("libraryPersistenceId", libraryPersistenceId);
        return (List<PlayList>)query.getResultList();
	}

}
