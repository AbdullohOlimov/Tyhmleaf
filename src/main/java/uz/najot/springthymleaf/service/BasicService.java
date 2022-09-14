package uz.najot.springthymleaf.service;

import java.util.Optional;

public interface BasicService<T> {
    boolean save(T t);
    Optional<T> findById(Integer id);
}
