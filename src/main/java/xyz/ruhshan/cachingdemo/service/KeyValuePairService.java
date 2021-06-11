package xyz.ruhshan.cachingdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import xyz.ruhshan.cachingdemo.entity.KeyValuePair;

public interface KeyValuePairService {
    KeyValuePair findByKey(String key);
    void save(KeyValuePair keyValuePair);
}
