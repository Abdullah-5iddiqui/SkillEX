package com.SkillExchanger.SkillExchanger.Controller;

import com.SkillExchanger.SkillExchanger.Entity.LoginRequest;
import com.SkillExchanger.SkillExchanger.Entity.User;
import com.SkillExchanger.SkillExchanger.Repo.SkillRepo;
import com.SkillExchanger.SkillExchanger.Service.SkillService;
import com.SkillExchanger.SkillExchanger.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/skills")
    public ResponseEntity<?> getSkills() {
        return skillService.getAllSkills();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user){
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            return ResponseEntity.ok("Login successful");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.register(user);
    }

}
