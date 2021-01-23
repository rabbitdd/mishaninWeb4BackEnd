package com.web4.mishanin.Web4.data;

import com.web4.mishanin.Web4.model.SecurityUser;
import com.web4.mishanin.Web4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOptional.get();
        System.out.println(user.toString());

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//        System.out.println("*****");
//        System.out.println(userOptional);
//        if (userOptional.isPresent()) {
//            return userOptional.map(SecurityUser::new).get();
//        } else {
//            return null;
//        }
        // userOptional.orElseThrow(()-> new UsernameNotFoundException("Not found: " + username));
        // System.out.println(userOptional.map(SecurityUser::new).get());




//        User user;
//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//            System.out.println("loadByUsername: " + user.toString());
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//        } else {
//            return null;
//        }
    }

    public String addUser(User user) {
        try {
            user.setRole("USER");
            userRepository.save(user);
            return "add";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "notAdd";
    }
}
