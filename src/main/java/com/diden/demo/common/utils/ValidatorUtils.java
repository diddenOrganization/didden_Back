package com.diden.demo.common.utils;

import java.util.List;
import java.util.Objects;

public class ValidatorUtils {

    public static <T> boolean isListNullOrEmpty(List<T> objects) {
        return Objects.isNull(objects) || (objects.isEmpty());
    }
}
