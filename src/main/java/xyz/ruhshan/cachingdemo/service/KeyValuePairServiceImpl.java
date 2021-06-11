package xyz.ruhshan.cachingdemo.service;

import org.springframework.stereotype.Service;
import xyz.ruhshan.cachingdemo.entity.KeyValuePair;
import xyz.ruhshan.cachingdemo.repository.KeyValuePairRepository;

@Service
public class KeyValuePairServiceImpl implements KeyValuePairService {
    private final KeyValuePairRepository keyValuePairRepository;

    public KeyValuePairServiceImpl(KeyValuePairRepository keyValuePairRepository) {
        this.keyValuePairRepository = keyValuePairRepository;
    }

    @Override
    public KeyValuePair findByKey(String key) {
        return keyValuePairRepository.findByKey(key);
    }
}
