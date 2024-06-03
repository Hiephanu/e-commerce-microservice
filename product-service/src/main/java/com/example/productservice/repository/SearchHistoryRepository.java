package com.example.productservice.repository;

import com.example.productservice.model.entity.SearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryRepository extends MongoRepository<SearchHistory, String> {
}
