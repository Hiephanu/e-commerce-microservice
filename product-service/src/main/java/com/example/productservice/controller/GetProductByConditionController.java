package com.example.productservice.controller;

import com.example.productservice.exception.model.InternalServerErrorException;
import com.example.productservice.model.entity.Product;
import com.example.productservice.model.response.ResponseBody;
import com.example.productservice.service.GetProductByViewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product/views")
@AllArgsConstructor
public class GetProductByConditionController {
    private final GetProductByViewService getProductByViewService;
    @GetMapping("")
    public ResponseEntity<ResponseBody> getProductByView(@RequestParam(defaultValue =  "0") int page,
                                                         @RequestParam(defaultValue = "20") int perPage) {
        try {
            List<Product> products = getProductByViewService.getProductByView(page, perPage);
            return  ResponseEntity.ok(new ResponseBody("Success", products, "SUCCESS"));
        } catch (Exception e) {
            throw  new InternalServerErrorException(e.getMessage());
        }
    }
}
