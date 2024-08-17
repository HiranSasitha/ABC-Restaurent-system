package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.LoginDto;
import com.example.abc_restaurant_system.dto.UserResponseDto;
import com.example.abc_restaurant_system.entity.Role;
import com.example.abc_restaurant_system.entity.User;
import com.example.abc_restaurant_system.repository.UserRepository;
import com.example.abc_restaurant_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements  UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public UserResponseDto createJwtToken(LoginDto dto) throws Exception{
        String userName = dto.getUserName();
        String password = dto.getPassword();
        System.out.println(userName);
        System.out.println(password);

        authenticate(userName,password);

        UserDetails userDetails = loadUserByUsername(userName);
        String generateToken = jwtUtil.generateJwtToken(userDetails);
        User user = userRepo.findByUserName(userName);

        return new UserResponseDto(generateToken,user);

    }

    private void authenticate(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

        }catch (BadCredentialsException e){
            throw new Exception("invalid User",e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(name);

        if(user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthority(user)

            );


        }else {
            throw new UsernameNotFoundException("User Not Found"+name);
        }


    }

    public Set getAuthority(User user) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for(Role role:user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }
}

