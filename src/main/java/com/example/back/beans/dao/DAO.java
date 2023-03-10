package com.example.back.beans.dao;

import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface DAO<T> {
    void create(T t);
    Optional<T> read(int id);
    List<T> readAll();
    void update(int id, T t);
    void delete(int id);

}
