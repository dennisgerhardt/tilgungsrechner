package com.mycompany.tilgungsrechner.service;

import java.util.Map;
import java.util.WeakHashMap;

public class StorageImpl implements IStorage {

    private Map<String, Object> storage;

    StorageImpl() {
        storage = new WeakHashMap<>();
    }

    @Override
    public void store(String key, Object o) {
        storage.put(key, o);
    }

    @Override
    public Object get(String key) {
        return storage.get(key);
    }

    @Override
    public void del(String key) {
        storage.remove(key);
    }
}