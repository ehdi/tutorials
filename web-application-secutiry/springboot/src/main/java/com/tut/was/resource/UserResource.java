package com.tut.was.resource;


import com.tut.was.domain.User;
import com.tut.was.repository.UserRepository;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserResource {

    Logger logger = LoggerFactory.getLogger(UserResource.class);
    private final UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setFirstname(stripXSS(user.getFirstname()));
        return userRepository.save(user);
    }

    @PostMapping("/raw")
    public User createUserRaw(@RequestBody User user) {
        logger.debug("Request to create new raw user {}", user);
        return userRepository.save(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> allUser() {

        var responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.SET_COOKIE,
                createCookie().toString());

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(userRepository.findAll());
    }

    private ResponseCookie createCookie(){
        return ResponseCookie
                .from("token", "$%#^^#")
                .path("/")
                .sameSite("Strict")
                .domain("local.dev")
                .secure(true)
                .httpOnly(true)
                .maxAge(Duration.ofDays(1))
                .build();
    }

    private static String stripXSS(String value) {
        if (value == null) {
            return null;
        }
        value = ESAPI.encoder()
                .canonicalize(value);
        return Jsoup.clean(value, Whitelist.none());
    }


}


