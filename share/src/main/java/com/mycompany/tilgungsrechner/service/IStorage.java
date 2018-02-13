package com.mycompany.tilgungsrechner.service;

/**
 * stores, retrieves and deletes objects
 * for passing data between stages/scenes
 */
public interface IStorage {

    void store(String key, Object o);
    Object get(String key);
    void del(String key);
}