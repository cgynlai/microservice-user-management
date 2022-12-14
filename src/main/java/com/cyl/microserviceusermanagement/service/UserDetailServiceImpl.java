package com.cyl.microserviceusermanagement.service;

import com.cyl.microserviceusermanagement.model.User;
import com.cyl.microserviceusermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserDetailServiceImpl  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElse(null);
        if ( user == null ) {
            throw  new UsernameNotFoundException(username);
        }

         // authorize user with Role
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add( new SimpleGrantedAuthority( user.getRole().name() ));


        return  new org.springframework.security.core.userdetails.User (username, user.getPassword(), authorities);
    }
}
