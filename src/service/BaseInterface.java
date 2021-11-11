package service;

import models.responce.Responce;

import java.util.List;
import java.util.UUID;

public interface BaseInterface<T> {
    Responce add(T t);
    Responce delete(UUID id);
    List<T> getList();
    boolean edit(UUID id);
}
