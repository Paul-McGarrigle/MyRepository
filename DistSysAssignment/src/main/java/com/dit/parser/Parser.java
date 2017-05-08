package com.dit.parser;

import java.util.Collection;

import com.dit.entities.PlayList;
import com.dit.entities.User;

public interface Parser {
	public void parse();
	public void addUser(User user);
	public User findUser(String username, String password);
	public Collection<PlayList> getPlayList(String libraryPersistenceId);
}
