package com.mycompany.tilgungsrechner.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceResolver {

    private final static Map<Class<?>, Object> Services;

    static {
        Services = new HashMap<>();
        Services.put(ICalculator.class, new CalculatorImpl());
        Services.put(IValidation.class, new ValidationImpl());
    }

    public static<T> T resolve(Class<T> key) {
        return (T) Services.get(key);
    }
}