package com._742pm.docs.controllers;

import com._742pm.docs.models.User;
import com._742pm.docs.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @GetMapping("/me")
    @ApiOperation("Позволяет получить всю информацию о текущем пользователе.")
    public Map<String, Object> me(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @GetMapping("/user")
    @ApiOperation("Позволяет получить информацию о текущем пользователе: имя, айди и ссылку на аватар.")
    public User user(@AuthenticationPrincipal OAuth2User principal) {
        var user =  User.fromPrincipal(principal);
        logger.info("Got user" + user.toString());
        return user;
    }

}
