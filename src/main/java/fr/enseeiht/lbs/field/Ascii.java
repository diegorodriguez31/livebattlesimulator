package main.java.fr.enseeiht.lbs.field;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface allowing to store on an Enum value an Ascii Character.
 * This is typically used when we store an instance of the enumeration on disk.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Ascii {
        public char value();
    }

