package com.SkillExchanger.SkillExchanger.Entity;

public class UserCard {

    private String username;
    private String skillName;
    private String category;
    private String description;
    private double rating;
    private UserSkill.SkillType type;

    public UserSkill.SkillType getType() {
        return type;
    }
    public void setType(UserSkill.SkillType type) {
        this.type = type;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


}
