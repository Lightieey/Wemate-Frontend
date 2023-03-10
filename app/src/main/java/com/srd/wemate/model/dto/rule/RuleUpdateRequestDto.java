package com.srd.wemate.model.dto.rule;

public class RuleUpdateRequestDto {


	private String content;

	public RuleUpdateRequestDto(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
}
