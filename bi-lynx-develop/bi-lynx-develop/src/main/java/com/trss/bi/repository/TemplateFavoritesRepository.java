package com.trss.bi.repository;

import com.trss.bi.domain.TemplateFavorites;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateFavoritesRepository extends MongoRepository<TemplateFavorites, ObjectId> {
    TemplateFavorites findByUserId(Long userId);
}
