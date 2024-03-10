package com.backend.internal.usermanagement.common.util;

import java.lang.reflect.Field;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClassUtils {
    
    public static boolean containsObjectOfType(Class<?> dtoClass, Class<?> clazz) {
        for (Field field : dtoClass.getDeclaredFields()) {
            if (field.getType() == clazz) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGenericClass(Class<?> clazz) {
        return clazz.getTypeParameters().length > 0;
    }
    
}
