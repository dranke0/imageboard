package com.example.imageboard.prototype;

import java.util.List;

public interface Mapper<T, S> {
    T toDto(S s);
    S toEntity(T t);
    List<T> toDto(List<S> s);
    List<S> toEntity(List<T> t);
}
