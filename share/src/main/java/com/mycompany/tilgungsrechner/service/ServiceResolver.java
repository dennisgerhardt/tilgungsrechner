package com.mycompany.tilgungsrechner.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class ServiceResolver {

    private final static String IMPL_SUFFIX = "Impl";
    private final static Map<Class<?>, Object> SERVICES;

    static {
        SERVICES = new HashMap<>();

        SERVICES.put(IValidation.class, constructImplementation(IValidation.class.getSimpleName()));
        SERVICES.put(IRepaymentSchedule.class, constructImplementation(IRepaymentSchedule.class.getSimpleName()));
        SERVICES.put(ISceneManager.class, constructImplementation(ISceneManager.class.getSimpleName()));
        SERVICES.put(IStorage.class, constructImplementation(IStorage.class.getSimpleName()));
    }

    public static<T> T resolve(Class<T> key) {
        return (T) SERVICES.get(key);
    }

    private static Object constructImplementation(final String interfaceName) {
        final String className = interfaceName.substring(1) + IMPL_SUFFIX;

        try {
            Class<?> aClass = Class.forName("com.mycompany.tilgungsrechner.service." + className);
            Constructor<?> constructor = aClass.getDeclaredConstructor();
            Object o = constructor.newInstance();

            return o;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException
                | InvocationTargetException ex)
        {
            ex.printStackTrace();
            System.exit(-1);
        }

        throw new RuntimeException("System.exit");
    }
}