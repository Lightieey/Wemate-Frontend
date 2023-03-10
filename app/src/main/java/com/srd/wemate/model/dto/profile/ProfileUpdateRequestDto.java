package com.srd.wemate.model.dto.profile;

public class ProfileUpdateRequestDto {

	public String getPet() {
		return pet;
	}

	public String getAddr1() {
		return addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public int getPeriod() {
		return period;
	}

	public String getRoom_type() {
		return room_type;
	}

	public String getRent_fee() {
		return rent_fee;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getGender() {
		return gender;
	}

	public boolean isSmoke() {
		return smoke;
	}

	public int getAge() {
		return age;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public void setRent_fee(String rent_fee) {
		this.rent_fee = rent_fee;
	}

	private String name;
	private String title;
	private String gender;
	private boolean smoke;

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setSmoke(boolean smoke) {
		this.smoke = smoke;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	private int age;
	private String pet;
	private String addr1;
	private String addr2;
	private int period;
	private String room_type;
	private String rent_fee;

    public ProfileUpdateRequestDto(String name, String title, String gender, boolean smoke, int age, String pet, String addr1, String addr2,
  		  int period, String room_type, String rent_fee) {
    	this.name = name;
  	  	this.title = title;
  	  	this.gender = gender;
  	  	this.smoke = smoke;
  	  	this.age = age;
  	  	this.pet = pet;
  	  	this.addr1 = addr1;
  	  	this.addr2 = addr2;
  	  	this.period = period;
  	  	this.room_type = room_type;
  	  	this.rent_fee = rent_fee;
    }

    public ProfileUpdateRequestDto() {}
}
