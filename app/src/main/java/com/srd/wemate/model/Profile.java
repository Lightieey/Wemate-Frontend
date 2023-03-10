package com.srd.wemate.model;

public class Profile {

  private String name;
  private String title; //한줄 제목
  private String content; // 소개
  private String gender;    // female or male
  private boolean smoke;    // 1 or 0
  private String age;
  private String pet;        // pet-type or "null"
  private String addr1;        // 구
  private String addr2;        // 동
  private String period;        // 6 or 1 or 2
  private String room_type;    // share or solo
  private String rent_fee;    // monthly or lease
  private String img;
  private MateGroup groupid;

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public boolean isSmoke() {
    return smoke;
  }

  public void setSmoke(boolean smoke) {
    this.smoke = smoke;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getPet() {
    return pet;
  }

  public void setPet(String pet) {
    this.pet = pet;
  }

  public String getAddr1() {
    return addr1;
  }

  public void setAddr1(String addr1) {
    this.addr1 = addr1;
  }

  public String getAddr2() {
    return addr2;
  }

  public void setAddr2(String addr2) {
    this.addr2 = addr2;
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }

  public String getRoom_type() {
    return room_type;
  }

  public void setRoom_type(String room_type) {
    this.room_type = room_type;
  }

  public String getRent_fee() {
    return rent_fee;
  }

  public void setRent_fee(String rent_fee) {
    this.rent_fee = rent_fee;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public MateGroup getGroupid() {
    return groupid;
  }

  public void setGroupid(MateGroup groupid) {
    this.groupid = groupid;
  }




  @Override
  public String toString() {
    return "Profile{" +
            "id=" + id +
            ", name=" + name +
            ", title=" + title +
            ", content=" + content +
            ", gender=" + gender +
            ", smoke=" + smoke +
            ", age=" + age +
            ", pet=" + pet +
            ", addr1=" + addr1 +
            ", addr2=" + addr2 +
            ", period=" + period +
            ", room_type=" + room_type +
            ", rent_fee=" + rent_fee +
            ", img=" + img +
            ", roommate=" + groupid +
            '}';
  }
}

