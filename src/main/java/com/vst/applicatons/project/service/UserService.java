package com.vst.applicatons.project.service;

import com.vst.applicatons.project.entity.User;
import com.vst.applicatons.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
//        implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
//    {
//        User user = userRepository.findByEmail(email);
//
//        if (user == null) throw new UsernameNotFoundException("User no found!");
//
//        return user;
//    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }
}
