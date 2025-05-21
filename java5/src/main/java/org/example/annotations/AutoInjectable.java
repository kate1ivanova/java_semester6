package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a field should be automatically injected by the dependency injection framework.
 * This annotation is used to mark fields within classes that are candidates for automatic dependency injection.
 * When a class with fields annotated with {@code AutoInjectable} is processed by the dependency injection framework,
 * it automatically injects instances of the required dependencies into these fields based on the configured bindings.
 *
 * The annotation is marked with {@code @Retention(RetentionPolicy.RUNTIME)} to ensure that it is available at runtime
 * for processing by the dependency injection framework.
 *
 * Example usage:
 * {@code
 * public class MyClass {
 *     {@literal @}AutoInjectable
 *     private MyDependency dependency;
 * }
 * }
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {
}
