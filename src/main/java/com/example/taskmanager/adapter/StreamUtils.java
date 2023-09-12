package com.example.taskmanager.adapter;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class StreamUtils {

    public static <T> Stream<T> toStreamSafe(List<T> list) {
        if (list == null) {
            return Stream.empty();
        }
        return list.stream();
    }
}
