package com.srd.wemate.model.dto.profile;

import com.srd.wemate.model.Profile;

public class ProfileListResponseDto {

    public String getId() {
        return id;
    }

    private String id;

    public String getName() {
        return name;
    }

    private String name;

    private String title;
    private String gender;   // female or male
    private boolean smoke;   // 1 or 0
    private String age;
    private String pet;     // pet-type or "null"
    private String addr1;       // 시
    private String addr2;       // 도
    private String period;      // 6 or 1 or 2
    private String room_type;    // share or solo
    private String rent_fee; // monthly or lease

    public int getGid() {
        return gid;
    }

    private int gid;

    public ProfileListResponseDto(Profile entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.title = entity.getTitle();
        this.gender = entity.getGender();
        this.smoke = entity.isSmoke();
        this.age = entity.getAge();
        this.pet = entity.getPet();
        this.addr1 = entity.getAddr1();
        this.addr2 = entity.getAddr2();
        this.period = entity.getPeriod();
        this.room_type = entity.getRoom_type();
        this.rent_fee = entity.getRent_fee();
        this.gid = entity.getGroupid().getGid();
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id + "}";
    }
}
