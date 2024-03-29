package xyz.ruhshan.cachingdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.ruhshan.cachingdemo.entity.KeyValuePair;
import xyz.ruhshan.cachingdemo.repository.KeyValuePairRepository;

import java.util.Optional;

@Service
@Slf4j
public class KeyValuePairServiceImpl implements KeyValuePairService {
    private final KeyValuePairRepository keyValuePairRepository;

    public KeyValuePairServiceImpl(KeyValuePairRepository keyValuePairRepository) {
        this.keyValuePairRepository = keyValuePairRepository;
    }

    @Override
    @Cacheable(value="myAwesomeCache",key="#key",condition = "#key.startsWith('frequent_')")
    public KeyValuePair findByKey(String key) {

        return keyValuePairRepository.findByKey(key);

    }

    @Override
    @CacheEvict(value = "myAwesomeCache", key = "#keyValuePair.key")
    public void save(KeyValuePair keyValuePair) {
        keyValuePairRepository.save(keyValuePair);
    }

}
