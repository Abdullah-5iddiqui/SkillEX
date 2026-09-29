package com.SkillExchanger.SkillExchanger.Repo;

import com.SkillExchanger.SkillExchanger.Entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepo extends JpaRepository<UserSkill, Integer> {
    List<UserSkill> findBySkillIdAndType (int id, UserSkill.SkillType type);
}
