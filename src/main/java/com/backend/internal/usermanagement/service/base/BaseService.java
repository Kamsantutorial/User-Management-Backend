package com.backend.internal.usermanagement.service.base;

import java.util.List;
import org.springframework.data.domain.Page;

import com.backend.internal.usermanagement.exception.ServerException;

/**
 * Base CRUD service
 */
public interface BaseService<T, R> {

    void create(T dto) throws ServerException;

    void update(T dto, Long id) throws ServerException;

    void delete(Long id) throws ServerException;

    T findOne(Long id) throws ServerException;

    List<T> findAll();

    Page<T> findWithPage(T dto, R pageableDTO);
}
