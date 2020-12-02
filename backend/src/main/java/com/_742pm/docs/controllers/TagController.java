package com._742pm.docs.controllers;

import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import com._742pm.docs.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@Api(value = "Контроллер для добавления и получения тегов")
public class TagController
{

    Logger logger = LoggerFactory.getLogger(TagController.class);
    @Autowired
    private ITagService tagService;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex)
    {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @GetMapping(value = "/tags", produces = "application/json")
    @ApiOperation("Позволяет найти все теги, существующие у текущего пользователя.")
    public List<Tag> getTags(@AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return tagService.findAll(user);
    }

    @PostMapping(value = "/tags", consumes = "application/json")
    @ApiOperation("Позволяет добавить тег к документу для пользователя.")
    public void addTag(@RequestBody Tag[] tags)
    {
        Arrays.stream(tags).parallel().map(tag -> tagService.create(tag));
    }

}
