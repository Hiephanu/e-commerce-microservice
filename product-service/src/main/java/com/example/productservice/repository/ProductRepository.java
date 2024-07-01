package com.example.productservice.repository;

import com.example.productservice.model.dto.ProductFilter;
import com.example.productservice.model.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepository {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public Product save(Product product) {
        return mongoTemplate.save(product);
    }

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Product.class));
    }

    public void deleteById(String id) {
        Query q = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(q, Product.class);
    }

    public List<Product> findByCategory(String category, Pageable pageable) {
        Query query = new Query();
        query.addCriteria(Criteria.where("p_categories").in(category));
        query.with(pageable);
        return mongoTemplate.find(query, Product.class);
    }
    public List<Product> findByKeyword(String keyword, Pageable pageable){
        Query query = new Query();
        query.addCriteria(Criteria.where("p_name").regex(keyword, "i"));
        query.with(pageable);
        return  mongoTemplate.find(query, Product.class);
    }

    public  List<Product> getProductByView(Pageable pageable) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "p_views"));
        query.with(pageable);

        return mongoTemplate.find(query, Product.class);
    }

    public List<Product> filterProduct(ProductFilter productFilter, Pageable pageable){
        Criteria criteria = new Criteria();
        if (productFilter.getCategory() != null && !productFilter.getCategory().isEmpty()) {
            criteria.and("p_category").in(productFilter.getCategory());
        }
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("p_price").gte(productFilter.getMinSize()).lte(productFilter.getMaxSize())),
                Aggregation.match(criteria),
                Aggregation.match(Criteria.where("p_origin").in(productFilter.getOrigin())),
                Aggregation.skip(pageable.getOffset()),
                Aggregation.limit(pageable.getPageNumber())
        );
        AggregationResults<Product> results = mongoTemplate.aggregate(aggregation, "product", Product.class);
        return results.getMappedResults();
    }
}
