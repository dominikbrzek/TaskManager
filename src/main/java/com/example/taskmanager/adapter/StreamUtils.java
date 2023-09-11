package com.example.taskmanager.adapter;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@UtilityClass
public class StreamUtils {

    public static <T> Stream<T> toStreamSafe(List<T> list) {
        if (list == null) {
            return Stream.empty();
        }
        return list.stream();
    }

    public static <T> Stream<T> toStreamSafe(T[] list) {
        if (list == null) {
            return Stream.empty();
        }
        return Arrays.stream(list);
    }

    public static <T> Stream<T> toStreamSafe(Set<T> set) {
        if (set == null) {
            return Stream.empty();
        }
        return set.stream();
    }

}
