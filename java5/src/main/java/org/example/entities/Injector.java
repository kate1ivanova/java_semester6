package org.example.entities;

import org.example.annotations.AutoInjectable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Manages dependency injection by reading class mappings from a properties file
 * and injecting concrete implementations into fields annotated with {@code AutoInjectable}.
 *
 * This class allows configuration of dependencies through properties files that map interface names
 * to their corresponding implementation classes. It supports automatic dependency injection for any
 * objects containing fields annotated with {@code AutoInjectable}.
 *
 * Two constructors are provided to load properties from different sources:
 * - A default constructor that loads from a fixed resource named "res/some.properties".
 * - A parameterized constructor that accepts a custom configuration path.
 *
 * Example usage:
 * {@code
 * Injector injector = new Injector("config/myapp.properties");
 * MyApplication app = new MyApplication();
 * injector.inject(app);  // Inject dependencies based on configured mappings
 * }
 *
 */
public class Injector {

    private final Properties properties;

    /**
     * Creates an instance of {@code Injector} using a default properties file located at "res/some.properties".
     * Initializes properties and loads the configuration from the specified resource.
     */
    public Injector() {
        properties = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(
                    "res/some.properties"
            );
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates an instance of {@code Injector} with a custom configuration path for the properties file.
     * @param configPath the path to the configuration file that contains the mappings of interfaces to their implementations.
     */
    public Injector(String configPath) {
        properties = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(
                    configPath
            );
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Injects dependencies into an object's {@code AutoInjectable} annotated fields based on the current properties configuration.
     * @param object the object to inject dependencies into
     * @return the same object with dependencies injected
     * @param <T> the type of the object
     */
    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                field.setAccessible(true);
                String interfaceName = field.getType().getName();
                try {
                    Class<?> implClass = Class.forName(properties.getProperty(interfaceName));
                    Object implInstance = implClass.newInstance();
                    field.set(object, implInstance);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }
}
