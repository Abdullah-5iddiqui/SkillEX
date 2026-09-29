package com.SkillExchanger.SkillExchanger.Service;

import com.SkillExchanger.SkillExchanger.Entity.*;
import com.SkillExchanger.SkillExchanger.Repo.RatingRepo;
import com.SkillExchanger.SkillExchanger.Repo.SkillRepo;
import com.SkillExchanger.SkillExchanger.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    private RatingRepo ratingRepo;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<?> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return ResponseEntity.ok().build();
    }
    public User findUserById(int id){
        return repo.findById(id).orElse(null);
    }
    public ResponseEntity<?> deleteUser(int userId){
        repo.deleteById(userId);
        return ResponseEntity.ok().build();
    }
    public User findByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }

    public ResponseEntity<?> getMySkills(Principal principal){

        String username = principal.getName();
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Skill> skills = user.getUserSkills().stream()
                .map(UserSkill::getSkill)
                .distinct()
                .toList();

        return ResponseEntity.ok(skills);

    }

    public ResponseEntity<?> search(String keyword, User loggedInUser) {
        List<User> users = repo.search(keyword);
        List<User> filtered = users.stream()
                .filter(user -> user.getId() != loggedInUser.getId())
                .collect(Collectors.toList());

        List<UserCard> userCards = new ArrayList<>();

        for (User i : filtered) {
            double rating = getAvgRating(i.getId());
            for (UserSkill userSkill : i.getUserSkills()) {
                UserCard userCard = new UserCard();
                userCard.setUsername(i.getUsername());
                userCard.setSkillName(userSkill.getSkill().getName());
                userCard.setCategory(userSkill.getSkill().getCategory());
                userCard.setRating(rating);
                userCards.add(userCard);
            }
        }

        return ResponseEntity.ok(userCards);
    }

    private double getAvgRating(int userId){
        Double avg =  ratingRepo.findAvgRating(userId);
        return avg!=null ? avg : 0.0;
    }



}
