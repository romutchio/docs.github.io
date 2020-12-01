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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Api(value = "Контроллер для поиска документов")
public class DocumentsController
{
    Logger logger = LoggerFactory.getLogger(DocumentsController.class);

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

    @Autowired
    private IDocumentService documentService;

    @GetMapping(value = "/documents/search")
    @ApiOperation("Позволяет найти документы, содержащие подстроку 'query' в названии для текущего пользователя или содержащие все перечисленные в 'tags' теги")
    public List<Document> searchDocuments(@RequestParam(name = "query") String query, @RequestParam(name = "tags") String[] tags, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);

        if (query == null && (tags == null || tags.length == 0))
        {
            return documentService.findAll(user);
        }
        var queryDocs = query != null ? documentService.findByQuery(query, user) : List.<Document>of();
        var tagDocs = documentService.findByTags(tags, user);
        queryDocs.addAll(tagDocs);
        return queryDocs;
    }

    @GetMapping(value = "/documents")
    @ApiOperation("Позволяет получить все документы для текущего пользователя.")
    public List<Document> getAllDocuments(@AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return documentService.findAll(user);
    }
}

