package xyz.ruhshan.cachingdemo.controller;

import org.springframework.web.bind.annotation.*;
import xyz.ruhshan.cachingdemo.entity.KeyValuePair;
import xyz.ruhshan.cachingdemo.service.KeyValuePairService;

@RestController
public class KeyValuePairController {
    private final KeyValuePairService keyValuePairService;

    public KeyValuePairController(KeyValuePairService keyValuePairService) {
        this.keyValuePairService = keyValuePairService;
    }

    @GetMapping("/{key}")
    public KeyValuePair getByKey(@PathVariable("key") String key){
        return keyValuePairService.findByKey(key);
    }
}
