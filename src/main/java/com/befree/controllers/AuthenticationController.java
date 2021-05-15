package com.befree.controllers;

import com.befree.data.model.vo.EventOwnerVO;
import com.befree.data.model.vo.UserVO;
import com.befree.security.AuthenticationCredentialsVO;
import com.befree.security.jwt.JwtTokenProvider;
import com.befree.services.EventOwnerServices;
import com.befree.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserServices userServices;
    //    @Autowired
//    private UserConverter userConverter;

    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @Autowired
    private EventOwnerServices eventOwnerServices;


    @PostMapping(value = "/user/login",
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
            throw new BadCredentialsException("Invalid username or password!");
        }


    }

    @PostMapping(value = "/eventowner/login",
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<EventOwnerVO> loginOwner(@RequestBody AuthenticationCredentialsVO data) {
        try {
            var username = data.getUsername() + ":event_owner";
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var eventOwner = eventOwnerServices.getEventOwnerByUserName(username.substring(0, username.indexOf(":")));
            var token = "";
            if (eventOwner != null) {
                eventOwner.setToken(tokenProvider.createTokenEventOwner(username, eventOwner.getRoles()));
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

            return ResponseEntity.ok(eventOwner);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password! \n" + e.getMessage() + "!");
        }


    }
}
