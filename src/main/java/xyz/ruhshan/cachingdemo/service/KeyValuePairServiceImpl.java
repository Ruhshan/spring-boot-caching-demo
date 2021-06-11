package xyz.ruhshan.cachingdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.ruhshan.cachingdemo.entity.KeyValuePair;
import xyz.ruhshan.cachingdemo.repository.KeyValuePairRepository;

import java.util.Optional;

@Service
@Slf4j
public class KeyValuePairServiceImpl implements KeyValuePairService {
    private final KeyValuePairRepository keyValuePairRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public KeyValuePairServiceImpl(KeyValuePairRepository keyValuePairRepository, RedisTemplate<String, Object> redisTemplate) {
        this.keyValuePairRepository = keyValuePairRepository;
        this.redisTemplate = redisTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public KeyValuePair findByKey(String key) {

        return checkInCache(key).orElseGet(()->this.getFromDb(key));

    }

    @Override
    public void save(KeyValuePair keyValuePair) {
        keyValuePairRepository.save(keyValuePair);
        saveInCache(keyValuePair.getKey(), keyValuePair);
    }

    private Optional<KeyValuePair> checkInCache(String key) {

        try {
            Object entryInCache = redisTemplate.opsForValue().get(key);
            return Optional.of(objectMapper.readValue(entryInCache.toString(), KeyValuePair.class));

        } catch (Exception e) {
            log.warn("Error in processing {}", e.getMessage());
        }

        return Optional.empty();

    }

    private KeyValuePair getFromDb(String key){
        KeyValuePair keyValuePair = keyValuePairRepository.findByKey(key);
        saveInCache(key, keyValuePair);
        return keyValuePair;
    }

    private void saveInCache(String key, KeyValuePair keyValuePair){
        try {
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(keyValuePair));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
