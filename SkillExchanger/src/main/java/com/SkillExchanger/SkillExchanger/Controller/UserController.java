package com.SkillExchanger.SkillExchanger.Controller;

import com.SkillExchanger.SkillExchanger.Entity.User;
import com.SkillExchanger.SkillExchanger.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "HELLO USER";
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

    @GetMapping("/mySkills")
    public ResponseEntity<?> getMySkills(Principal principal){
        return userService.getMySkills(principal);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> search(@PathVariable String keyword, Principal principal){
        String name = principal.getName();
        User loggedInUser = userService.findByUsername(name);
        return userService.search(keyword,loggedInUser);
    }

}
