package com.SkillExchanger.SkillExchanger.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SkillExchanger.SkillExchanger.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u " +
            "LEFT JOIN u.userSkills us " +
            "LEFT JOIN us.skill s " +
            "WHERE LOWER(u.address) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(s.category) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> search(String keyword);


}
