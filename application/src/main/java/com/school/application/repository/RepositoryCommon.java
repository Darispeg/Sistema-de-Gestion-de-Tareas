package com.school.application.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface RepositoryCommon<T> {
    
    /* Metodos basicos para cada tabla */
    public boolean save(T Object);
    public boolean update(T Object);
    public List<T> findAll(Pageable pageable);
    public T findId(int id);
}
