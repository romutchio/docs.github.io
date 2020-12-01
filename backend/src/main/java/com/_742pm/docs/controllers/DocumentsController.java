package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.User;
import com._742pm.docs.service.IDocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "Контроллер для поиска документов")
public class DocumentsController
{
    Logger logger = LoggerFactory.getLogger(DocumentsController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @Autowired
    private IDocumentService documentService;

    @GetMapping(value = "/documents/query", params = "query")
    @ApiOperation("Позволяет найти документы, содержащие подстроку 'query' в названии для текущего пользователя.")
    public List<Document> getDocumentsByQuery(@RequestParam(name = "query") String query, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return documentService.findByQuery(query, user);
    }

    @GetMapping(value = "/documents/tag", params = "tag")
    @ApiOperation("Позволяет найти документы с тегом 'tag' для текущего пользователя.")
    public List<Document> getDocumentsByTag(@RequestParam(name = "tag") String tag, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return documentService.findByTag(tag, user);
    }

    @GetMapping(value = "/documents")
    @ApiOperation("Позволяет получить все документы для текущего пользователя.")
    public List<Document> getAllDocuments(@AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return documentService.findAll(user);
    }
}

