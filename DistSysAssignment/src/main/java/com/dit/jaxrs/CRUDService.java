package com.dit.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dit.entities.TrackList;
import com.dit.services.TrackService;

@Path("/tracks")
public class CRUDService {
	@Inject
	TrackService service;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public TrackList getBaseData(){
		TrackList list = new TrackList();
		list.setTrackList(service.getAllTracks());
		return list;
	}
}