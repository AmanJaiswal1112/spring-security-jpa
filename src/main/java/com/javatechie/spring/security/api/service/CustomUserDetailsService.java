package com.javatechie.spring.security.api.service;

import com.javatechie.spring.security.api.model.User;
import com.javatechie.spring.security.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
       User user =  userRepository.findByName(username);
        CustomeUserDetails userDetails = null;
       if(user != null )
       {
            userDetails = new CustomeUserDetails();
            userDetails.setUser(user);
       }
       else
       {
           throw new UsernameNotFoundException("User does not exist with username");
       }
       return  userDetails;
    }
}
