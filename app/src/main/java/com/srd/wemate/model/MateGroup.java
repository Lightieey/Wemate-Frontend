package com.srd.wemate.model;

import java.util.HashSet;
import java.util.Set;


public class MateGroup {

	public int getGid() {
		return gid;
	}

	public Set<Profile> getUsers() {
		return users;
	}

	private int gid;
	private Set<Profile> users;
	
	@Override
    public String toString() {
        return "MateGroup{" +
                "gid=" + gid +
                ", users=" + users +
                '}';
    }

    public MateGroup(int gid, Set<Profile> users) {
        this.gid = gid;
        this.users = users;
    }
	
	public void addMate(Profile user) {
		if (users == null) users = new HashSet<Profile>();
		users.add(user);
	}
	
	public void deleteMate(Profile user) {
		if (users == null) return;
		users.remove(user);
	}
}
