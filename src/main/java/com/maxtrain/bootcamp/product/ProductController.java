package com.maxtrain.bootcamp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository pRepo;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts() {
        var products = pRepo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        var p = pRepo.findById(id);
        if (p.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(p.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> postProduct(@RequestBody Product product) {
        if (product == null || product.getId() != 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var p = pRepo.save(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> putVendor(@PathVariable int id, @RequestBody Product product) {
        if (product == null || product.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var p = pRepo.findById(product.getId());
        if (p.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var result = pRepo.save(product);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        var product = pRepo.findById(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pRepo.delete(product.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
