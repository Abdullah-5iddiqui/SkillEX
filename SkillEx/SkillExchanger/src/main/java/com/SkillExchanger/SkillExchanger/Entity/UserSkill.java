    package com.SkillExchanger.SkillExchanger.Entity;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;
    import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "User_Skills")
    @NoArgsConstructor
    public class UserSkill {

        public enum SkillType{
            Offer,Learn
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private int id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        @JsonBackReference("userRef")
        private User user;

        @ManyToOne()
        @JoinColumn(name = "skill_id", nullable = false)
        @JsonBackReference("skillRef")
        private Skill skill;

        public SkillType getType() {
            return type;
        }

        public void setType(SkillType type) {
            this.type = type;
        }

        @Enumerated(EnumType.STRING)
        @Column(name = "type",nullable = false)
        private SkillType type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Skill getSkill() {
            return skill;
        }

        public void setSkill(Skill skill) {
            this.skill = skill;
        }

    }

