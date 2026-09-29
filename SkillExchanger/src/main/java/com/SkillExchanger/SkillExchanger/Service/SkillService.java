package com.SkillExchanger.SkillExchanger.Service;

import com.SkillExchanger.SkillExchanger.Entity.Skill;
import com.SkillExchanger.SkillExchanger.Repo.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    private SkillRepo repo;

    public Skill addSkill(Skill skill) {
//        return ResponseEntity.ok(repo.save(skill));
        return repo.save(skill);
    }

    public ResponseEntity<?> getAllSkills() {
        return ResponseEntity.ok(repo.findAll());
    }
    public ResponseEntity<?> getSkillByName(String name) {
        return ResponseEntity.ok(repo.findByName(name));
    }
}
