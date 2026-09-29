// I PROLLY DONT NEED IT
//
//package com.SkillExchanger.SkillExchanger.Entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//    @Entity
//    @Table(name = "matches")
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    public class Match {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        // User who teaches the skill
//        @ManyToOne
//        @JoinColumn(name = "teacher_id", nullable = false)
//        private User teacher;
//
//        // User who learns the skill
//        @ManyToOne
//        @JoinColumn(name = "learner_id", nullable = false)
//        private User learner;
//
//        @ManyToOne
//        @JoinColumn(name = "skill_id", nullable = false)
//        private Skill skill;
//
//        @Column(name = "status", nullable = false)
//        private String status;
//
//    }
