package net.guides.springboot2.springboot2webappjsp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {
  
    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public Product addProduct(@Valid @RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
    	log.debug("Getting all Products ");
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id) throws JsonProcessingException {
    	log.debug("Getting Products for  "+ESAPI.encoder().encodeForHTML(id+""));
    	Product p=service.getProductById(id);
    	ObjectMapper objectMapper = new ObjectMapper();
    	String carAsString = objectMapper.writeValueAsString(p);

    	log.debug("Retrived object is  "+carAsString);
        return p;//service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {
    	log.debug("Getting Products for Name   "+ESAPI.encoder().encodeForHTML(name));
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
    
    public static void main(String[] args) throws JsonProcessingException {
    	Product p=new Product();
    	ObjectMapper objectMapper = new ObjectMapper();
    	String carAsString = objectMapper.writeValueAsString(p);
    	System.out.println(carAsString);
    	Encoder esapiEncoder=ESAPI.encoder();
        String encodedEndpointURL = ESAPI.encoder().encodeForHTML("<td>xxxxxxxxxxxxxxx</td>");
        System.out.println(encodedEndpointURL);

    
	}
}
