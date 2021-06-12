package com.redis.controller;

import com.redis.entity.Product;
import com.redis.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(key = "#id", value = "Product", unless = "#result.price > 1000")
    // The key is from @PathVariable.
    // The value here is defined in Product Entity class.
    // Cache data if the price is greater than 1000.
    public Product getProductById(@PathVariable int id)
    {
        return productDAO.getProductById(id);
    }

    @PutMapping
    @CachePut(key = "#product.id", value = "Product")
    // WHen data is updated, it is updated in cache as well.
    public Product update(@RequestBody Product product)
    {
        return productDAO.update(product);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Product")
    // When data is deleted, it is deleted from cache as well.
    public String deleteProduct(@PathVariable int id)
    {
        return productDAO.deleteProduct(id);
    }
}
