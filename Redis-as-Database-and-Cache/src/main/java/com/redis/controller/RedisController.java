package com.redis.controller;

import com.redis.entity.Product;
import com.redis.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class RedisController
{
    @Autowired
    private ProductDAO productDAO;

    @PostMapping
    public Product save(@RequestBody Product product)
    {
        return productDAO.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts()
    {
        return productDAO.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id)
    {
        return productDAO.getProductById(id);
    }

    @PutMapping
    public Product update(@RequestBody Product product)
    {
        return productDAO.update(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id)
    {
        return productDAO.deleteProduct(id);
    }
}
