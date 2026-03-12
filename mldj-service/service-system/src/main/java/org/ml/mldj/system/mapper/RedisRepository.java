package org.ml.mldj.system.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Async("taskExecutor")
    public void asyncDelete(String key) {
        try {
            Thread.sleep(500);
            redisTemplate.delete(key);
        } catch (Exception e) {}
    }
}
