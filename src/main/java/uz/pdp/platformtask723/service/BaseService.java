package uz.pdp.platformtask723.service;

import java.util.List;

public interface BaseService<T, R> {
    R add(T t);
    List<R> get();
    boolean delete(Integer id);
    boolean edit(Integer id, T t);
}
