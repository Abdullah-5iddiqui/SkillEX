package com.SkillExchanger.SkillExchanger.Config;

import com.SkillExchanger.SkillExchanger.Entity.User;
import com.SkillExchanger.SkillExchanger.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

        @Autowired
        private UserRepo repo;

        public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = repo.findByUsername(username).orElse(null);
            if (user != null) {
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build();
            }
            throw new UsernameNotFoundException("NO CUSTOMER FOUND WITH USERNAME: " + username);
        }
}
