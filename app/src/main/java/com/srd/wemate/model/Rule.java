package com.srd.wemate.model;


public class Rule {

	private int rid;

	public void setRid(int rid) {
		this.rid = rid;
	}

	public void setMateGroup(MateGroup mateGroup) {
		this.mateGroup = mateGroup;
	}

	private String content;
	private MateGroup mateGroup;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public int getRid() {
		return rid;
	}

	public MateGroup getMateGroup() {
		return mateGroup;
	}


	
	@Override
	public String toString() {
		return "Rule{" +
				"rid=" + rid +
				", content=" + content +
				//", mategroup=" + mateGroup.getGid() +
				'}';
	}

	public Rule(String content, MateGroup mateGroup) {
		this.content = content;
		this.mateGroup = mateGroup;
		//mateGroup.setRule(this);
	}
	
	public void update(String content) {
        this.content = content;
    }
}
