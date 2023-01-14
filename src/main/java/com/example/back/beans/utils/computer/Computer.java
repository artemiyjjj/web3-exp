package com.example.back.beans.utils.computer;

import jakarta.ejb.Local;

@Local
public interface Computer<T> {
    boolean calculateHit(T t);
}
