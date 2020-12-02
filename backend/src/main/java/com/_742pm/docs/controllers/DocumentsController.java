package com._742pm.docs.controllers;

import com._742pm.docs.models.Document;
import com._742pm.docs.models.Tag;
import com._742pm.docs.models.User;
import com._742pm.docs.service.IDocumentService;
import com._742pm.docs.service.ITagService;
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
import java.util.stream.Collectors;

@RestController
@Api(value = "Контроллер для поиска документов")
public class DocumentsController
{
    Logger logger = LoggerFactory.getLogger(DocumentsController.class);
    @Autowired
    private IDocumentService documentService;
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

    private List<DocumentDTO> convertToDTOs(List<Document> documents, User user)
    {
        return documents.stream().map(document -> {
            String[] ts = tagService.getTags(user, document).stream().map(Tag::getName).toArray(String[]::new);
            return new DocumentDTO(ts, document.getId(), document.getName(), document.getUserId(), document.getData());
        }).collect(Collectors.toList());
    }

    @GetMapping(value = "/documents/search")
    @ApiOperation("Позволяет найти документы, содержащие подстроку 'query' в названии для текущего пользователя или содержащие все перечисленные в 'tags' теги")
    public List<DocumentDTO> searchDocuments(@RequestParam(name = "query", required = false) String query, @RequestParam(name = "tags", required = false) String[] tags, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);

        if (query == null && tags == null)
        {
            return convertToDTOs(documentService.findAll(user), user);
        }
        if (query == null)
        {

            return convertToDTOs(documentService.findByTags(tags, user), user);
        }
        if (tags == null)
        {
            return convertToDTOs(documentService.findByQuery(query, user), user);
        }

        var queryDocs = convertToDTOs(documentService.findByQuery(query, user), user);
        var tagDocs = convertToDTOs(documentService.findByTags(tags, user), user);
        logger.info("Found documents by tag: " + Arrays.toString(tagDocs.toArray()));
        logger.info("Found documents by query: " + Arrays.toString(queryDocs.toArray()));
        return queryDocs.stream().filter(tagDocs::contains).collect(Collectors.toList());
    }

    @GetMapping(value = "/documents")
    @ApiOperation("Позволяет получить все документы для текущего пользователя.")
    public List<DocumentDTO> getAllDocuments(@AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        return convertToDTOs(documentService.findAll(user), user);
    }
}

