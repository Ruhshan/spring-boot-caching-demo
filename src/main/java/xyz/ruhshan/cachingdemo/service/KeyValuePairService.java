package xyz.ruhshan.cachingdemo.service;

import xyz.ruhshan.cachingdemo.entity.KeyValuePair;

public interface KeyValuePairService {
    KeyValuePair findByKey(String key);
}
