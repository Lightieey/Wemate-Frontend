package com.srd.wemate.model.dto.rule;


public class RuleSaveRequestDto {

	private String content;
	private int groupID;

	public RuleSaveRequestDto(String content, int groupID) {
		this.content = content;
		this.groupID = groupID;
	}
	
}
