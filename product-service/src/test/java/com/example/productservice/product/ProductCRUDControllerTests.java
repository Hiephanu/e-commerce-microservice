package com.example.productservice.product;

import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductCrudService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductCRUDControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ProductRepository productRepository;
    @Autowired
    private ProductCrudService productCrudService;

    @Test
    public void testService() {
        List<Product> products= new ArrayList<>();

        for(int i=0;i < 5;i++) {
            products.add(new Product());
        }

    }
}
