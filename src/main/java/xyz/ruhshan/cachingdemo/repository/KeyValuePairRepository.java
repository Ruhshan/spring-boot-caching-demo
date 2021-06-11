package xyz.ruhshan.cachingdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.ruhshan.cachingdemo.entity.KeyValuePair;

public interface KeyValuePairRepository extends JpaRepository<KeyValuePair, Long> {
    KeyValuePair findByKey(String key);
}
