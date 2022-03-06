package com.trss.bi.web.rest;

import com.trss.bi.security.SecurityUtils;
import com.trss.bi.service.TemplateFavoritesService;
import com.trss.bi.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TemplateResource {
    private final Logger log = LoggerFactory.getLogger(TemplateResource.class);

    private TemplateService templateService;
    private TemplateFavoritesService templateFavoritesService;

    public TemplateResource(TemplateService templateService, TemplateFavoritesService templateFavoritesService) {
        this.templateService = templateService;
        this.templateFavoritesService = templateFavoritesService;
    }

    @GetMapping("/template/{id}")
    public String getTemplate(@PathVariable String id) {
        log.debug("REST request to getTemplate, id: '{}'", id);
        return "getTemplate()" + id;
    }

    @GetMapping("/templates")
    public String getTemplates() throws Exception {
        return templateService.getTemplates();
    }

    @GetMapping("/templates/favorites")
    public List<String> getFavorites() {
        return templateFavoritesService.findForCurrentUser().getTemplateIds();
    }

    @PostMapping("/templates/favorites")
    public void updateFavorites(@RequestParam(value="templateIds") ArrayList<String> templateIds) {
        log.debug("update favorites for user '{}': {}: ", SecurityUtils.getCurrentUserId(), templateIds);
        templateFavoritesService.updateFavorites(templateIds);
    }

    @PostMapping("/template/{id}")
    public String executeTemplate(@PathVariable String id, @RequestParam(value="companyIds") ArrayList<String> companyIds,
                                  @RequestParam(value="templateName") String templateName) throws IOException {
        log.debug("execute template: " + id + ", " + companyIds);
        return templateService.executeTemplate(id, companyIds, templateName);
    }
}
