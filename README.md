Redis with Spring Boot
-
### Redis - Remote Directory Server
> More popular as an In-Memory Data Structure store.
Redis is driven by Key-Value based Data Structure to persist the data.

#### Redis can be used as
- ***Database***
- ***Cache***
- ***Message Broker***

#### Important links 
- [Spring Data Redis](https://spring.io/projects/spring-data-redis)
- [Spring Data Redis - Reference Doc](https://docs.spring.io/spring-data/redis/docs/2.5.1/reference/html/#reference)
- [Spring Data Redis - API Doc](https://docs.spring.io/spring-data/redis/docs/2.5.1/api/)

#### Installation
- [Redis Docker Image](https://hub.docker.com/_/redis)
- [Direct download link](https://redis.io/download)
- [Download from GitHub](https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100)

#### Running Redis Docker Image
```
docker run --name redis -p 6379:6379 -d redis
```

#### Application
- ***Redis as Database and Cache Application***
	- Refer Application for the code.
- ***Redis as Message Broker***
	- Refer Application for the code.

#### Jedis Dependency
> Jedis Dependency is needed to connect the Spring Boot Application with Redis.
- [Jedis Dependency Link](https://mvnrepository.com/artifact/redis.clients/jedis) 

#### Caching

- ***@Cacheable*** - Usually used in ***GET APIs*** to store the data in Cache.
- ***@CacheEvict*** - Used when data is ***deleted*** in the Database and that needs to ***reflect in Cache***.
- ***@CachePut*** - Used when data is ***updated*** in the Database and that needs to ***reflect in the Cache***.