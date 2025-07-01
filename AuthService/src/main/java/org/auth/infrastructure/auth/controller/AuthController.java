package org.auth.infrastructure.auth.controller;

import org.auth.infrastructure.auth.model.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/auth/")
public class AuthController {

    private static final Logger log = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping(path = "/token", method=RequestMethod.GET)
    public String auth(@RequestParam String username, @RequestParam String role){
        log.info(AuthController.class.getName() + " " + username);
        return jwtUtils.createToken(username, role);
    }

}
