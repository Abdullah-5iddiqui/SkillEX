package com.SkillExchanger.SkillExchanger.Repo;

import com.SkillExchanger.SkillExchanger.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer> {
    @Query("SELECT avg(r.rating) FROM Rating r WHERE r.toUser.id = :ratingId")
    Double findAvgRating(int ratingId);
}
