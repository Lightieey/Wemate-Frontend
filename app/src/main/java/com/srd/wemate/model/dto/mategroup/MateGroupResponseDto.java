package com.srd.wemate.model.dto.mategroup;

import com.srd.wemate.model.MateGroup;
import com.srd.wemate.model.Profile;

import java.util.Set;

public class MateGroupResponseDto {

	private int gid;
	private Set<Profile> users;
	
	public MateGroupResponseDto(MateGroup entity) {
		this.gid = entity.getGid();
		this.users = entity.getUsers();
	}

	public MateGroupResponseDto() {}
}
