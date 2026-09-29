package com.SkillExchanger.SkillExchanger.Controller;

import com.SkillExchanger.SkillExchanger.Entity.Rating;
import com.SkillExchanger.SkillExchanger.Entity.RatingDTO;
import com.SkillExchanger.SkillExchanger.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/rate")
    public ResponseEntity<?> rate(@RequestBody RatingDTO rating,Principal principal) {
        return ratingService.rate(rating, principal.getName());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getAvgRating(@PathVariable int userId) {
        return ratingService.getAvgRating(userId);
    }
}
