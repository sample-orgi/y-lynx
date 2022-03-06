package com.trss.bi.service;

import com.trss.bi.domain.TemplateFavorites;
import com.trss.bi.repository.TemplateFavoritesRepository;
import com.trss.bi.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateFavoritesService {

    private TemplateFavoritesRepository templateFavoritesRepository;

    public TemplateFavoritesService(TemplateFavoritesRepository templateFavoritesRepository) {
        this.templateFavoritesRepository = templateFavoritesRepository;
    }

    public TemplateFavorites findForCurrentUser() {
        TemplateFavorites templateFavorites = templateFavoritesRepository.findByUserId(SecurityUtils.getCurrentUserId());
        if (templateFavorites == null) {
            templateFavorites = new TemplateFavorites().userId(SecurityUtils.getCurrentUserId());
        }
        return templateFavorites;
    }

    public void updateFavorites(List<String> templateIds) {
        TemplateFavorites templateFavorites = findForCurrentUser();
        templateFavoritesRepository.save(templateFavorites.templateIds(templateIds));
    }
}
