package com.tut.was.resource;


import com.tut.was.domain.User;
import com.tut.was.repository.UserRepository;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserResource {

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
        return userRepository.save(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> allUser(HttpServletResponse response) {

        // create a cookie
        Cookie cookie = new Cookie("platform","mobile");

        // expires in 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);

        // optional properties
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        // add cookie to response
        response.addCookie(cookie);

        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);

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


