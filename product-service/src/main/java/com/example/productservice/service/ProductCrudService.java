package com.example.productservice.service;

import com.example.productservice.converter.ConvertProduct;
import com.example.productservice.exception.model.InvalidException;
import com.example.productservice.exception.model.NotFoundException;
import com.example.productservice.model.dto.FileSaveReturn;
import com.example.productservice.model.dto.ProductPostSaveDto;
import com.example.productservice.model.entity.Product;
import com.example.productservice.model.entity.ProductMedia;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductCrudService {
    private final ProductRepository productRepository;
    private final ConvertProduct convertProduct;
    private final MinioStorageService minioStorageService;
    public Product getProductById(String id){
       if(id ==null){
           throw new InvalidException("Id can not be null");
       } else {
           Optional<Product> product = productRepository.findById(id);
           if(product.isPresent()){
               return product.get();
           } else  {
               throw new NotFoundException("Product not found");
           }
       }
    }

    public Product saveProduct(ProductPostSaveDto productPostSaveDto,List<MultipartFile> files) throws Exception {
        List<ProductMedia> productMedia = new ArrayList<>();
        for(MultipartFile file : files) {
            FileSaveReturn fileSaveReturn = minioStorageService.save(file,file.getOriginalFilename());
            productMedia.add(ProductMedia.convertToProductMedia(fileSaveReturn));
        }
        Product productConvert=  convertProduct.convertToProduct(productPostSaveDto);
        productConvert.setP_images(productMedia);
        return productRepository.save(productConvert);
    }

    public Product updateProductById(){
        return  null;
    }
    public String deleteProduct(String id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return id;
        } else {
            throw new NotFoundException("Product not found");
        }
    }
}
