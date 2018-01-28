package com.mycompany.tilgungsrechner.service;

import com.mycompany.tilgungsrechner.CalculatorImpl;
import com.mycompany.tilgungsrechner.ICalculator;

import java.util.HashMap;
import java.util.Map;

public class ServiceResolver {

    private final static Map<Class<?>, Object> Services;

    static {
        Services = new HashMap<Class<?>, Object>();
        Services.put(ICalculator.class, new CalculatorImpl());
    }

    public static<T> T resolve(Class<T> key) {
        return (T) Services.get(key);
    }
}