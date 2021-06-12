package com.redis.repository;

import com.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO
{
    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate redisTemplate;

    public Product save(Product product)
    {
        return saveOrUpdate(product);
    }

    public Product update(Product product)
    {
        return saveOrUpdate(product);
    }

    public List<Product> getAllProducts()
    {
        return redisTemplate
                .opsForHash()
                .values(HASH_KEY);
    }
    
    public Product getProductById(int id)
    {
        System.out.println("Retrieving data from Redis Database!");
        // When the data is available in Cache, then this method won't be called.

        return (Product) redisTemplate
                .opsForHash()
                .get(HASH_KEY, id);
    }

    public String deleteProduct(int id)
    {
        redisTemplate
                .opsForHash()
                .delete(HASH_KEY, id);

        return "Product deleted.";
    }

    private Product saveOrUpdate(Product product) {
        redisTemplate
                .opsForHash()
                .put(HASH_KEY, product.getId(), product);

        return product;
    }
}
