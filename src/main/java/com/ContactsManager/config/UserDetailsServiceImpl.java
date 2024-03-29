package com.ContactsManager.config;

import com.ContactsManager.dao.*;
import com.ContactsManager.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; //this will inject object of UserRepository for method getUserByUserName

    //adding unimplemented method of interface UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
        //here we have bring user from db and return user details, so require dao of UserRepository.java
        User user = userRepository.getUserByUserName(username); //this will give us username

        if(user == null){
            //if now user is found.
            throw new UsernameNotFoundException("Could not found user!");
        }

        //if user is found
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

		return customUserDetails;
    }
}
