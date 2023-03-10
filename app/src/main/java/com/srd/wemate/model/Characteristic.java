package com.srd.wemate.model;

public class Characteristic {
    private String id;
    private String relation;
    private String rest_style;
    private String life_style;
    private String share;
    private String shower;
    private String clean;
    private String guest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRest_style() {
        return rest_style;
    }

    public void setRest_style(String rest_style) {
        this.rest_style = rest_style;
    }

    public String getLife_style() {
        return life_style;
    }

    public void setLife_style(String life_style) {
        this.life_style = life_style;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getShower() {
        return shower;
    }

    public void setShower(String shower) {
        this.shower = shower;
    }

    public String getClean() {
        return clean;
    }

    public void setClean(String clean) {
        this.clean = clean;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "id='" + id + '\'' +
                ", relation='" + relation + '\'' +
                ", rest_style='" + rest_style + '\'' +
                ", life_style='" + life_style + '\'' +
                ", share='" + share + '\'' +
                ", shower='" + shower + '\'' +
                ", clean='" + clean + '\'' +
                ", guest='" + guest + '\'' +
                '}';
    }


}
