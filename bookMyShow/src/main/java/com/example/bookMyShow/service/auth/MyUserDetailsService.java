package com.example.bookMyShow.service.auth;

import com.example.bookMyShow.entity.User;
import com.example.bookMyShow.model.auth.UserPrinciple;
import com.example.bookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //logic to check if user exists with username
        User user = userRepository.findByUserName(username);
        if(null == user){
            throw new UsernameNotFoundException("User not found");
        }

        //return back the details of user but in spring required format i.e., UserDetails
        return new UserPrinciple(user);
    }
}
