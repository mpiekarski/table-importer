package net.piekarski.ti.guice.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
public @interface PrimaryKey {
}
