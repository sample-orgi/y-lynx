package com.trss.bi.repository;

import com.trss.bi.domain.Report;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends MongoRepository<Report, ObjectId> {
    Report findByCollabKey(String collabKey);
    Page<Report> findByUserId(Pageable pageable, Long userId);
    Page<Report> findByCustomerId(Pageable pageable, Long customerId);

    Optional<Report> findByIdAndUserId(ObjectId id, Long userId);
    Optional<Report> findByIdAndCustomerId(ObjectId id, Long customerId);

    void deleteByIdAndUserId(ObjectId id, Long userId);
    void deleteByIdAndCustomerId(ObjectId id, Long customerId);
}
