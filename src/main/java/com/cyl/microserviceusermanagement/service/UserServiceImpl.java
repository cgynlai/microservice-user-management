package com.cyl.microserviceusermanagement.service;

import com.cyl.microserviceusermanagement.model.User;
import com.cyl.microserviceusermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

@Autowired
    public UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

@Override
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<String> findUsers(List<Long> idList) {
        return userRepository.findByIdList(idList);
    }
}
