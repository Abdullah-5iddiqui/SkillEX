package com.SkillExchanger.SkillExchanger.Service;

import com.SkillExchanger.SkillExchanger.Entity.User;
//import com.SkillExchanger.SkillExchanger.Entity.UserSkill;
//import com.SkillExchanger.SkillExchanger.Repo.UserSkillRepo;
import com.SkillExchanger.SkillExchanger.Entity.UserSkill;
import com.SkillExchanger.SkillExchanger.Repo.UserSkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSkillService {

    @Autowired
    private UserSkillRepo repo;

    public UserSkill addUserSkill(UserSkill userSkill) {
        return repo.save(userSkill);
//        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> findUsersBySkillAndType(int skill, String type) {
        UserSkill.SkillType skillType = UserSkill.SkillType.valueOf(type);

        List<UserSkill> userSkills = repo.findBySkillIdAndType(skill, skillType);

        if (userSkills.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No users found for the given skill and type.");
        }

        List<User> users = userSkills.stream()
                .map(userSkill -> userSkill.getUser())
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }
}
