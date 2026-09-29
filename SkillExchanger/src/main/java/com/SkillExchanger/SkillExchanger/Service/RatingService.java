package com.SkillExchanger.SkillExchanger.Service;

import com.SkillExchanger.SkillExchanger.Entity.Rating;
import com.SkillExchanger.SkillExchanger.Entity.RatingDTO;
import com.SkillExchanger.SkillExchanger.Entity.User;
import com.SkillExchanger.SkillExchanger.Repo.RatingRepo;
import com.SkillExchanger.SkillExchanger.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class RatingService {
    @Autowired
    private RatingRepo repo;
    @Autowired
    private UserRepo userRepo;
    public ResponseEntity<Rating> rate(RatingDTO ratingReq, String name) {

            User fromUser = userRepo.findByUsername(name)
                    .orElseThrow(() -> new IllegalArgumentException("Logged in user not found"));

            User toUser = userRepo.findByUsername(ratingReq.getUsername()).orElseThrow(() -> new IllegalArgumentException("Rated user not found"));
            Rating rating = new Rating();
            rating.setRating(ratingReq.getRating());
            rating.setComment(ratingReq.getComment());
            rating.setFromUser(fromUser);
            rating.setToUser(toUser);
        return ResponseEntity.ok(repo.save(rating));
    }

    public ResponseEntity<?> getAvgRating(int userId) {
        return ResponseEntity.ok(repo.findAvgRating(userId));
    }
}
