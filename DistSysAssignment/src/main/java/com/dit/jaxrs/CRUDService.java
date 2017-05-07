package com.dit.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dit.entities.TrackList;
import com.dit.entities.User;
import com.dit.parser.Parser;
import com.dit.services.TrackService;

@Path("/tracks")
public class CRUDService {
	@Inject
	TrackService service;
	
	@Inject
	Parser parser;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public TrackList getBaseData(){
		TrackList list = new TrackList();
		list.setTrackList(service.getAllTracks());
		return list;
	}
	
	@POST
	@Path("/parse")
    @Consumes(MediaType.APPLICATION_JSON)
	public void parse(){
		System.out.println("CRUD");
		parser.parse();
	}
	
	@POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user){
        System.out.println(user.getUsername());
        parser.addUser(user);
    }
	
	 @GET
	    @Path("/{username}/{password}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public User findUser(@PathParam("username") String username, @PathParam("password") String password){
	        return parser.findUser(username, password);
	    }
}
