package com.befree.controllers;

import com.befree.adapter.DozerConverter;
import com.befree.adapter.custom.UserConverter;
import com.befree.data.model.Permission;
import com.befree.data.model.Usertype;
import com.befree.data.model.vo.GraduationVO;
import com.befree.data.model.vo.UserVO;
import com.befree.security.AuthenticationCredentialsVO;
import com.befree.security.jwt.JwtTokenProvider;
import com.befree.services.GraduationServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserServices userServices;
//    @Autowired
//    private UserConverter userConverter;
//    @Autowired
    private GraduationServices graduationServices;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @PostMapping(value = "/login",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<UserVO> login(@RequestBody AuthenticationCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userServices.getUserByUserName(username);
            var token = "";
            if (user != null) {
                user.setToken(tokenProvider.createToken(username, user.getRoles()));
            } else {
                throw new UsernameNotFoundException("Username: " + username + "not found!");
            }
//            HashMap<Object, Object> userModel = new HashMap<>();
//            HashMap<Object, Object> userModelOrdered = new HashMap<>();
//            userModel.put("id_user", user.getId());
//            userModel.put("user_name", user.getUserName());
//            userModel.put("first_name", user.getFirstName());
//            userModel.put("last_name", user.getLastName());
//            userModel.put("email", user.getEmail());
//            userModel.put("gender", user.getGender());
//            userModel.put("birthday", user.getBirthday());
//            userModel.put("roles", user.getRoles());
//            userModel.put("permissions", user.getPermissions());
//            userModel.put("likesSended", user.getLikesSended());
//            userModel.put("likeReceived", user.getLikeReceived());
//            userModel.put("token", token);
//            userModel.put("matches",user.getMatches());

            return ResponseEntity.ok(user);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password! " + e);
        }


    }

}
