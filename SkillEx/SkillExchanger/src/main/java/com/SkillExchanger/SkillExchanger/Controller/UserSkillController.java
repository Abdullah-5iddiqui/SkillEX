package com.SkillExchanger.SkillExchanger.Controller;

import com.SkillExchanger.SkillExchanger.Entity.Skill;
import com.SkillExchanger.SkillExchanger.Entity.User;
import com.SkillExchanger.SkillExchanger.Entity.UserSkill;
import com.SkillExchanger.SkillExchanger.Service.SkillService;
import com.SkillExchanger.SkillExchanger.Service.UserService;
import com.SkillExchanger.SkillExchanger.Service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/UserSkills")
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private UserService userService;
//    @PostMapping
//    public ResponseEntity<?> addUserSkill(@RequestBody UserSkill userSkill) {
//        return userSkillService.addUserSkill(userSkill);
//    }
    @PostMapping("/{type}")
    public ResponseEntity<?> addSkill(Principal principal, @RequestBody Skill skill, @PathVariable UserSkill.SkillType type) {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        System.out.println("\n"+principal.getName());
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        Skill savedSkill = skillService.addSkill(skill);
        System.out.println("User: " + user.getId()); // should be a non-null DB ID
        System.out.println("Skill: " + skill.getId()); // should also be non-null


        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(savedSkill);
        userSkill.setType(type);
        UserSkill savedUserSkill = userSkillService.addUserSkill(userSkill);

        return ResponseEntity.ok(savedUserSkill);
    }

    @GetMapping("/{skill}/{type}")
    public ResponseEntity<?> getUsersOnSkill(@PathVariable int skill, @PathVariable String type) {
        return userSkillService.findUsersBySkillAndType(skill, type);
    }


}
