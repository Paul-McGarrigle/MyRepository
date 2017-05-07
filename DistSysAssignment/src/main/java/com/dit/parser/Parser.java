package com.dit.parser;

import com.dit.entities.User;

public interface Parser {
	public void parse();
	public void addUser(User user);
	public User findUser(String username, String password);
}
