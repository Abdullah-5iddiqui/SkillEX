package com.SkillExchanger.SkillExchanger.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


    @Entity
    @Table(name = "ratings")
    @NoArgsConstructor
    public class Rating {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public User getFromUser() {
            return fromUser;
        }

        public void setFromUser(User fromUser) {
            this.fromUser = fromUser;
        }

        public User getToUser() {
            return toUser;
        }

        public void setToUser(User toUser) {
            this.toUser = toUser;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        @ManyToOne
        @JoinColumn(name = "from_user_id", nullable = false)
        private User fromUser;

        @ManyToOne
        @JoinColumn(name = "to_user_id", nullable = false)
        private User toUser;

        @Column(nullable = false)
        private double rating;

        private String comment;

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }


    }
