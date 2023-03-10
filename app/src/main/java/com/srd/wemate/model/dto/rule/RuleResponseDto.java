package com.srd.wemate.model.dto.rule;

import com.srd.wemate.model.MateGroup;
import com.srd.wemate.model.Rule;

public class RuleResponseDto {

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MateGroup getMateGroup() {
        return mateGroup;
    }

    public void setMateGroup(MateGroup mateGroup) {
        this.mateGroup = mateGroup;
    }

    private int rid;
	private String content;
	private MateGroup mateGroup;
	
	public RuleResponseDto(Rule entity) {
        this.rid = entity.getRid();
        this.content = entity.getContent();
        this.mateGroup = entity.getMateGroup();
    }
}
