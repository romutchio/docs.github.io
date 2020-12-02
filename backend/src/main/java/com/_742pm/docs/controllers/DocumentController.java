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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

@RestController
@Api(value = "Контроллер для работы с документом")
public class DocumentController
{
    Logger logger = LoggerFactory.getLogger(DocumentController.class);
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

    @GetMapping(value = "/document/{id}", produces = "application/json")
    @ApiOperation("Позволяет получить конкретный документ по его айди.")
    public Document getDocument(@PathVariable("id") UUID id)
    {
        return documentService.getById(id).orElse(null);
    }

    @PostMapping(value = "/document", consumes = "application/json")
    @ApiOperation("Позволяет создать документ. Возвращает айди созданного документа. Принимает список тегов, данные и имя документа.")
    public UUID postDocument(@RequestBody DocumentDTO documentDTO, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        logger.info("Got request from" + user.getName());

        var userId = user.getId();
        var document = new Document(documentDTO.getName(), userId, documentDTO.getData());
        var createdDocumentId = documentService.create(document).getId();
        var tags = Arrays.stream(documentDTO.getTags()).parallel().map(tag -> tagService.create(new Tag(tag, createdDocumentId, userId))).toArray(UUID[]::new);
        logger.info("Created " + tags.length + " of tags for user " + user.getName() + " and document " + createdDocumentId + ": " + Arrays.toString(tags));
        return createdDocumentId;

    }

    @PutMapping(value = "/document", consumes = "application/json")
    @ApiOperation("Позволяет обновить конкретный документ. В теле запроса обязательно должно быть поле id для айди документа. userId можно не проставлять.")
    public void updateDocument(@RequestBody DocumentDTO documentDTO, @AuthenticationPrincipal OAuth2User principal)
    {
        var user = User.fromPrincipal(principal);
        logger.info("Got request from" + user.getName());

        var userId = user.getId();

        var document = new Document(documentDTO.getId(), documentDTO.getName(), userId, documentDTO.getData());
        var documentId = document.getId();
        //tagService.deleteTags(documentId, userId);
        var tags = Arrays.stream(documentDTO.getTags()).parallel().map(tag ->
                tagService.create(new Tag(tag, documentId, userId))
        ).toArray(UUID[]::new);

        logger.info("Created " + tags.length + " of tags for user " + user.getName() + " and document " + documentId + ": " + Arrays.toString(tags));
        documentService.update(documentId, document);
    }

    @DeleteMapping("/documents/{id}")
    @ApiOperation("Позволяет удалить конкретный документ по его айди.")
    public void deleteDocument(@PathVariable("id") UUID id)
    {
        documentService.delete(id);
        //tagService.deleteTags(documentId, userId);
    }

}
