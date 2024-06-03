package com.example.productservice.controller;

import com.example.productservice.exception.model.InternalServerErrorException;
import com.example.productservice.model.dto.ProductPostSaveDto;
import com.example.productservice.model.response.ResponseBody;
import com.example.productservice.service.ProductCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductCrudController {
    private final ProductCrudService getProductService;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getProductById(@PathVariable String id){
        try{
            return ResponseEntity.ok(new ResponseBody("Success",getProductService.getProductById(id),"SUCCESS"));
        } catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PostMapping(value = "",consumes = {"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseBody> saveProduct(@RequestPart ProductPostSaveDto productPostSaveDto,
                                                    @RequestPart ("files")List<MultipartFile> files){
        try{
            return ResponseEntity.ok(new ResponseBody("Success",getProductService.saveProduct(productPostSaveDto,files),"SUCCESS"));
        } catch (Exception e){
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    public ResponseEntity<ResponseBody> deleteProductById(@PathVariable String id){
        try{
            return ResponseEntity.ok(new ResponseBody("Success", getProductService.deleteProduct(id),"SUCCESS"));
        } catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
